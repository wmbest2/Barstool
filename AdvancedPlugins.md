Advanced Plugin Usage
====

If your plugin contains injections, Dagger can take care of these when the
module is provided. See the example below to see how to create the plugin.

Create Plugin
----

```java
public class ThemePlugin implements Barstool.Plugin {
    @Inject @Themes String[] mOptions;

    public String getTitle() {
        return "Themes";
    }

    public View getView(Context aContext, ViewGroup aParent) {
        LayoutInflater inflater = LayoutInflater.from(aContext);
        
        // Create adapter with mOptions

        return infalter.inflate(R.layout.theme_picker, aParent, false);
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
    @Provides @Themes String[] options() {
        return new String[] {"red", "green", "blue"};
    }

    @Provides(type=SET) Barstool.Plugin getThemePlugin(ThemePlugin plugin) {
        return plugin;
    }
}
```
