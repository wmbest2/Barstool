package com.wmbest.barstool;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Set;

import dagger.ObjectGraph;

import javax.inject.Inject;

public class Barstool {

    public static void setup(ObjectGraph aGraph, DrawerLayout aViewRoot) {
        Context context = aViewRoot.getContext();
        LayoutInflater in = LayoutInflater.from(context);
        in.inflate(R.layout.barstool_list, aViewRoot, true);
        ListView aView = (ListView) aViewRoot.findViewById(R.id.right_drawer);
        aView.setAdapter(new PluginAdapter(context, aGraph));
    }
    
    public static class PluginAdapter extends ArrayAdapter<Plugin> {
        ObjectGraph mGraph;
        LayoutInflater mInflater;
        @Inject public Set<Plugin> mPlugins;

        public PluginAdapter(Context aContext, ObjectGraph aGraph) {
            super(aContext, -1);
            mInflater = LayoutInflater.from(aContext);
            mGraph = aGraph;
            mGraph.inject(this);

            addAll(mPlugins.toArray(new Plugin[mPlugins.size()]));
        }

        public View getView(int aPos, View aConvert, ViewGroup aParent) {
            View aRoot = mInflater.inflate(R.layout.barstool_item, aParent, false);
            Plugin plugin = getItem(aPos);

            TextView title = (TextView) aRoot.findViewById(R.id.title);
            title.setText(plugin.getTitle());

            View pluginView = plugin.getView(aRoot.getContext());
            plugin.bindView(pluginView, mGraph);

            ((ViewGroup) aRoot).addView(pluginView);
            return aRoot;
        }
    }

    public interface Plugin {
        public String getTitle();
        public View getView(Context aContext);
        public void bindView(View aView, ObjectGraph aObject);
    }
}
