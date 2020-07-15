package com.clean.way.rx.items;

import android.util.Log;

import com.clean.way.rx.api.Network;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class Item17 {

    public static String TAG = "Item17";
    private CompositeDisposable compositeDisposable;
    private Network network;

    public Item17(Network network) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        this.network = network;
    }

    public void itemExample(String apiKey) {
        this.case1(apiKey);
    }

    private void case1(String apiKey) {
        compositeDisposable.add(
                this.network.getToken(apiKey)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(token -> Log.d(TAG, "" + token)));
    }
}
