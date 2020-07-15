package com.clean.way.rx.items;

import android.util.Log;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

public class Item29 {

    public static String TAG = "Item29";
    private CompositeDisposable compositeDisposable;

    public Item29() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
    }

    public void itemExample() {
        this.case4();
    }

    private void case1() {

        Observable<String> sourceObservable = Observable
                .interval(1, TimeUnit.SECONDS)
                .flatMap(id -> Observable.just("Request to API with userId = " + id))
                .doOnNext(this::print)
                .take(1);

        Disposable subscriptionA = sourceObservable
                .subscribe(element -> {
                    // A Observing code
                });

        Disposable subscriptionB = sourceObservable
                .subscribe(element -> {
                    // B Observing code
                });

        Disposable subscriptionC = sourceObservable
                .subscribe(element -> {
                    // C Observing code
                });

        sleep(5000);
        compositeDisposable.clear();
        subscriptionA.dispose();
        subscriptionB.dispose();
        subscriptionC.dispose();
    }

    private void case2() {

        Observable<String> sourceObservable = Observable
                .interval(1, TimeUnit.SECONDS)
                .flatMap(id -> Observable.just("Request to API with userId = " + id))
                .doOnNext(this::print)
                .take(1)
                .share();

        Disposable subscriptionA = sourceObservable
                .subscribe(element -> {
                    // A Observing code
                });

        Disposable subscriptionB = sourceObservable
                .subscribe(element -> {
                    // B Observing code
                });

        Disposable subscriptionC = sourceObservable
                .subscribe(element -> {
                    // C Observing code
                });

        sleep(5000);
        compositeDisposable.clear();
        subscriptionA.dispose();
        subscriptionB.dispose();
        subscriptionC.dispose();
    }

    private void case3() {

        ConnectableObservable<String> sourceObservable = Observable
                .interval(1, TimeUnit.SECONDS)
                .flatMap(id -> Observable.just("Request to API with userId = " + id))
                .doOnNext(this::print)
                .take(1)
                .publish();

        Disposable subscriptionA = sourceObservable
                .subscribe(element -> {
                    // A Observing code
                });

        Disposable subscriptionB = sourceObservable
                .subscribe(element -> {
                    // B Observing code
                });

        Disposable subscriptionC = sourceObservable
                .subscribe(element -> {
                    // C Observing code
                });

        sourceObservable.connect();

        sleep(5000);
        subscriptionA.dispose();
        subscriptionB.dispose();
        subscriptionC.dispose();
    }

    private void case4() {

        Observable<String> observableA = Observable
                .interval(1, TimeUnit.SECONDS)
                .flatMap(id -> Observable.just("Request to API A with userId = " + id))
                .take(1);

        Observable<String> observableB = Observable
                .interval(1, TimeUnit.SECONDS)
                .flatMap(id -> Observable.just("Request to API B with userId = " + id))
                .take(1);

        Observable<String> observableC = Observable
                .interval(1, TimeUnit.SECONDS)
                .flatMap(id -> Observable.just("Request to API C with userId = " + id))
                .take(1);

        Disposable subscription = Observable
                .merge(observableA, observableB, observableC)
                .subscribe(this::print);

        sleep(5000);
        subscription.dispose();
    }

    private void print(String message) {
        Log.d(TAG, message);
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
