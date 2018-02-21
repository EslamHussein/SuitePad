package com.suitepad.presentation;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


    @Override
    protected void onStart() {
        super.onStart();

        webView = findViewById(R.id.web_view);
        webView.setWebViewClient(new CustomWebViewClient(this));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);



    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.loadUrl("file:///android_asset/sample.html");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.suitepad.httpproxyserver", "com.suitepad.httpproxyserver.HTTPProxyService"));
        stopService(intent);

    }
}
