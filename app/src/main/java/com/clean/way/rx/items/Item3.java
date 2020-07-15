package com.clean.way.rx.items;

import android.util.Log;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class Item3 {

    public static String TAG = "Item3";
    private CompositeDisposable compositeDisposable;

    public Item3() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
    }

    public void itemExample() {
        this.case3();
    }

    private void case1() {

        Observable<Integer> first = Observable.fromArray(10, 100, 1000);
        Observable<Integer> second = Observable.fromArray(20, 200, 2000);

        compositeDisposable.add(
                first.concatWith(second)
                        .subscribe(number -> Log.d(TAG, "" + number)));
    }

    private void case2() {

        Observable<Integer> first = Observable.interval(1, TimeUnit.SECONDS)
                .map(numberLong -> numberLong != null ? numberLong.intValue() : 0);
        Observable<Integer> second = Observable.fromArray(20, 200, 2000);

        compositeDisposable.add(
                first.concatWith(second)
                        .subscribe(number -> Log.d(TAG, "" + number)));

        sleep(5000);
        compositeDisposable.clear();
    }

    private void case3() {

        Observable<Integer> first = Observable.interval(1, TimeUnit.SECONDS)
                .map(numberLong -> numberLong != null ? numberLong.intValue() : 0)
                .take(3);
        Observable<Integer> second = Observable.fromArray(20, 200, 2000);

        compositeDisposable.add(
                first.concatWith(second)
                        .subscribe(number -> Log.d(TAG, "" + number)));

        sleep(5000);
        compositeDisposable.clear();
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
