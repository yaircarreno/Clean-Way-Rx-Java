package com.clean.way.rx.items;

import android.util.Log;

import com.clean.way.rx.api.Network;
import com.clean.way.rx.models.User;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class Item6 {

    public static String TAG = "Item6";
    private CompositeDisposable compositeDisposable;
    private Network network;

    private boolean loggedIn = false;
    private boolean anyState = false;

    public Item6(Network network) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        this.network = network;
    }

    public void itemExample(String username) {
        this.case1(username);
    }

    private void case1(String username) {

        // Use verifyUser(user, true) to simulate error.

        compositeDisposable.add(
                this.network.getUser(username)
                        .doOnNext(user -> loggedIn = true)
                        .flatMap(user -> verifyUser(user, false))
                        .subscribe(user -> {
                                    Log.d(TAG, "" + user);
                                    Log.d(TAG, "" + loggedIn);
                                }, throwable -> {
                                    Log.e(TAG, "error: " + throwable);
                                    Log.d(TAG, "" + loggedIn);
                                },
                                () -> Log.d(TAG, "completed")));
    }

    private void case2(String username) {
        compositeDisposable.add(
                this.network.getUser(username)
                        .flatMap(user -> verifyUser(user, false))
                        .subscribe(user -> {
                                    loggedIn = true;
                                    Log.d(TAG, "" + user);
                                    Log.d(TAG, "" + loggedIn);
                                }, throwable -> {
                                    Log.e(TAG, "error: " + throwable);
                                    Log.d(TAG, "" + loggedIn);
                                },
                                () -> Log.d(TAG, "completed")));
    }

    private void case3(String username) {
        compositeDisposable.add(
                this.network.getUser(username)
                        .filter(user -> !user.getName().isEmpty() && anyState)
                        .subscribe(user -> {
                                    loggedIn = true;
                                    Log.d(TAG, "" + user);
                                }, throwable -> {
                                    Log.e(TAG, "error: " + throwable);
                                },
                                () -> Log.d(TAG, "completed")));
    }

    private void case4(final String username, final boolean state) {
        compositeDisposable.add(
                this.network.getUser(username)
                        .filter(user -> !user.getName().isEmpty() && state)
                        .subscribe(user -> {
                                    loggedIn = true;
                                    Log.d(TAG, "" + user);
                                }, throwable -> {
                                    Log.e(TAG, "error: " + throwable);
                                },
                                () -> Log.d(TAG, "completed")));
    }

    private Observable<User> verifyUser(final User user, final boolean isSimulatedError) {

        return Observable.create(emitter -> {
            if (isSimulatedError) {
                emitter.onError(new Throwable());
            }
            emitter.onNext(user);
            emitter.onComplete();
        });
    }
}
