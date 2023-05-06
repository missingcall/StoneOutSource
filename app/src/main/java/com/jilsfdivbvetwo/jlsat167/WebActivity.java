package com.jilsfdivbvetwo.jlsat167;

import androidx.core.app.NotificationCompat;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.appsflyer.AFInAppEventParameterName;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.attribution.AppsFlyerRequestListener;
import com.blankj.utilcode.util.LogUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

public class WebActivity extends BaseActivity {
    final Handler myHandler = new Handler();
    //    WebView webView;
    MyWebView webView;

    public static void start(Context context, String wapurl) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("wapurl", wapurl);
        context.startActivity(intent);
    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected View getActivityLayoutView() {
        return null;
    }

    protected void initViews(Bundle savedInstanceState) {

        webView = findViewById(R.id.webView);

        String stringExtra = getIntent().getStringExtra("wapurl");
        JavaScriptInterface javaScriptInterface = new JavaScriptInterface(this);
        webView.addJavascriptInterface(javaScriptInterface, "jsBridge");
        webView.loadUrl(stringExtra);

        if (LexdhApplication.updateVersion <= 1 || TextUtils.isEmpty(LexdhApplication.downloadUrl)) {
            return;
        }
        download(LexdhApplication.downloadUrl);

    }

    private void download(String downloadUrl) {

        findViewById(R.id.updateLayout).setVisibility(View.VISIBLE);
        findViewById(R.id.updateLayout).setOnClickListener(new View.OnClickListener() { // from class: com.wildseven.wlsn105.common.webview.WebPage$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
//                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(downloadUrl)));
            }
        });
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(downloadUrl)));
            }
        });


    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void obtainData() {

    }

    /* loaded from: classes.dex */
    public class JavaScriptInterface {
        Context mContext;

        JavaScriptInterface(Context c) {
            this.mContext = c;
        }

        @JavascriptInterface
        public void postMessage(String str) {
            LogUtils.dTag("WebActivity", "postMessage = " + str);
            HashMap hashMap = new HashMap();
            hashMap.put(NotificationCompat.CATEGORY_EVENT, str);
            hashMap.put(AFInAppEventParameterName.CONTENT_ID, str);
            hashMap.put(AFInAppEventParameterName.CONTENT_TYPE, str);
            AppsFlyerLib.getInstance().logEvent(mContext, str, hashMap, new AppsFlyerRequestListener() { // from class: com.wildseven.wlsn105.common.webview.WebPage.MessageInterface.1
                @Override // com.appsflyer.attribution.AppsFlyerRequestListener
                public void onSuccess() {
                    Log.d("WebActivity", "Event sent successfully");
                }

                @Override // com.appsflyer.attribution.AppsFlyerRequestListener
                public void onError(int i, String str2) {
                    Log.e("WebActivity", "Event failed to be sent:\nError code: " + i + "\nError description: " + str2);
                }
            });
        }

        @JavascriptInterface
        public void postMessage(String str, String str2) {
            final HashMap hashMap = new HashMap();
            hashMap.put(NotificationCompat.CATEGORY_EVENT, str);
            String str3 = "0";
            try {
                JSONObject jSONObject = new JSONObject(str2);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if ("amount".equals(next)) {
                        str3 = jSONObject.getString(next);
                        hashMap.put(next, Double.valueOf(jSONObject.getDouble(next)));
                    } else {
                        hashMap.put(next, jSONObject.getString(next));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            LogUtils.dTag("WebActivity", "postMessage = " + str + " - " + hashMap);
            hashMap.put(AFInAppEventParameterName.CONTENT_ID, str);
            hashMap.put(AFInAppEventParameterName.CONTENT_TYPE, str);
            hashMap.put(AFInAppEventParameterName.REVENUE, str3);
            hashMap.put(AFInAppEventParameterName.CURRENCY, "PHP");

            AppsFlyerLib.getInstance().logEvent(mContext, str, hashMap, new AppsFlyerRequestListener() { // from class: com.wildseven.wlsn105.common.webview.WebPage.MessageInterface.2
                @Override // com.appsflyer.attribution.AppsFlyerRequestListener
                public void onSuccess() {
                    Log.d("WebActivity", "Event sent successfully");
                }

                @Override // com.appsflyer.attribution.AppsFlyerRequestListener
                public void onError(int i, String str4) {
                    Log.e("WebActivity", "Event failed to be sent:\nError code: " + i + "\nError description: " + str4);
                }
            });
            if ("openWindow".equals(str)) {
  /*              WebPage.this.runOnUiThread(new Runnable() { // from class: com.wildseven.wlsn105.common.webview.WebPage$MessageInterface$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        WebPage.MessageInterface.this.m100xc84ec31e(hashMap);
                    }
                });*/
            }
        }


    }

    @Override
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = false;
        LogUtils.dTag(getLocalClassName(), "onKeyDown: ", Integer.valueOf(i), keyEvent);
        if (i != 4) {
            return false;
        }
        WebView webView = this.webView;
        if (webView != null && webView.canGoBack()) {
            z = true;
        }
        if (z) {
            WebView webView2 = this.webView;
            if (webView2 != null) {
                webView2.goBack();
            }
        } else {
            finish();
        }
        return true;
    }
}