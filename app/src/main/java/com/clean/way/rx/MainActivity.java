package com.clean.way.rx;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;
import android.content.Intent;
import android.os.Bundle;
import com.clean.way.rx.api.Cache;
import com.clean.way.rx.api.Network;
import com.clean.way.rx.items.Item29;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        final Network network = new Network();
        final Cache cache = new Cache();

        Item29 item = new Item29();
        item.itemExample();
    }

    @OnClick(R.id.example_1_button) void showPaginatorExample() {
        Intent intent = new Intent(this, PaginatorActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.example_2_button) void showSubscriptionsExample() {
        Intent intent = new Intent(this, SubscriptionsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.example_3_button) void showSubscriptionsWithTakeExample() {
        Intent intent = new Intent(this, TakeSubscriptionsActivity.class);
        startActivity(intent);
    }
}