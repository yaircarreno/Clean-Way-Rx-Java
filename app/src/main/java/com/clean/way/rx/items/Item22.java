package com.clean.way.rx.items;

import android.util.Log;

import com.clean.way.rx.api.Cache;
import com.clean.way.rx.api.Network;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class Item22 {

    public static String TAG = "Item22";
    private CompositeDisposable compositeDisposable;
    private Network network;
    private Cache cache;

    public Item22(Network network, Cache cache) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        this.network = network;
        this.cache = cache;
    }

    public void itemExample(String username) {
        this.case1(username);
    }

    private void case1(String username) {

        compositeDisposable.add(
                this.network.getToken("apiKey")
                        .concatMap(token -> this.cache.storeToken(token))
                        .ignoreElements()
                        .andThen(this.network.getUser(username))
                        .subscribe(user -> Log.d(TAG, "User: " + user)));
    }
}
