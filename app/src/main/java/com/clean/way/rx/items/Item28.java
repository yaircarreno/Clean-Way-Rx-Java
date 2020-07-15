package com.clean.way.rx.items;

import android.util.Log;

import com.clean.way.rx.api.Cache;
import com.clean.way.rx.api.Network;
import com.clean.way.rx.models.User;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class Item28 {

    public static String TAG = "Item28";
    private CompositeDisposable compositeDisposable;
    private Network network;
    private Cache cache;

    public Item28(Network network, Cache cache) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        this.network = network;
        this.cache = cache;
    }

    public void itemExample(String username) {
        this.case5(username);
    }

    private void case1(String username) {

        compositeDisposable.add(
                this.network.getToken("api-key")
                        .concatMap(token -> cache.storeTokenWithError(token))
                        .concatMap(saved -> network.getUser(username))
                        .subscribe(element -> Log.d(TAG, "" + element),
                                throwable -> Log.e(TAG, "error:" + throwable),
                                () -> Log.d(TAG, "completed")));
    }

    private void case2(String username) {

        compositeDisposable.add(
                this.network.getToken("api-key")
                        .concatMap(token -> cache.storeTokenWithError(token))
                        .concatMap(saved -> network.getUser(username))
                        .onErrorResumeNext(throwable -> Observable.just(new User()))
                        .subscribe(user -> Log.d(TAG, "next: " + user),
                                throwable -> Log.e(TAG, "error: " + throwable),
                                () -> Log.d(TAG, "completed")));
    }

    private void case3(String username) {

        compositeDisposable.add(
                this.network.getToken("api-key")
                        .concatMap(token -> cache.storeTokenWithError(token))
                        .concatMap(saved -> network.getUser(username))
                        .onErrorResumeNext(throwable -> {
                            Log.d(TAG, Objects.requireNonNull(throwable.getMessage()));
                            return Observable.empty();
                        })
                        .subscribe(user -> Log.d(TAG, "next: " + user),
                                throwable -> Log.e(TAG, "error: " + throwable),
                                () -> Log.d(TAG, "completed")));
    }

    private void case4(String username) {

        compositeDisposable.add(
                this.network.getToken("api-key")
                        .concatMap(token -> cache.storeTokenWithError(token))
                        .concatMap(saved -> network.getUser(username))
                        .retry(2)
                        .onErrorResumeNext(throwable -> {
                            Log.d(TAG, Objects.requireNonNull(throwable.getMessage()));
                            return Observable.empty();
                        })
                        .subscribe(user -> Log.d(TAG, "next: " + user),
                                throwable -> Log.e(TAG, "error: " + throwable),
                                () -> Log.d(TAG, "completed")));
    }

    private void case5(String username) {

        compositeDisposable.add(
                this.network.getToken("api-key")
                        .concatMap(token -> cache.storeTokenWithError(token))
                        .concatMap(saved -> network.getUser(username))
                        .retryWhen(errors -> errors
                                .doOnNext(ignored -> Log.d(TAG, "retrying..."))
                                .delay(2, TimeUnit.SECONDS)
                                .take(4)
                                .concatWith(Observable.error(new Throwable())))
                        .onErrorResumeNext(throwable -> {
                            Log.d(TAG, Objects.requireNonNull(throwable.getMessage()));
                            return Observable.empty();
                        })
                        .subscribe(user -> Log.d(TAG, "next: " + user),
                                throwable -> Log.e(TAG, "error: " + throwable),
                                () -> Log.d(TAG, "completed")));
    }
}
