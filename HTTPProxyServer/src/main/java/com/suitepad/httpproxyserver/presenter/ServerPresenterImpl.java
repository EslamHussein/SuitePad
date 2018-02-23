package com.suitepad.httpproxyserver.presenter;


import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.HttpServerRequestCallback;
import com.suitepad.httpproxyserver.model.MenuRepo;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by EslamHussein on 2/21/18.
 */

public class ServerPresenterImpl implements ServerPresenter, HttpServerRequestCallback {

    private static final String TAG = ServerPresenterImpl.class.getSimpleName();
    private AsyncHttpServer server = new AsyncHttpServer();
    private AsyncServer mAsyncServer = new AsyncServer();
    private CompositeDisposable compositeDisposable;
    private MenuRepo menuRepo;


    public ServerPresenterImpl(AsyncHttpServer server, AsyncServer mAsyncServer, CompositeDisposable compositeDisposable, MenuRepo menuRepo) {
        this.server = server;
        this.mAsyncServer = mAsyncServer;
        this.compositeDisposable = compositeDisposable;
        this.menuRepo = menuRepo;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void startServer() {
        server.get("/file.json", this);
        server.listen(mAsyncServer, 8080);

    }

    @Override
    public void onDestroy() {
        server.stop();
        mAsyncServer.stop();
        compositeDisposable.clear();
    }

    @Override
    public void onRequest(AsyncHttpServerRequest request, final AsyncHttpServerResponse response) {

        compositeDisposable.add(menuRepo.getMenuItems().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        response.send(s);

                    }

                    @Override
                    public void onError(Throwable e) {

                        response.send("[]"); // return empty  list

                    }

                    @Override
                    public void onComplete() {
                    }
                }));

    }
}
