package com.clean.way.rx;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class SubscriptionsActivity extends AppCompatActivity {

    public static String TAG = "SubscriptionsActivity";
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscriptions);

        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }

        compositeDisposable.add(
                Observable.interval(1, TimeUnit.SECONDS)
                        .subscribe(element -> Log.d(TAG, "" + element)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}