package com.clean.way.rx.items;

import android.util.Log;

import com.clean.way.rx.api.Network;
import com.clean.way.rx.models.Token;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class Item11 {

    public static String TAG = "Item11";
    private CompositeDisposable compositeDisposable;
    private Network network;

    public Item11(Network network) {
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
                        .map(Token::getValue)
                        .subscribe(value ->
                                Log.d(TAG, "Token's value: " + value)));
    }

    private void case2(String apiKey) {
        compositeDisposable.add(
                this.network.getToken(apiKey)
                        .subscribe(token -> {
                            Log.d(TAG, "Token's value: " + token.getValue());
                            Log.d(TAG, "Token's state: " + token.isValid());
                        }));
    }
}
