package com.clean.way.rx.items;

import android.util.Log;
import android.util.Pair;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class Item8 {

    public static String TAG = "Item8";
    private CompositeDisposable compositeDisposable;

    public Item8() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
    }

    public void itemExample(int first, int second) {

        compositeDisposable.add(
                Observable.just(new Pair<>(first, second))
                        .map(pair -> Math.max(pair.first, pair.second))
                        .subscribe(data -> Log.d(TAG, "next:" + data),
                                throwable -> Log.e(TAG, "error:" + throwable),
                                () -> Log.d(TAG, "completed"))
        );
    }

    // Most complex way
    private Observable<Integer> maximumComplexWay(int first, int second) {
        return Observable.just(Math.max(first, second));
    }

    // Super complex way
    private Observable<Integer> maximumSuperComplexWay(Observable<Integer> left, Observable<Integer> right) {
        return Observable.zip(left, right, (first, second) -> first < second ? second : first);
    }

    public void clearDisposables() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}
