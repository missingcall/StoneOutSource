package com.jilsfdivbvetwo.jlsat167;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.MailTo;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Message;
import android.util.AttributeSet;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blankj.utilcode.util.LogUtils;

public class MyWebView extends WebView {
    private WebViewClient client;
    private WebChromeClient chromeClient;

    public MyWebView(Context context) {
        super(context);
        initWebView(context);
    }

    //这里调用super的时候掉了一个参数的构造方法,一直报空指针
    public MyWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initWebView(context);
    }

    public MyWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initWebView(context);
    }

    private void initWebView(Context context) {
        WebViewClient webViewClient = new WebViewClient() { // from class: com.wildseven.wlsn105.common.webview.MyWebView.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str.startsWith(MailTo.MAILTO_SCHEME)) {
                    MyWebView.this.getContext().startActivity(new Intent("android.intent.action.SENDTO", Uri.parse(str)));
                    return true;
                } else if (str.startsWith("tel:")) {
                    MyWebView.this.getContext().startActivity(new Intent("android.intent.action.DIAL", Uri.parse(str)));
                    return true;
                } else if (str.startsWith("https://play.google.com/store/apps/details?id=")) {
                    return super.shouldOverrideUrlLoading(webView, str);
                } else {
                    webView.loadUrl(str);
                    return false;
                }
            }
        };
        this.client = webViewClient;

        if (LexdhApplication.webviewSet.equals(LexdhApplication.WEBVIEW_SET_WSD)) {
            this.chromeClient = new WebChromeClient() {
                @Override
                public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
                    WebView tempWebView = new WebView(context);
                    tempWebView.setWebViewClient(new WebViewClient() {
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                        context.startActivity(new Intent("android.intent.action.VIEW", request.getUrl()));
                            LexdhActivity.openSystemBrowser(context, request.getUrl());
                            return true;
                        }

                        @Override
                        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                            handler.proceed();
                        }
                    });

                    WebViewTransport transport = (WebViewTransport) resultMsg.obj;
                    transport.setWebView(tempWebView);
                    resultMsg.sendToTarget();
                    return true;
                }

                @Override
                public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage(message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                    result.cancel();
                    return true;
                }
            };
        } else {
            this.chromeClient = new WebChromeClient();
        }


        setWebViewClient(client);
        setWebChromeClient(chromeClient);
        initWebViewSettings(context);
        requestFocus(130);
        setClickable(true);
    }

    private void initWebViewSettings(Context context) {
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        if (LexdhApplication.webviewSet.equals(LexdhApplication.WEBVIEW_SET_WSD)) {
            settings.setSupportMultipleWindows(true);//重要! 必须重写WebChromeClient的onCreateWindow方法
        }
        settings.setUserAgentString(getUseragent(context, LexdhApplication.USERAGENT_VERSION, LexdhActivity.getAndroidID()));
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        settings.setLoadsImagesAutomatically(true);
        settings.setMixedContentMode(0);
        settings.setAllowFileAccess(true);
    }

    public static String getUseragent(final Context context, String version, String uuid) {
        String userAgent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                userAgent = WebSettings.getDefaultUserAgent(context);
            } catch (Exception e) {
                userAgent = System.getProperty("http.agent");
            }
        } else {
            userAgent = System.getProperty("http.agent");
        }
        final StringBuilder sb = new StringBuilder();
        assert userAgent != null;
        for (int i = 0, length = userAgent.length(); i < length; i++) {
            char c = userAgent.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", (int) c));
            } else {
                sb.append(c);
            }
        }
        String replace = sb.toString().replace("; wv", "; xx-xx");
        String format = String.format("%s/%s AppShellVer:%s UUID/%s", replace, Build.BRAND, version, uuid);
        LogUtils.d(format);
        return format;
    }
}