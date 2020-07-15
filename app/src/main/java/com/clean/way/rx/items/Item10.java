package com.clean.way.rx.items;

import android.util.Log;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class Item10 {

    public static String TAG = "Item10";
    private CompositeDisposable compositeDisposable;

    public Item10() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
    }

    public void itemExample() {
        this.case1();
    }

    private void case1() {
        compositeDisposable.add(
                Observable.just("data")
                        .doOnComplete(this::runProcess)
                        .map(item -> "transformed " + item)
                        .subscribe(item -> Log.d(TAG, "item: " + item)));
    }

    private void case2() {
        compositeDisposable.add(
                Observable.just("data")
                        .doOnComplete(this::runBlock)
                        .map(item -> "transformed " + item)
                        .subscribe(item -> Log.d(TAG, "item: " + item)));
    }

    private void runProcess() {
        Log.d(TAG, "Running any process");
    }

    private Completable runBlock() {
        return Completable.fromRunnable(this::runProcess);
    }
}
