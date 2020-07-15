package com.clean.way.rx.items;

import android.util.Log;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class Item12 {

    public static String TAG = "Item12";
    private CompositeDisposable compositeDisposable;

    public Item12() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
    }

    public void itemExample() {
        this.case1();
    }

    private void case1() {
        compositeDisposable.add(
                Observable.fromArray(itemsWithoutNulls())
                        .map(String::toUpperCase)
                        .subscribe(element -> Log.d(TAG, "" + element)));
    }

    private void case2() {
        compositeDisposable.add(
                Observable.fromArray(items())
                        .filter(item -> item != null)
                        .map(String::toUpperCase)
                        .subscribe(element -> Log.d(TAG, "" + element)));
    }

    private String[] items() {
        return new String[]{"h", "e", null, "l", "o"};
    }

    private String[] itemsWithoutNulls() {
        return new String[]{"h", "e", "l", "l", "o"};
    }
}
