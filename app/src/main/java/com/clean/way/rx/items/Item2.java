package com.clean.way.rx.items;

import android.util.Log;

import com.clean.way.rx.api.Network;
import com.clean.way.rx.models.User;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class Item2 {

    public static String TAG = "Item2";
    private CompositeDisposable compositeDisposable;
    private Network network;

    private User userGlobal;

    public Item2(Network network) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        this.network = network;
    }

    public void itemExample(String username) {
        this.case2(username);
    }

    private void case1(String username) {
        compositeDisposable.add(
                this.network.getUser(username)
                        .flatMap(user -> {
                            userGlobal = new User(user.getName(), user.getEmail(), user.getPosts());
                            return Observable.just(userGlobal);
                        })
                        .subscribe(user -> Log.d(TAG, "user: " + user)));
    }

    private void case2(String username) {

        compositeDisposable.add(
                this.network.getUser(username)
                        .subscribe(user -> {
                            userGlobal = user;
                            Log.d(TAG, "user: " + user);
                        }));
    }
}
