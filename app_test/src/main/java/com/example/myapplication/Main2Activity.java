package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.ycbjie.webviewlib.wv.WvJsHandler;
import com.ycbjie.webviewlib.wv.X5WvWebView;

import androidx.appcompat.app.AppCompatActivity;


public class Main2Activity extends AppCompatActivity {


    private String mJson;
    private X5WvWebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1main);
        mWebView = findViewById(R.id.web_view);
        findViewById(R.id.iv_back).setOnClickListener(v -> finish());
        mJson = "{\"data\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMzMxNzk3MDIzMTkxNjAxMTUyIiwiYXVkIjoiMTM1NjgxNDc4MTIyMjIyMzg3MiIsImlzcyI6IjE4Nzg0MzEzNTg1IiwiZXhwIjoxNjEyOTI5NjI2fQ.Xsci21HD51rlBkIOPmbefFby2Qu2y1tKpMfJQ7LZNv97_Jh3G8QPEe1nJcQZE5eWotyOmwiRzaDp-m-oc-m-Vw\",\"msgType\":\"cmd_userToken\"}";
        initWebViewBridge();
        mWebView.loadUrl("http://192.168.2.72:8067/CompanyRegisterIntroduce");
    }

    @JavascriptInterface
    public void initWebViewBridge() {
        //js调native
        mWebView.registerHandler("sendTitle", (data, callback) -> Log.d("js调native设置标题", data.toString()));
        mWebView.registerHandler("callbackHandler", (WvJsHandler) (data, callback) -> {
            callback.onResult(mJson);
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mWebView.getX5WebChromeClient().uploadMessageForAndroid5(data, resultCode);
    }


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.getSettings().setJavaScriptEnabled(true);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mWebView != null) {
            mWebView.getSettings().setJavaScriptEnabled(false);
        }
    }
}