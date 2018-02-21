package com.suitepad.httpproxyserver;

import android.app.Application;

/**
 * Created by EslamHussein on 2/21/18.
 */

public class App extends Application {


    private static App instance;

    public static App get() {
        if (instance == null)
            throw new IllegalStateException("Something went horribly wrong!!, no application attached!");
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}
