package com.clean.way.rx;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class TakeSubscriptionsActivity extends AppCompatActivity {

    public static String TAG = "TakeSubscriptionsActivity";
    private Subject<Boolean> cancel = PublishSubject.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_subscriptions);

        Observable.interval(1, TimeUnit.SECONDS)
                .takeUntil(cancel)
                .subscribe(element -> Log.d(TAG, "" + element));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancel.onNext(true);
        cancel.onComplete();
    }
}