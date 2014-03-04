package com.example.barstool.plugins;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dagger.ObjectGraph;

import barstool.Barstool;

import com.example.barstool.BuildConfig;

public class AppNamePlugin implements Barstool.Plugin {
    public String getTitle() {
        return "App Name";
    }

    public View getView(Context aContext, ViewGroup aParent) {
        TextView view = new TextView(aContext);
        view.setText(BuildConfig.PACKAGE_NAME);

        return view;
    }
}
