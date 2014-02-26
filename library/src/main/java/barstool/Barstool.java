package barstool;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Set;
import java.lang.reflect.Field;

import dagger.ObjectGraph;

import javax.inject.Inject;

public class Barstool {

    private ObjectGraph mGraph;
    private int mTitle = -1;
    private int mBackground = -1;

    private static boolean isDebug(Context aContext) {
        boolean result = false;
        try {
            Class<?> bc = aContext.getClassLoader().loadClass(aContext.getPackageName() + ".BuildConfig");
            Field debug = bc.getDeclaredField("DEBUG");
            result = debug.getBoolean(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Barstool empty() {
        return new Barstool();        
    }

    public static Barstool with(ObjectGraph aGraph) {
        return new Barstool(aGraph);
    }

    @Inject Barstool() {

    }

    private Barstool(ObjectGraph aGraph) {
        mGraph = aGraph;
    }

    public Barstool titleAppearance(int aRes) {
        mTitle = aRes;
        return this;
    }

    public Barstool background(int aRes) {
        mBackground = aRes;
        return this;
    }

    public Barstool graph(ObjectGraph aGraph) {
        mGraph = aGraph;
        return this;
    }

    public ListView asListView(Context aContext) {
        return build(aContext, null, false);
    }

    public Dialog asDialog() {
        return null;
    }

    public void wrap(Activity aActivity) {
        wrap(aActivity, isDebug(aActivity));
    }

    public void wrap(Activity aActivity, boolean aShow) {
        if (!aShow) return;
        ViewGroup root = (ViewGroup) aActivity.findViewById(android.R.id.content);
        View w = root.getChildAt(0);

        if (!(w instanceof DrawerLayout)) {
            DrawerLayout layout = new DrawerLayout(w.getContext());
            root.removeViewAt(0);
            layout.addView(w);
            w = layout;
            root.addView(w, 0);
        }

        into((DrawerLayout) w, aShow);
    }

    public void into(ViewGroup aView) {
        into(aView, isDebug(aView.getContext()));
    }

    public void into(ViewGroup aView, boolean aShow) {
        if (!aShow) return;
        if (aView == null) return;

        Context context = aView.getContext();
        build(context, aView, true);
    }

    private ListView build(Context aContext, ViewGroup aRoot, boolean aAdd) {
        if (mGraph == null)
            throw new UnsupportedOperationException("You must call .graph(ObjectGraph) for barstool to work");
        LayoutInflater in = LayoutInflater.from(aContext);

        View aView = in.inflate(R.layout.barstool_list, aRoot, aAdd);
        ListView list = (ListView) aView.findViewById(R.id.right_drawer);
        
        PluginAdapter pa = new PluginAdapter(aContext, mGraph);

        if (mTitle != -1) {
            pa.setTitleStyle(mTitle);
        }

        if (mBackground != -1) {
            aRoot.setBackgroundResource(mBackground);
        }

        list.setAdapter(pa);
        return list;
    }

    public static class PluginAdapter extends ArrayAdapter<Plugin> {
        int mTitleStyle = -1;
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

        public void setTitleStyle(int aStyle) {
            mTitleStyle = aStyle;
        }

        public View getView(int aPos, View aConvert, ViewGroup aParent) {
            View aRoot = mInflater.inflate(R.layout.barstool_item, aParent, false);
            Plugin plugin = getItem(aPos);

            TextView title = (TextView) aRoot.findViewById(R.id.title);
            title.setText(plugin.getTitle());

            if (mTitleStyle > 0) {
                title.setTextAppearance(title.getContext(), mTitleStyle);
            }

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

