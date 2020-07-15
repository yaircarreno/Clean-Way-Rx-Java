package com.clean.way.rx.items;

import android.util.Log;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class Item1 {

    public static String TAG = "Item1";
    private CompositeDisposable compositeDisposable;

    public Item1() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
    }

    public void itemExample() {
        this.case11();
    }

    private void case1() {

        compositeDisposable.add(
                Observable.fromArray(Arrays.asList("h", "e"), Arrays.asList("l", "l", "o"))
                        .subscribe(element -> Log.d(TAG, "" + element)));
    }

    private void case2() {

        compositeDisposable.add(
                Observable.just(Arrays.asList("h", "e", "l", "l", "o"))
                        .subscribe(element -> Log.d(TAG, "" + element)));
    }

    private void case3() {

        compositeDisposable.add(
                Observable.fromIterable(Arrays.asList("h", "e", "l", "l", "o"))
                        .subscribe(element -> Log.d(TAG, "" + element)));
    }

    private void case4() {

        compositeDisposable.add(
                Observable.range(1, 5)
                        .subscribe(element -> Log.d(TAG, "" + element)));
    }

    private void case5() {

        compositeDisposable.add(
                Observable.defer(() ->
                        Observable.just(Arrays.asList("h", "e", "l", "l", "o")))
                        .subscribe(element -> Log.d(TAG, "" + element)));
    }

    private void case6() {

        compositeDisposable.add(
                Observable.empty()
                        .subscribe(element -> Log.d(TAG, "" + element),
                                throwable -> Log.e(TAG, "error:" + throwable),
                                () -> Log.d(TAG, "completed")));
    }

    private void case7() {

        compositeDisposable.add(
                Observable.never()
                        .subscribe(element -> Log.d(TAG, "" + element),
                                throwable -> Log.e(TAG, "error:" + throwable),
                                () -> Log.d(TAG, "completed")));
    }

    private void case8() {
        compositeDisposable.add(
                Observable.error(() -> {
                    throw new Exception("SampleError!");
                })
                        .subscribe(element -> Log.d(TAG, "" + element),
                                throwable -> Log.e(TAG, "error:" + throwable),
                                () -> Log.d(TAG, "completed")));
    }

    private void case9() {
        compositeDisposable.add(
                Observable.interval(1, TimeUnit.SECONDS)
                        .subscribe(element -> Log.d(TAG, "" + element)));

        sleep(5000);
        compositeDisposable.clear();
    }

    private void case10() {
        compositeDisposable.add(
                Observable.timer(1, TimeUnit.SECONDS)
                        .subscribe(element -> Log.d(TAG, "" + element),
                                throwable -> Log.e(TAG, "error:" + throwable),
                                () -> Log.d(TAG, "completed")));

        sleep(5000);
        compositeDisposable.clear();
    }

    private void case11() {
        compositeDisposable.add(
                taskWrapped(this.imperativeTask())
                        .subscribe(data -> Log.d(TAG, "next: " + data),
                                throwable -> Log.e(TAG, "error: " + throwable),
                                () -> Log.d(TAG, "completed")));
    }

    private Observable<String> taskWrapped(final Object task) {

        return Observable.create(emitter -> {
            try {
                String data = (String) task;
                emitter.onNext(data);
                emitter.onComplete();
            } catch (Throwable e) {
                emitter.onError(e);
            }
        });
    }

    private String imperativeTask() {

        String data = "Any data";
        Log.d(TAG, "Do any imperative task or process");
        return data;
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