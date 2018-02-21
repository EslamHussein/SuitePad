package com.suitepad.presentation;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;

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
        startServer();
    }

    private void startServer(){

        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.suitepad.httpproxyserver", "com.suitepad.httpproxyserver.HTTPProxyService"));
        startService(intent);


    }


}
