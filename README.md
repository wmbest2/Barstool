Barstool
========

A pluggable debug toolbar for android using Square's Dagger library

Inspiration
----

After seeing the Debug toolbar, used in Square's apps, along with the
utility of the Django debug toolbar, I felt that there was a need for a simple
way to create a debug interface in a modular, pluggable way.

Usage
=====

Limitations
----

Barstool takes a android.support.v4.widget.DrawerLayout as a parameter.
This is to inject the Toolbar view and is a requirement I'd like to 
remove but for now it is necessary.

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

    public void bindView(View aView, ObjectGraph aGraph) {
        ...
    }
}
```

Inject Plugin
----

Load Barstool
----

```java
    ObjectGraph og = ((MyApp) getApplication()).getObjectGraph();
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
    Barstool.setup(og, drawer); 
```
