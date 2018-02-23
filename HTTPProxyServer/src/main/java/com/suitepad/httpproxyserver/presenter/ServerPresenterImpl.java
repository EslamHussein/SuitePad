package com.suitepad.httpproxyserver.presenter;

import android.util.Log;

import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.HttpServerRequestCallback;
import com.suitepad.httpproxyserver.model.MenuRepo;
import com.suitepad.httpproxyserver.model.MenuRepoImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by EslamHussein on 2/21/18.
 */

public class ServerPresenterImpl implements ServerPresenter, HttpServerRequestCallback {

    private AsyncHttpServer server = new AsyncHttpServer();
    private AsyncServer mAsyncServer = new AsyncServer();

    private static final String TAG = ServerPresenterImpl.class.getSimpleName();

    private CompositeDisposable compositeDisposable;
    private MenuRepo menuRepo;

    @Override
    public void onCreate() {
        compositeDisposable = new CompositeDisposable();
        menuRepo = new MenuRepoImpl();

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
