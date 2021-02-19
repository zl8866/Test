package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.widget.TextView;

import com.ycbjie.webviewlib.base.X5WebChromeClient;
import com.ycbjie.webviewlib.wv.X5WvWebView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;

public class Main1Activity extends AppCompatActivity {


    private X5WebChromeClient chromeClient;
    private X5WvWebView mWebView;
    private TextView mTvTitle;

    public static void start() {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1main);
        mWebView = findViewById(R.id.web_view);
        mTvTitle = findViewById(R.id.tv_title);
        findViewById(R.id.iv_back).setOnClickListener(v -> finish());

        String json = "{\"data\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMzMxNzk3MDIzMTkxNjAxMTUyIiwiYXVkIjoiMTM1NjU2MTAwMjk0NjEwMTI0OCIsImlzcyI6IjE4Nzg0MzEzNTg1IiwiZXhwIjoxNjEyODY5MTIxfQ.yLnWiHbjAiHPQ1zzhlX4oEZYSeQSJwu70iMKPn3QMG3RT2X-nPIH-mYjlNCmxuMQEUj48h1OVALDGB5MLHel0Q\",\"msgType\":\"cmd_userToken\"}";
        mWebView.loadUrl("file:///android_asset/anzhuo.html");
        initWebViewBridge();
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        mWebView.getSettings().setAppCacheEnabled(true);
        chromeClient = new X5WebChromeClient(mWebView, this) {
        };
        mWebView.setWebChromeClient(chromeClient);
        mWebView.callHandler("calledHandler", json, data -> {

        });
    }


    // 注册JS方法 js调用java
    @JavascriptInterface
    public void initWebViewBridge() {
        mWebView.registerHandler("sendTitle", (data, function) -> {
            System.out.println("data=" + data);
        });
        mWebView.registerHandler("callbackHandler", (data, function) -> {
            System.out.println("data==" + data);
            try {
                JSONObject jsonObject = new JSONObject(data.toString());
                String msgType = jsonObject.getString("msgType");
                Log.d("msgType", msgType);
                /// 发送token
                if (msgType.equals("cmd_userToken")) {
                    HashMap<String, String> dataNew = new HashMap<String, String>() {{
                        put("msgType", "cmd_userToken");
                        put("data", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMzMxNzk3MDIzMTkxNjAxMTUyIiwiYXVkIjoiMTM1Njg3MTQ4MzMzMDEzNDAxNiIsImlzcyI6IjE4Nzg0MzEzNTg1IiwiZXhwIjoxNjEyOTQzMTQ1fQ.-8vmGNo8RkS5OXUepU21a9DTEUS1sGQhkR1M8ESjhTCnR2TlbpHBTMGW5SjtSB8uA2eCIXFIkOkTcO6p3gwnWg");
                    }};
                    function.onResult(new JSONObject(dataNew));
                }
                /// 关闭页面
                else if (msgType.equals("cmd_closePage")) {
                    finish();
                }
                /// 返回上级页面
                else if (msgType.equals("cmd_goBack")) {
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        });
    }
}