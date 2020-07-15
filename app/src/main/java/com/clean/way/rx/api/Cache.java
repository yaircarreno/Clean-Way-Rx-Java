package com.clean.way.rx.api;

import com.clean.way.rx.models.Token;

import io.reactivex.rxjava3.core.Observable;

public class Cache {

    public Observable<Boolean> storeToken(Token token) {
        return Observable.just(true);
    }

    public Observable<Boolean> storeTokenWithError(Token token) {
        return Observable.error(() -> {
            throw new Exception("SampleError!");
        });
    }
}
