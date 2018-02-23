package com.suitepad.httpproxyserver;

import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.suitepad.httpproxyserver.model.MenuRepo;
import com.suitepad.httpproxyserver.model.MenuRepoImpl;

/**
 * Created by EslamHussein on 2/23/18.
 */

public class Injection {

    public static AsyncHttpServer provideAsyncHttpServer() {
        return new AsyncHttpServer();
    }

    public static AsyncServer provideAsyncServer() {
        return new AsyncServer();
    }

    public static MenuRepo provideMenuRepo() {

        return new MenuRepoImpl();
    }
}
