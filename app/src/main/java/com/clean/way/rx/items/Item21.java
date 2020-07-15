package com.clean.way.rx.items;

import android.util.Log;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class Item21 {

    public static String TAG = "Item21";
    private CompositeDisposable compositeDisposable;

    public Item21() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
    }

    public void itemExample() {
        this.case3();
    }

    private void case1() {

        compositeDisposable.add(
                operationRx()
                        .map(item -> item * 3)
                        .subscribe(item -> Log.d(TAG, "Item: " + item),
                                throwable -> Log.e(TAG, "error: " + throwable),
                                this::lastStep));
    }

    private void case2() {

        Disposable subscription =
                operationRxChanged()
                        .map(item -> item * 3)
                        .subscribe(item -> Log.d(TAG, "Item: " + item),
                                throwable -> Log.e(TAG, "error: " + throwable),
                                this::lastStep);

        sleep(5000);
        subscription.dispose();
    }

    private void case3() {
        compositeDisposable.add(
                numberEmitter()
                        .map(item -> item * 3)
                        .subscribe(item -> Log.d(TAG, "Item: " + item),
                                throwable -> Log.e(TAG, "error: " + throwable),
                                this::lastStep));
    }

    private void case4() {
        compositeDisposable.add(
                wrongNumberEmitter()
                        .map(item -> item * 3)
                        .subscribe(item -> Log.d(TAG, "Item: " + item),
                                throwable -> Log.e(TAG, "error: " + throwable),
                                this::lastStep));
    }

    private Observable<Integer> operationRx() {
        return Observable.fromIterable(Arrays.asList(1, 2, 3, 4, 5));
    }

    private Observable<Integer> operationRxChanged() {
        return Observable.interval(1, TimeUnit.SECONDS).map(Long::intValue);
    }

    private Observable<Integer> numberEmitter() {

        return Observable.create(emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onComplete();
        });
    }

    private Observable<Integer> wrongNumberEmitter() {

        return Observable.create(emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onComplete();
            emitter.onNext(3);
        });
    }

    private void lastStep() {
        Log.d(TAG, "Do any critical thing, for instance, release resources.");
    }

    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
            if (compositeDisposable != null) {
                compositeDisposable.clear();
            }
        } catch (InterruptedException e) {
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }
    }
}
