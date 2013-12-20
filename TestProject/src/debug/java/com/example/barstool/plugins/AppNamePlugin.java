package com.example.barstool.plugins;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import dagger.ObjectGraph;

import com.wmbest.barstool.Barstool;

public class AppNamePlugin implements Barstool.Plugin {
    public String getTitle() {
        return "App Name";
    }

    public View getView(Context aContext) {
        return new TextView(aContext);
    }

    public void bindView(View aView, ObjectGraph aGraph) {
        ((TextView) aView).setText("Bill's Awesome Test!");
    }
}
