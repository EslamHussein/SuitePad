package com.suitepad.httpproxyserver;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.suitepad.httpproxyserver.presenter.ServerPresenter;
import com.suitepad.httpproxyserver.presenter.ServerPresenterImpl;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class HTTPProxyService extends Service {

    private static final String TAG = HTTPProxyService.class.getName();


    private ServerPresenter serverPresenter;


    @Override
    public void onCreate() {
        super.onCreate();
        serverPresenter = new ServerPresenterImpl();
        serverPresenter.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        serverPresenter.startServer();
        return Service.START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        serverPresenter.onDestroy();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }




}
