package com.example.barstool.plugins;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import barstool.Barstool;
import dagger.ObjectGraph;

public class NetworkPlugin implements Barstool.Plugin {
    public String getTitle() {
        return "Network Settings";
    }

    public View getView(Context aContext, ViewGroup aParent) {
        LayoutInflater inflater = LayoutInflater.from(aContext);
        View view = inflater.inflate(com.example.barstool.R.layout.network_debug, aParent, false);

        // Something fun

        return view;
    }
}
