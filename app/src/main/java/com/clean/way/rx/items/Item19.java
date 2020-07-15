package com.clean.way.rx.items;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class Item19 {

    public static String TAG = "Item19";
    private CompositeDisposable compositeDisposable;

    public Item19() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
    }

    public void itemExample() {
        this.case3();
    }

    private void case1() {

        Observable<Integer> source = Observable.fromArray(10, 100, 1000);

        compositeDisposable.add(
                source.reduce(1, (accumulator, next) -> accumulator + next)
                        .subscribe(number -> Log.d(TAG, "" + number)));
    }

    private void case2() {

        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS);

        compositeDisposable.add(
                source.reduce(1, (accumulator, next) -> accumulator + next.intValue())
                        .subscribe(number -> Log.d(TAG, "" + number)));
    }

    private void case3() {

        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS)
                .take(3);

        compositeDisposable.add(
                source.reduce(1, (accumulator, next) -> accumulator + next.intValue())
                        .subscribe(number -> Log.d(TAG, "" + number)));
    }
}
