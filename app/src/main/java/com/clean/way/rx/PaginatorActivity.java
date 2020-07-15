package com.clean.way.rx;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.clean.way.rx.models.Paginator;

public class PaginatorActivity extends AppCompatActivity {

    public static String TAG = "PaginatorActivity";

    private Paginator paginator;
    private CompositeDisposable compositeDisposable;

    @BindView(R.id.results_text)
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paginator);
        ButterKnife.bind(this);
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        bindPaginator();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void bindPaginator() {
        this.paginator = new Paginator();

        compositeDisposable.add(
                this.paginator.getResults("Query 1")
                        .map(result -> "Updating the list with => " + result)
                        .subscribe(this::showResults));

        compositeDisposable.add(
                this.paginator.getResults("Query 2")
                        .map(result -> "Hiding the loader after getting => " + result)
                        .subscribe(this::showResults));

        compositeDisposable.add(
                this.paginator.getResults("Query 3")
                        .map(result -> "Doing any other update with => " + result)
                        .subscribe(this::showResults));

    }

    private void queryData(Integer page) {
        this.paginator.pushPage(page);
    }

    private void showResults(String result) {
        Log.d(TAG, result);
        resultTextView.setText(result);
    }

    @OnClick(R.id.load_initial)
    void initialLoad() {
        this.queryData(1);
    }

    @OnClick(R.id.load_by_scrolling)
    void scrollingLoad() {
        this.queryData(5);
    }

    @OnClick(R.id.load_manually)
    void manuallyLoad() {
        this.queryData(25);
    }
}