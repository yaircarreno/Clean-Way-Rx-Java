package com.clean.way.rx.items;

import android.util.Log;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Item32 {

    public static String TAG = "Item32";
    private CompositeDisposable compositeDisposable;

    public Item32() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
    }

    public void itemExample() {
        this.case2();
    }

    private void case2() {


        Observable<Long> sourceShared = Observable
                .interval(1, TimeUnit.SECONDS, Schedulers.newThread())
                .share();

        Observable<String> sourceOne = sourceShared
                .map(numberLong ->
                        "Source one: " + numberLong + " =>")
                .take(3);

        Observable<String> sourceTwo = sourceShared
                .map(numberLong ->
                        "Source two: " + numberLong + " =>")
                .take(2);

        Disposable subscription = Observable
                .concat(sourceOne, sourceTwo)
                .throttleLatest(1, TimeUnit.SECONDS, Schedulers.computation())
                .doOnNext(element -> loggerScheduler(element, "Observable"))
                .subscribe(element -> this.loggerScheduler(element, "Observer"));

        sleep(5000);
        compositeDisposable.clear();
        subscription.dispose();
    }

    private void loggerScheduler(String element, String origin) {
        Log.d(TAG, element + " " + origin + " code executed on " + Thread.currentThread().getName());
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
