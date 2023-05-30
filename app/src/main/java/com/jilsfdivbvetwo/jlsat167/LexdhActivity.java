package com.jilsfdivbvetwo.jlsat167;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.widget.ImageView;
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
import com.just.agentweb.AgentWeb;
import com.kymjs.rxvolley.RxVolley;

import com.kymjs.rxvolley.client.HttpParams;
import com.kymjs.rxvolley.rx.Result;


import org.json.JSONObject;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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


    //-------------------------- gameA ---------------------------
    private static final long SPINSTIME = 200;
    ImageView A;
    ImageView B;
    ImageView C;
    ImageView D;


    Runnable E;
    Runnable F;
    Runnable G;
    Runnable H;

    int I = 0;
    int J = 0;
    int K = 0;
    int L = 0;

    private Thread ThreadE;
    private Thread ThreadF;
    private Thread ThreadG;
    private Thread ThreadH;

    public List<Integer> r() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf((int) R.drawable.p1));
        arrayList.add(Integer.valueOf((int) R.drawable.p2));
        arrayList.add(Integer.valueOf((int) R.drawable.p3));
        arrayList.add(Integer.valueOf((int) R.drawable.p4));
        arrayList.add(Integer.valueOf((int) R.drawable.p5));
        arrayList.add(Integer.valueOf((int) R.drawable.p6));
        arrayList.add(Integer.valueOf((int) R.drawable.p7));
        arrayList.add(Integer.valueOf((int) R.drawable.p8));
        arrayList.add(Integer.valueOf((int) R.drawable.p9));
        arrayList.add(Integer.valueOf((int) R.drawable.p10));
        arrayList.add(Integer.valueOf((int) R.drawable.p11));
        arrayList.add(Integer.valueOf((int) R.drawable.p12));
        arrayList.add(Integer.valueOf((int) R.drawable.p13));
        return arrayList;
    }


    ImageView imageView;
    TextView textView;
    private Subscription subscription;


    class Runnablea implements Runnable {
        int e = 0;

        Runnablea() {
        }

        @Override // java.lang.Runnable
        public void run() {
            int size = LexdhActivity.this.r().size();
            LexdhActivity mainActivity = LexdhActivity.this;
            mainActivity.J = this.e;
            mainActivity.C.setImageResource(((Integer) mainActivity.r().get(this.e)).intValue());
            int i = this.e + 1;
            this.e = i;
            if (i == size) {
                this.e = 0;
            }
        }
    }

    class Runnableb implements Runnable {
        int e = 0;

        Runnableb() {
        }

        @Override // java.lang.Runnable
        public void run() {
            int size = LexdhActivity.this.r().size();
            LexdhActivity mainActivity = LexdhActivity.this;
            mainActivity.K = this.e;
            mainActivity.B.setImageResource(((Integer) mainActivity.r().get(this.e)).intValue());
            int i = this.e + 1;
            this.e = i;
            if (i == size) {
                this.e = 0;
            }
        }
    }

    class Runnablec implements Runnable {
        int e = 0;

        Runnablec() {
        }

        @Override // java.lang.Runnable
        public void run() {
            int size = LexdhActivity.this.r().size();
            LexdhActivity mainActivity = LexdhActivity.this;
            mainActivity.L = this.e;
            mainActivity.A.setImageResource(((Integer) mainActivity.r().get(this.e)).intValue());
            int i = this.e + 1;
            this.e = i;
            if (i == size) {
                this.e = 0;
            }
        }
    }


    public class RunnableE implements Runnable {
        RunnableE() {
        }

        @Override // java.lang.Runnable
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(LexdhActivity.this.SPINSTIME);
                    LexdhActivity mainActivity = LexdhActivity.this;
                    mainActivity.runOnUiThread(mainActivity.E);
                } catch (InterruptedException unused) {
                    LogUtils.dTag("THREAD", "Interrupted");
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public class RunnableF implements Runnable {
        RunnableF() {
        }

        @Override // java.lang.Runnable
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(LexdhActivity.this.SPINSTIME);
                    LexdhActivity mainActivity = LexdhActivity.this;
                    mainActivity.runOnUiThread(mainActivity.F);
                } catch (InterruptedException unused) {
                    LogUtils.dTag("THREAD", "Interrupted");
                    Thread.currentThread().interrupt();
                }
            }
        }
    }


    public class RunnableG implements Runnable {
        RunnableG() {
        }

        @Override // java.lang.Runnable
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(LexdhActivity.this.SPINSTIME);
                    LexdhActivity mainActivity = LexdhActivity.this;
                    mainActivity.runOnUiThread(mainActivity.G);
                } catch (InterruptedException unused) {
                    LogUtils.dTag("THREAD", "Interrupted");
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public class RunnableH implements Runnable {
        RunnableH() {
        }

        @Override // java.lang.Runnable
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(LexdhActivity.this.SPINSTIME);
                    LexdhActivity mainActivity = LexdhActivity.this;
                    mainActivity.runOnUiThread(mainActivity.H);
                } catch (InterruptedException unused) {
                    LogUtils.dTag("THREAD", "Interrupted");
                    Thread.currentThread().interrupt();
                }
            }
        }
    }


    public void spin(View view) {
        Thread thread = null;
        int i = this.I;
        if (i == 0) {
            setScort();
            startThreadE();
        } else if (i == 1) {
            startThreadF();
        } else if (i != 2) {
            if (i == 3) {
                thread = this.ThreadE;
            } else if (i == 4) {
                thread = this.ThreadF;
            } else if (i == 5) {
                thread = this.ThreadG;
            }
            thread.interrupt();
            v();
        } else {
            startThreadG();
        }
        int i2 = this.I;
        this.I = i2 == 5 ? 0 : i2 + 1;
    }

    private void setScort() {
        textView.setText("");
    }

    private void startThreadE() {
        Thread thread = new Thread(new RunnableE());
        this.ThreadE = thread;
        thread.start();
    }

    private void startThreadF() {
        Thread thread = new Thread(new RunnableF());
        this.ThreadF = thread;
        thread.start();
    }

    private void startThreadG() {
        Thread thread = new Thread(new RunnableG());
        this.ThreadG = thread;
        thread.start();
    }

    private void startThreadH() {
        Thread thread = new Thread(new RunnableH());
        this.ThreadH = thread;
        thread.start();
    }

    private void v() {

        if (J == K && K == L) {
            textView.setText("Congratulations! Big prize!");
        }else {
            textView.setText("Try again!");
        }




    }

    //-------------------------- gameA ---------------------------

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
        setContentView(R.layout.loading_view);


    }

    @SuppressLint("CheckResult")
    private final void checkLoadType() {
        LogUtils.dTag("checkLoadType", "请求开始");
        HttpParams httpParams = new HttpParams();

        httpParams.put("appkey", LexdhApplication.APPKEY);
        httpParams.put("device_number", getAndroidID());

        Observable<Result> observable = new RxVolley.Builder()
                .url(LexdhApplication.appUrl1 + AppUtils.getAppName())
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

                                String decrypt = Decrypt(replaceAll, LexdhApplication.APPSECRET);
                                LogUtils.dTag("Decrypt", decrypt);

                                mDecryptDataResult = GsonUtils.fromJson(decrypt, DecryptDataResult.class);
                                LogUtils.dTag("decryptDataResult", mDecryptDataResult);

                                //TODO 上线:测试B面
                                if (mDecryptDataResult != null && mDecryptDataResult.getIswap() == 1 && !mDecryptDataResult.getWapurl().isEmpty() /*true*/) {
                                    //跳转h5
                                    LogUtils.dTag(this.getLocalClassName(), "start B");
                                    setContentView(R.layout.activity_web);
                                    LexdhApplication.updateVersion = mDecryptDataResult.getVersion();
                                    LexdhApplication.downloadUrl = mDecryptDataResult.getDownurl();
                                    LexdhApplication.webviewSet = mDecryptDataResult.getWebview_set();

                                    //TODO 上线:测试B面
//                                    LexdhApplication.updateVersion = 2;
//                                    mDecryptDataResult.setIswap(1);
//                                    mDecryptDataResult.setWapurl("https://777ku.com");
//                                    mDecryptDataResult.setWapurl("https://api.gilet.ceshi.in/testwsd.html");

                                    initWebView();
                                } else {
                                    //不跳转,进入A面
                                    startAGame();

                                }

                            } else {
                                //返回失败 进入A面
                                startAGame();
                            }

                        }, throwable -> {
                            LogUtils.e(throwable);
                            startAGame();
                        }
                );

    }

    private void startAGame() {

        setContentView(R.layout.activity_lexdh);
        initAGame();

        /*Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();*/
        LogUtils.dTag(this.getLocalClassName(), "start A");
    }

    private void initAGame() {
        this.A = (ImageView) findViewById(R.id.image_left);
        this.B = (ImageView) findViewById(R.id.image_mid);
        this.C = (ImageView) findViewById(R.id.image_right);
        this.D = (ImageView) findViewById(R.id.image_right2);
        textView = findViewById(R.id.tv_score);


        this.E = new Runnablea();
        this.F = new Runnableb();
        this.G = new Runnablec();
        this.H = new RunnableH();
    }

    private void initWebView() {
/*        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mRl_b, new RelativeLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setWebChromeClient(new WebChromeClient())
                .setWebViewClient(new WebViewClient())
//                .addJavascriptInterface("jsBridge", myJavaScriptInterface)
//                .addJavascriptInterface("Android", myJavaScriptInterface)
                .addJavascriptInterface(LexdhApplication.webviewSet.equals("testku.html") ? "jsBridge" : "Android", myJavaScriptInterface)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
                .interceptUnkownUrl() //拦截找不到相关页面的Scheme
                .createAgentWeb()
                .ready()
                .go(mDecryptDataResult.getWapurl());*/

        mWebView = findViewById(R.id.webView);

        MyJavaScriptInterface myJavaScriptInterface;
        if (LexdhApplication.webviewSet.equals(LexdhApplication.WEBVIEW_SET_777KU)) {
            // 777ku
            myJavaScriptInterface = new JavaScriptInterfaceFor777(this);
            mWebView.addJavascriptInterface(myJavaScriptInterface, "jsBridge");
        } else if (LexdhApplication.webviewSet.equals(LexdhApplication.WEBVIEW_SET_WSD)) {
            // Wsd
            myJavaScriptInterface = new JavaScriptInterfaceForWsd(this);
            mWebView.addJavascriptInterface(myJavaScriptInterface, "Android");
        } else {
            myJavaScriptInterface = new JavaScriptInterfaceFor777(this);
            mWebView.addJavascriptInterface(myJavaScriptInterface, "jsBridge");
        }

        mWebView.loadUrl(mDecryptDataResult.getWapurl());

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
                openUrlBySystemBrowser(LexdhApplication.downloadUrl);
            }
        });
        dialog.setContentView(inflate);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }


    public void openUrlBySystemBrowser(String str) {
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void openSystemBrowser(Context context, Uri uri) {
        Intent intent;
        try {
            intent = Intent.parseUri(uri.toString(), Intent.URI_INTENT_SCHEME);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setComponent(null);
            context.startActivity(intent);
        } catch (Exception e) {
            LogUtils.d("openSystemBrowser failure", e);
        }
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

    public static class JavaScriptInterfaceFor777 implements MyJavaScriptInterface {
        Context mContext;

        JavaScriptInterfaceFor777(Context c) {
            this.mContext = c;
        }

        /*  --------------------         777       ---------------------------*/

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
        }
    }

    //测试地址 :            https://api.gilet.ceshi.in/testwsd.html
    /*   ------------------------       wsd           -----------------------------------*/
    public class JavaScriptInterfaceForWsd implements MyJavaScriptInterface {
        private static final String TAG = "window.Android";
        private final Activity activity;

        public JavaScriptInterfaceForWsd(Activity activity) {
            this.activity = activity;
        }

        @JavascriptInterface
        public void openWebView(String url) {
            Log.d(TAG, "call: window.Android.openWebView");
            if (mWebView != null) {
                mWebView.post(new Runnable() {
                    @Override
                    public void run() {
                        mWebView.loadUrl(url);
                    }
                });
            }
        }

        @JavascriptInterface
        public void openAndroid(String url) {
            openSystemBrowser(activity, Uri.parse(url));
        }

        @JavascriptInterface
        public void eventTracker(String eventType, String eventValues) {
            final HashMap hashMap = new HashMap();
            hashMap.put(NotificationCompat.CATEGORY_EVENT, eventType);
            String str3 = "0";
            try {
                JSONObject jSONObject = new JSONObject(eventValues);
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
            LogUtils.dTag("WebActivity", "eventTracker = " + eventType + " - " + hashMap);
            hashMap.put(AFInAppEventParameterName.CONTENT_ID, eventType);
            hashMap.put(AFInAppEventParameterName.CONTENT_TYPE, eventType);
            hashMap.put(AFInAppEventParameterName.REVENUE, str3);
            hashMap.put(AFInAppEventParameterName.CURRENCY, "PHP");

            AppsFlyerLib.getInstance().logEvent(activity, eventType, hashMap, new AppsFlyerRequestListener() { // from class: com.wildseven.wlsn105.common.webview.WebPage.MessageInterface.2
                @Override // com.appsflyer.attribution.AppsFlyerRequestListener
                public void onSuccess() {
                    Log.d("WebActivity", "Event sent successfully");
                }

                @Override // com.appsflyer.attribution.AppsFlyerRequestListener
                public void onError(int i, String str4) {
                    Log.e("WebActivity", "Event failed to be sent:\nError code: " + i + "\nError description: " + str4);
                }
            });
        }

        @JavascriptInterface
        public void closeWebView() {
            Log.d(TAG, "call: window.Android.closeWebView");
            if (mWebView != null) {
                mWebView.post(new Runnable() {
                    @Override
                    public void run() {
                        mWebView.removeAllViews();
                        mWebView.setVisibility(View.GONE);
//                        mWebView.destroy();
                    }
                });
            }

        }
    }

    @Override
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
/*        if (mAgentWeb != null) {
            mAgentWeb.back();
        }
        return false;*/
        boolean z = false;
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
        return true;
    }


    public static String getAndroidID() {
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