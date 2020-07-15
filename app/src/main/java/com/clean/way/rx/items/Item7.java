package com.clean.way.rx.items;

import android.util.Log;

import com.clean.way.rx.api.Cache;
import com.clean.way.rx.api.Network;
import com.clean.way.rx.models.Token;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class Item7 {

    public static String TAG = "Item7";
    private CompositeDisposable compositeDisposable;
    private Network network;
    private Cache cache;

    public Item7(Network network, Cache cache) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        this.network = network;
        this.cache = cache;
    }

    public void itemExample(String apiKey) {
        this.case3(apiKey);
    }

    private void case1(String apiKey) {

        this.network.getToken(apiKey)
                .subscribe(token -> {
                    if (token.isValid()) {
                        this.cache.storeToken(token)
                                .subscribe(saved ->
                                        Log.d(TAG, "Token stored: " + saved)
                                );
                    }
                });
    }

    private void case2(String apiKey) {
        compositeDisposable.add(
                this.network.getToken(apiKey)
                        .filter(Token::isValid)
                        .concatMap(token -> this.cache.storeToken(token))
                        .subscribe(saved ->
                                Log.d(TAG, "Token stored: " + saved)));
    }

    private void case3(String apiKey) {

        PublishSubject<String> oneSubject = PublishSubject.create();
        PublishSubject<String> twoSubject = PublishSubject.create();

        oneSubject
                .subscribe(item -> Log.d(TAG, "" + item));

        twoSubject
                .subscribe(item -> oneSubject.onNext(item));

        twoSubject.onNext("A");
        twoSubject.onNext("B");
        twoSubject.onNext("C");
    }
}
