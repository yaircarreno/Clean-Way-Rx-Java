package com.clean.way.rx.items;

import android.util.Log;

import com.clean.way.rx.api.Cache;
import com.clean.way.rx.api.Network;
import com.clean.way.rx.models.Token;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class Item9 {

    public static String TAG = "Item9";
    private CompositeDisposable compositeDisposable;
    private Network network;
    private Cache cache;

    Token currentToken;

    public Item9(Network network, Cache cache) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        this.network = network;
        this.cache = cache;
    }

    public void itemExample(String apiKey) {
        this.case1(apiKey);
    }

    private void case1(String apiKey) {

        currentToken = new Token();

        this.network.getToken(apiKey)
                .subscribe(token ->
                        currentToken = token
                );

        if (currentToken.isValid()) {
            this.cache.storeToken(currentToken)
                    .subscribe(saved ->
                            Log.d(TAG, "Token stored: " + saved)
                    );
        }
    }

    private void case2(String apiKey) {
        compositeDisposable.add(
                this.network.getToken(apiKey)
                        .filter(Token::isValid)
                        .concatMap(token -> this.cache.storeToken(token))
                        .subscribe(saved ->
                                Log.d(TAG, "Token stored: " + saved)));
    }
}
