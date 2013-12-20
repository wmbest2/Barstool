package com.wmbest.barstool;

import dagger.Module;
import dagger.Provides;

import java.util.Set;
import static java.util.Collections.emptySet;
import static dagger.Provides.Type.SET_VALUES;

@Module(injects = Barstool.PluginAdapter.class, library=true)
public class BarstoolModule {
    @Provides(type=SET_VALUES) Set<Barstool.Plugin> provideDefault() {
        return emptySet();
    }
}
