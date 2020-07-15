package com.clean.way.rx.items;

import android.util.Log;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class Item20 {

    public static String TAG = "Item20";
    private CompositeDisposable compositeDisposable;

    public Item20() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
    }

    public void itemExample() {
        this.case2();
    }

    private void case1() {

        compositeDisposable.add(
                Observable.fromArray("A", "B", "C")
                        .single("A")
                        .subscribe(item -> Log.d(TAG, "Item: " + item)));
    }

    private void case2() {

        compositeDisposable.add(
                Observable.fromArray("A", "B", "C")
                        .take(1)
                        .single("A")
                        .subscribe(item -> Log.d(TAG, "Item: " + item)));
    }
}
