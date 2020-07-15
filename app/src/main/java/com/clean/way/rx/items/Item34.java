package com.clean.way.rx.items;

import android.util.Log;

import java.util.Objects;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class Item34 {

    public static String TAG = "Item34";
    private CompositeDisposable compositeDisposable;

    public Item34() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
    }

    public void itemExample() {
        this.case1();
    }

    private void case1() {

        Disposable subscription = Observable.just("Any value emitted!")
                .subscribe(item -> Log.d(TAG, "next: " + item),
                        throwable -> Log.e(TAG, "error: " + throwable),
                        () -> Log.d(TAG, "completed"));

        sleep(5000);
        subscription.dispose();

    }

    private Observable<Integer> wrongNumberEmitter() {

        return Observable.create(emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
        });
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
