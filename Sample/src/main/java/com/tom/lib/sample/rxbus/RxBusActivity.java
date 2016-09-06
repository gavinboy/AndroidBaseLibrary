package com.tom.lib.sample.rxbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tom.lib.sample.R;

public class RxBusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bus);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.subscribe,SubscriberFragment.newInstance())
                .add(R.id.observable,ObservableFragment.newInstance())
                .commit();
    }
}
