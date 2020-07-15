package com.clean.way.rx.items;

import android.util.Log;
import android.util.Pair;

import java.util.Calendar;
import java.util.Objects;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class Item27 {

    public static String TAG = "Item27";
    private CompositeDisposable compositeDisposable;

    public Item27() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
    }

    public void itemExample() {
        this.case1();
    }

    private void case1() {

        Observable<String> source = queryToAPI();

        sleep(10000);
        Disposable subscription = source.flatMap(result1 -> validateDataFromAPI()
                .map(result2 -> new Pair<>(result1, result2)))
                .subscribe(pair -> {
                    Log.d(TAG, pair.first);
                    Log.d(TAG, pair.second);
                });
        subscription.dispose();
    }

    private void case2() {

        Observable<String> source = queryToAPIWhitDefer();

        sleep(10000);
        Disposable subscription = source.flatMap(result1 -> validateDataFromAPI()
                .map(result2 -> new Pair<>(result1, result2)))
                .subscribe(pair -> {
                    Log.d(TAG, pair.first);
                    Log.d(TAG, pair.second);
                });
        subscription.dispose();
    }

    private Observable<String> queryToAPI() {
        return Observable.just("Query API at => " + getCurrentTime());
    }

    private Observable<String> queryToAPIWhitDefer() {
        return Observable.defer(() ->
                Observable.just("Query API at => " + getCurrentTime())
        );
    }

    private Observable<String> validateDataFromAPI() {
        return Observable.just("Validate data from API at => " + getCurrentTime());
    }

    private String getCurrentTime() {
        Calendar now = Calendar.getInstance();
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);
        return minute + "m: " + second + "s";
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
