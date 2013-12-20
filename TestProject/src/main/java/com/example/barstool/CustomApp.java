package com.example.barstool;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import dagger.*;

public class CustomApp extends Application {

    ObjectGraph mOG;

    @Override
    public void onCreate() {
        super.onCreate();

        mOG = ObjectGraph.create(new MainModule());
    }

    public ObjectGraph getOG() {
        return mOG;
    }
}
