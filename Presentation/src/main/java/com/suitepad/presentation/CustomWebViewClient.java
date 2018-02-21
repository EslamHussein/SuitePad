package com.suitepad.presentation;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.telecom.Call;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by EslamHussein on 2/21/18.
 */

public class CustomWebViewClient extends WebViewClient {


    private static final String REMOTE_DATA_SOURCE_URL = "http://someremoteurl.com/sample.json";

    private static final String LOCAL_DATA_SOURCE_URL = "http://localhost:8080/file.json";


    private Context context;

    public CustomWebViewClient(Context context) {
        this.context = context;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {

        String url = request.getUrl().toString();
        WebResourceResponse response = super.shouldInterceptRequest(view, request);

        if (url.contains(REMOTE_DATA_SOURCE_URL) && response == null) {
            try {

                return handleRequest(LOCAL_DATA_SOURCE_URL);
            } catch (Exception e) {
                e.printStackTrace();
                return response;
            }
        }

        return response;
    }


    @SuppressWarnings("deprecation") // From API 21 we should use another overload
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        WebResourceResponse response = super.shouldInterceptRequest(view, url);

        if (url.contains(REMOTE_DATA_SOURCE_URL) && response == null) {
            try {
                return handleRequest(LOCAL_DATA_SOURCE_URL);
            } catch (IOException e) {
                e.printStackTrace();
                return response;
            }
        }
        return response;
    }


    @NonNull
    private WebResourceResponse handleRequest(@NonNull String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return new WebResourceResponse(
                response.header("content-type", "text/plain"),
                response.header("content-encoding", "utf-8"),
                response.body().byteStream()
        );

    }



}
