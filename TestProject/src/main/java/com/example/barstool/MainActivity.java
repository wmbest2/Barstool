package com.example.barstool;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.*;

import com.wmbest.barstool.Barstool;

public class MainActivity extends ActionBarActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle aBundle) {
        super.onCreate(aBundle);
        setContentView(R.layout.main_activity);

        if (BuildConfig.DEBUG) {
            Barstool.setup(((CustomApp) getApplication()).getOG(), 
                (DrawerLayout) findViewById(R.id.drawer));
        }
    }
}
