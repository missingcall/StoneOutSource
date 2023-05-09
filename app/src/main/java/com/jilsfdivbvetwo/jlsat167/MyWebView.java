package com.jilsfdivbvetwo.jlsat167;

import android.content.Context;
import android.content.Intent;
import android.net.MailTo;
import android.net.Uri;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebView extends WebView {
    private final WebViewClient client;

    public MyWebView(Context context) {
        super(context);
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
        setWebViewClient(webViewClient);
        initWebViewSettings();
        requestFocus(130);
        setClickable(true);
    }

    public MyWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
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
        setWebViewClient(webViewClient);
        initWebViewSettings();
        requestFocus(130);
        setClickable(true);
    }

    public MyWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
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
        setWebViewClient(webViewClient);
        initWebViewSettings();
        requestFocus(130);
        setClickable(true);
    }

    private void initWebViewSettings() {
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
//        settings.setJavaScriptCanOpenWindowsAutomatically(true);
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
//        settings.setSupportMultipleWindows(true);
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
}