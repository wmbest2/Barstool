package com.example.barstool;

import com.example.barstool.plugins.*;

import barstool.*;

import dagger.Module;
import dagger.Provides;
import static dagger.Provides.Type.SET;
@Module(
    injects=MainActivity.class,
    includes=BarstoolModule.class
)
public class MainModule {
    @Provides(type=SET) Barstool.Plugin getAppNamePlugin() {
        return new AppNamePlugin();
    }
    @Provides(type=SET) Barstool.Plugin getNetworkPlugin() {
        return new NetworkPlugin();
    }
}
