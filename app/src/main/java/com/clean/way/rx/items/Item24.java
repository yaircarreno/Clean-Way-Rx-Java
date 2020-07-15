package com.clean.way.rx.items;

import android.util.Log;
import com.clean.way.rx.api.Network;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class Item24 {

    public static String TAG = "Item24";
    private CompositeDisposable compositeDisposable;
    private Network network;

    private boolean loggedIn = false;

    public Item24(Network network) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        this.network = network;
    }

    public void itemExample(String username) {
        this.case1(username);
    }

    private void case1(String username) {

        compositeDisposable.add(
                this.network.getUser(username)
                        .doOnNext(this::logger)
                        .subscribe(data -> Log.d(TAG, "next: " + data)));
    }

    private void case2(String username) {
        compositeDisposable.add(
                this.network.getUser(username)
                        .doOnNext(this::loggerWithSideEffect)
                        .subscribe(user -> {
                            if (!user.getName().isEmpty() && loggedIn) {
                                // Do anything
                            }
                        }));
    }

    private void logger(Object object) {
        Log.d(TAG, object.toString());
    }

    private void loggerWithSideEffect(Object object) {
        Log.d(TAG, object.toString());
        loggedIn = true;
    }
}
