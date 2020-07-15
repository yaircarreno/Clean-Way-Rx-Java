package com.clean.way.rx.items;

import android.util.Log;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Item31 {

    public static String TAG = "Item31";
    private CompositeDisposable compositeDisposable;

    public Item31() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
    }

    public void itemExample() {
        this.case4();
    }

    private void case1() {
        compositeDisposable.add(
                colorDataStream()
                        .subscribeOn(Schedulers.newThread())
                        .doOnNext(element -> this.loggerScheduler(element, "Observable"))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(element -> this.loggerScheduler(element, "Observer")));
    }

    private void case2() {
        compositeDisposable.add(
                colorDataStream()
                        .doOnNext(element -> this.loggerScheduler(element, "Observable"))
                        .observeOn(Schedulers.newThread())
                        .doOnNext(element -> this.loggerScheduler(element, "Observable"))
                        .subscribeOn(Schedulers.computation())
                        .subscribe(element -> this.loggerScheduler(element, "Observer")));
    }

    private void case3() {
        compositeDisposable.add(
                colorDataStream()
                        .doOnNext(element -> this.loggerScheduler(element, "Observable"))
                        .subscribeOn(Schedulers.newThread())
                        .doOnNext(element -> this.loggerScheduler(element, "Observable"))
                        .subscribeOn(Schedulers.computation())
                        .subscribe(element -> this.loggerScheduler(element, "Observer")));
    }

    private void case4() {
        compositeDisposable.add(
                colorDataStream()
                        .doOnNext(element -> this.loggerScheduler(element, "Observable"))
                        .observeOn(Schedulers.newThread())
                        .doOnNext(element -> this.loggerScheduler(element, "Observable"))
                        .observeOn(Schedulers.computation())
                        .subscribe(element -> this.loggerScheduler(element, "Observer")));
    }

    private Observable<String> colorDataStream() {
        return Observable.create(emitter -> {
            emitter.onNext("Gray =>");
            emitter.onComplete();
        });
    }

    private void loggerScheduler(String element, String origin) {
        Log.d(TAG, element + " " + origin + " code executed on " + Thread.currentThread().getName());
    }
}
