package com.clean.way.rx.items;

import android.util.Log;
import com.clean.way.rx.api.Network;
import com.clean.way.rx.models.Token;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class Item13 {

    public static String TAG = "Item13";
    private CompositeDisposable compositeDisposable;
    private Network network;

    public Item13(Network network) {
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
                        .subscribe(token -> {
                            if (token.isValid()) {
                                Log.d(TAG, "" + token);
                            }
                        }));
    }

    private void case2(String apiKey) {
        compositeDisposable.add(
                this.network.getToken(apiKey)
                        .filter(Token::isValid)
                        .subscribe(token -> {
                            Log.d(TAG, "" + token);
                        }));
    }

    private void case3(String apiKey) {
        compositeDisposable.add(
                this.network.getToken(apiKey)
                        .subscribe(token -> {
                            if (token.isValid()) {
                                Log.d(TAG, "" + token);
                            } else {
                                //Token is not valid
                            }
                        }));
    }
}
