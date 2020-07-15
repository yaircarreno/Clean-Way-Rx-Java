package com.clean.way.rx.items;

import android.util.Log;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class Item15 {

    public static String TAG = "Item15";
    private CompositeDisposable compositeDisposable;

    public Item15() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
    }

    public void itemExample() {
        this.case3();
    }

    private void case1() {

        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS);

        Disposable subscriptionOne = source
                .map(longNumber -> longNumber * 3)
                .filter(number -> number % 2 == 0)
                .subscribe(data -> Log.d(TAG, "from observer one: " + data));

        Disposable subscriptionTwo = source
                .map(longNumber -> longNumber * 3)
                .filter(number -> number % 3 == 0)
                .subscribe(data -> Log.d(TAG, "from observer two: " + data));

        sleep(5000);
        subscriptionOne.dispose();
        subscriptionTwo.dispose();
    }

    private void case2() {

        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS)
                .map(longNumber -> longNumber * 3);

        Disposable subscriptionOne = source
                .filter(number -> number % 2 == 0)
                .subscribe(data -> Log.d(TAG, "from observer one: " + data));

        Disposable subscriptionTwo = source
                .filter(number -> number % 3 == 0)
                .subscribe(data -> Log.d(TAG, "from observer two: " + data));

        sleep(5000);
        subscriptionOne.dispose();
        subscriptionTwo.dispose();
    }

    private void case3() {

        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS)
                .map(longNumber -> longNumber * 3)
                .share();

        Disposable subscriptionOne = source
                .filter(number -> number % 2 == 0)
                .subscribe(data -> Log.d(TAG, "from observer one: " + data));

        Disposable subscriptionTwo = source
                .filter(number -> number % 3 == 0)
                .subscribe(data -> Log.d(TAG, "from observer two: " + data));

        sleep(5000);
        subscriptionOne.dispose();
        subscriptionTwo.dispose();
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
