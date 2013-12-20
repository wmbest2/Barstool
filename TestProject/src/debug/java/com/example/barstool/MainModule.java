package com.example.barstool;

import com.example.barstool.plugins.*;

import com.wmbest.barstool.Barstool;

import dagger.Module;
import dagger.Provides;
import static dagger.Provides.Type.SET;
@Module(includes=com.wmbest.barstool.BarstoolModule.class)
public class MainModule {
    @Provides(type=SET) Barstool.Plugin getAppNamePlugin() {
        return new AppNamePlugin();
    }
    @Provides(type=SET) Barstool.Plugin somethingElse() {
        return new AppNamePlugin();
    }
    @Provides(type=SET) Barstool.Plugin stuff() {
        return new AppNamePlugin();
    }
    @Provides(type=SET) Barstool.Plugin whatsup() {
        return new AppNamePlugin();
    }
}
