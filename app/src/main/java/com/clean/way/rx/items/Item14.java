package com.clean.way.rx.items;

import android.util.Log;

import com.clean.way.rx.api.Network;
import com.clean.way.rx.models.Token;

import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class Item14 {

    public static String TAG = "Item14";
    private CompositeDisposable compositeDisposable;
    private Network network;

    public Item14(Network network) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        this.network = network;
    }

    public void itemExample(String apiKey) {
        this.case2(apiKey);
    }

    private void case1(String apiKey) {
        compositeDisposable.add(
                this.network.getToken(apiKey)
                        .subscribe(token -> {
                            if (token.isValid()) {
                                // First condition
                            } else {
                                // Second condition
                            }
                        }));

    }

    private void case2(String apiKey) {

        Observable<Notification<Token>> valid = this.network.getToken(apiKey)
                .flatMap(token -> verifyToken(token).materialize());

        compositeDisposable.add(
                valid.filter(Notification::isOnNext)
                        .map(Notification::getValue)
                        .subscribe(data -> Log.d(TAG, "next: " + data),
                                throwable -> Log.e(TAG, "error: " + throwable),
                                () -> Log.d(TAG, "completed")));

        compositeDisposable.add(
                valid.filter(Notification::isOnError)
                        .map(Notification::getError)
                        .subscribe(data -> Log.d(TAG, "next: " + data),
                                throwable -> Log.e(TAG, "error: " + throwable),
                                () -> Log.d(TAG, "completed")));

    }

    private Observable<Token> verifyToken(Token token) {
        if (token.isValid()) {
            return Observable.just(token);
        } else {
            return Observable.error(new Throwable());
        }
    }
}
