package com.example.barstool;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.*;

import javax.inject.Inject;

import barstool.Barstool;

import com.android.volley.RequestQueue;
import com.vokal.volley.BaseUrl;
import com.vokal.volley.VolleyBall.ServerChanger;

public class MainActivity extends ActionBarActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle aBundle) {
        super.onCreate(aBundle);
        setContentView(R.layout.main_activity);

        ((CustomApp) getApplication()).getOG().inject(this);
        Barstool.with(((CustomApp) getApplication()).getOG()).wrap(this);
    }
}
