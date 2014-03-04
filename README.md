Barstool
========

A pluggable debug toolbar for android using Square's Dagger library

![example](http://i.imgur.com/YnbMe7ul.png)

Inspiration
----

After seeing the Debug toolbar, used in Square's apps, along with the
utility of the Django debug toolbar, I felt that there was a need for a simple
way to create a debug interface in a modular, pluggable way.

Usage
=====

Limitations
----

Barstool takes a android.support.v4.widget.DrawerLayout or will wrap an Activity.

Barstool will only inject the drawer if BuildConfig.DEBUG == true


Create Plugin
----

```java
public class ThemePlugin implements Barstool.Plugin {
    public String getTitle() {
        return "Themes";
    }

    public View getView(Context aContext) {
        LayoutInflater inflater = LayoutInflater.from(aContext);
        return infalter.inflate(R.layout.theme_picker, null);
    }
}
```

Inject Plugin
----

```java
@Module(
    injects=MyActivity.class,
    includes=BarstoolModule.class
)
public class MyModule {
    @Provides(type=SET) Barstool.Plugin getThemePlugin() {
        return new ThemePlugin();
    }
}
```

For Injecting members using the current ObjectGraph see [here](AdvancedPlugins.md)

Load Barstool
----

```java
    ObjectGraph og = ((MyApp) getApplication()).getObjectGraph();
    Barstool.with(og).wrap(myActivity); 
```

Need to make your life easier? 
Inject a Barstool object!

```java
    // In @Module
    @Provides Barstool provideBuilder() {
        return Barstool.empty()
            .titleAppearance(R.style.title_appearance)
            .background(R.drawable.my_nine)
    }

    // In Activity

    @Inject Barstool mBarstool

    public void onCreate(Bundle aBundle) {
        ...

        mBarstool.graph(getOG()).wrap(this);
    }
```
