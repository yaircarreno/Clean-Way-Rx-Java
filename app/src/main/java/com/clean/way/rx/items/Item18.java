package com.clean.way.rx.items;

import android.util.Log;
import com.clean.way.rx.api.Network;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class Item18 {

    public static String TAG = "Item18";
    private CompositeDisposable compositeDisposable;
    private Network network;

    public Item18(Network network) {
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
                        .filter(user -> !user.getName().isEmpty())
                        .subscribe(data -> Log.d(TAG, "next: " + data),
                                throwable -> Log.e(TAG, "error: " + throwable),
                                () -> Log.d(TAG, "completed")));
    }
}
