package com.clean.way.rx.items;

import android.util.Log;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class Item23 {

    public static String TAG = "Item23";
    private CompositeDisposable compositeDisposable;

    public Item23() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
    }

    public void itemExample() {
        this.case1();
    }

    private void case1() {

        Observable<String> uppercase = Observable.fromArray("A", "B", "C", "D");
        Observable<String> lowercase = Observable.fromArray("a", "b", "c", "d");

        compositeDisposable.add(
                uppercase
                        .switchMap(upperLetter -> lowercase
                                .map(lowerLetter -> upperLetter + lowerLetter))
                        .subscribe(item -> Log.d(TAG, "" + item)));
    }

    private void case2() {

        Observable<String> uppercase = Observable.fromArray("A", "B", "C", "D");
        Observable<String> lowercase = Observable.fromArray("a", "b", "c", "d");

        compositeDisposable.add(
                Observable.combineLatest(uppercase, lowercase, (upperLetter, lowerLetter) ->
                        upperLetter + lowerLetter)
                        .subscribe(item -> Log.d(TAG, "" + item)));

    }
}
