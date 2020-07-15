package com.clean.way.rx.items;

import android.util.Log;
import android.util.Pair;

import com.clean.way.rx.api.Cache;
import com.clean.way.rx.api.Network;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class Item5 {

    public static String TAG = "Item5";
    private CompositeDisposable compositeDisposable;
    private Network network;
    private Cache cache;

    public Item5(Network network, Cache cache) {
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
                        .concatMap(token -> cache.storeToken(token)
                                .concatMap(saved -> network.getUser(username)))
                        .subscribe(user -> Log.d(TAG, "User: " + user)));
    }

    private void case2(String username) {

        compositeDisposable.add(
                this.network.getToken("api-key")
                        .concatMap(token -> cache.storeToken(token))
                        .concatMap(saved -> network.getUser(username))
                        .subscribe(user -> Log.d(TAG, "User: " + user)));
    }

    private void case3(String username) {

        compositeDisposable.add(
                this.network.getToken("api-key")
                        .concatMap(token -> cache.storeToken(token)
                                .concatMap(saved -> network.getUser(username, token)))
                        .subscribe(user -> Log.d(TAG, "User: " + user)));
    }

    private void case4(String username) {

        compositeDisposable.add(
                this.network.getToken("api-key")
                        .concatMap(token -> cache.storeToken(token)
                                .map(saved -> new Pair<>(token, saved)))
                        .concatMap(pair -> network.getUser(username, pair.first))
                        .subscribe(user -> Log.d(TAG, "User: " + user)));
    }

    private void case5(String username) {

        compositeDisposable.add(
                this.network.getToken("api-key")
                        .concatMap(token -> cache.storeToken(token))
                        .ignoreElements()
                        .andThen(network.getUser(username))
                        .subscribe(user -> Log.d(TAG, "User: " + user)));
    }
}
