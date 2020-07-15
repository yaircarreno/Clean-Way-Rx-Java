package com.clean.way.rx.items;

import android.util.Log;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class Item26 {

    public static String TAG = "Item26";
    private CompositeDisposable compositeDisposable;

    public Item26() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
    }

    public void itemExample() {
        this.case1();
    }

    private void case1() {

        Observable<String> calculate = Observable.just(this.doAnyOperation());
    }

    private void case2() {

        Observable<String> calculate = Observable.just(this.doAnyOperation());

        calculate
                .subscribe(ignored -> Log.d(TAG, "Executed in Observer"));
    }

    private void case3() {

        Observable<String> calculate = Observable.defer(() ->
                Observable.just(this.doAnyOperation()));
    }

    private void case4() {

        Observable<String> calculate = Observable.defer(() ->
                Observable.just(this.doAnyOperation()));

        calculate
                .subscribe(ignored -> Log.d(TAG, "Executed in Observer"));
    }

    private String doAnyOperation() {
        Log.d(TAG, "Do it!");
        return "";
    }
}
