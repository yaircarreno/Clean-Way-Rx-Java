package com.clean.way.rx.items;

import android.util.Log;
import com.clean.way.rx.api.Network;
import com.clean.way.rx.models.Token;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class Item25 {

    public static String TAG = "Item25";
    private CompositeDisposable compositeDisposable;
    private Network network;

    public Item25(Network network) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        this.network = network;
    }

    public void itemExample(String username) {
        this.case1(username);
    }

    private void case1(String username) {

        Observable<Token> tokenObservable = network.getToken("api-key");

        compositeDisposable.add(tokenObservable
                .map(Token::getValue)
                .subscribe(this::setupRequest));
    }

    public void case2() {

        Observable<Token> tokenObservable = network.getToken("api-key");

        compositeDisposable.add(tokenObservable
                .subscribe(token ->
                        setupRequest(token.getValue())));
    }

    private void setupRequest(String token) {
        Log.d(TAG, token);
    }
}
