package com.jilsfdivbvetwo.jlsat167;

import android.app.Application;
import android.content.Context;

import com.appsflyer.AppsFlyerLib;
import com.blankj.utilcode.util.LogUtils;

public class LexdhApplication extends Application {


    /*
    *   App名称：测试跳转777KU
        appkey：e4b3dc7b23652624118d9c769dfb5625
        APP秘钥 ：jaix8WnfqRFpQlLk

        App名称：测试跳转WSD
        appkey：ea829534fdc2b87421a0a1ad59db9620
        APP秘钥 ：jaix8WnfqRFpQlLk

        App名称：测试不跳转
        appkey：8909f537acd4bd473ae0cbf745e505b6
        APP秘钥 ：jaix8WnfqRFpQlLk

        App名称：测试版本更新
        appkey：3e216b6296be95de8d127698de95674d
        APP秘钥 ：jaix8WnfqRFpQlLk
    *
    *
    * */
    public static final String AF_DEV_KEY = "CPHERDeCSretREiAbQg38V";
    public static final String APPKEY = "8029da0911f7bc3a49c939d7d1d28ac0"; //167
    public static final String APPSECRET = "3Dd7Jy0xJtWQfNkb"; //167
    //    public static final String APPKEY = "3e216b6296be95de8d127698de95674d"; //测试
//    public static final String APPSECRET = "jaix8WnfqRFpQlLk"; //测试
    //Useragent的最新 {Version}值是 1.0.10（请每个新包检查该版本号）
    public static final String USERAGENT_VERSION = "1.0.10";


    /*
     *
     *    接口地址，5选一都可以用，返回的格式不一样
     * */
    public static final String appUrl1 = "https://f1sdfgpt.buzz/api/v1/app/";
    public static final String appUrl2 = "https://f1sdfgpt.buzz/api/v2/scxx/";
    public static final String appUrl3 = "https://f1sdfgpt.buzz/api/v3/apsdfgsevbp/";
    public static final String appUrl4 = "https://f1sdfgpt.buzz/api/v4/ghjfd/";
    public static final String appUrl5 = "https://f1sdfgpt.buzz/api/v5/jfgfc/";

    public static final String appUrlTest = "https://api.gilet.ceshi.in/api/v1/app/";
    /*
    * 备用域名
    *   j4sdxcqw.buzz
        j4qwmjhy.buzz
        j5fdxoop.buzz
        j5xdstyg.buzz
        j6fdsqxu.buzz
        j2qwefds.buzz
        j7hgvdqt.buzz
        j7bvfdqi.buzz
        j8jhvdqe.buzz
        j8mhbfsu.buzz
    *
    * */

    private static Context context = null;
    public static String downloadUrl = "";
    public static String webviewSet = "";  //777KU / WSD
    public static final String WEBVIEW_SET_777KU = "777KU";
    public static final String WEBVIEW_SET_WSD = "WSD";
    public static int updateVersion = 1;


    private static LexdhApplication instance;
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    public static LexdhApplication getApp() {
        return instance;
    }

    @Override // tim.com.libnetwork.app.MyApplication, android.app.Application
    public void onCreate() {
        super.onCreate();
        mContext = this;
        initLog();
        initApplication();
        initAppsFlyer();
    }

    private void initApplication() {
        instance = this;
    }

    private void initAppsFlyer() {
        AppsFlyerLib.getInstance().init(AF_DEV_KEY, null, this);
        AppsFlyerLib.getInstance().start(this);
        AppsFlyerLib.getInstance().setDebugLog(true);
    }

    public static LexdhApplication getInstance() {
        return instance;
    }

    public void initLog() {
        final LogUtils.Config config = LogUtils.getConfig()
                //TODO 上线:需关闭log
                .setLogSwitch(BuildConfig.DEBUG)// 设置 log 总开关，包括输出到控制台和文件，默认开
//                .setConsoleSwitch(BuildConfig.DEBUG)// 设置是否输出到控制台开关，默认开
                .setGlobalTag(null)// 设置 log 全局标签，默认为空
                // 当全局标签不为空时，我们输出的 log 全部为该 tag，
                // 为空时，如果传入的 tag 为空那就显示类名，否则显示 tag
                .setLogHeadSwitch(true)// 设置 log 头信息开关，默认为开
                .setLog2FileSwitch(false)// 打印 log 时是否存到文件的开关，默认关
                .setDir("")// 当自定义路径为空时，写入应用的/cache/log/目录中
                .setFilePrefix("")// 当文件前缀为空时，默认为"util"，即写入文件为"util-MM-dd.txt"
                .setBorderSwitch(true)// 输出日志是否带边框开关，默认开
                .setSingleTagSwitch(true)// 一条日志仅输出一条，默认开，为美化 AS 3.1 的 Logcat
                .setConsoleFilter(LogUtils.V)// log 的控制台过滤器，和 logcat 过滤器同理，默认 Verbose
                .setFileFilter(LogUtils.V)// log 文件过滤器，和 logcat 过滤器同理，默认 Verbose
                .setStackDeep(1)// log 栈深度，默认为 1
                .setStackOffset(0);// 设置栈偏移，比如二次封装的话就需要设置，默认为 0
        LogUtils.d(config.toString());
    }

}
