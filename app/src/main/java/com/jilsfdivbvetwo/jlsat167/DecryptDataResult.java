package com.jilsfdivbvetwo.jlsat167;

public class DecryptDataResult {

/*
*
*
* {name:'AZ测试跳转',
 wapurl:'https://api.gilet.ceshi.in/testku.html',
 iswap:1,
 splash:'https://feilvb.oss-ap-southeast-6.aliyuncs.com//gilet/uploads/images/368771edbc83ea47af3238811204de1b.png',
 downurl:'https://feilvb.oss-ap-southeast-6.aliyuncs.com//gilet/uploads/files/apk/202304/19_1681873341_uXlzOLvvra.apk',
 version:5,
 appsflyer_id:0}
*
*
* */
    private String name;
    private String wapurl;
    private int iswap;
    private String splash;
    private String downurl;
    private int version;
    private int appsflyer_id;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setWapurl(String wapurl) {
        this.wapurl = wapurl;
    }

    public String getWapurl() {
        return wapurl;
    }

    public void setIswap(int iswap) {
        this.iswap = iswap;
    }

    public int getIswap() {
        return iswap;
    }

    public void setSplash(String splash) {
        this.splash = splash;
    }

    public String getSplash() {
        return splash;
    }

    public void setDownurl(String downurl) {
        this.downurl = downurl;
    }

    public String getDownurl() {
        return downurl;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public void setAppsflyer_id(int appsflyer_id) {
        this.appsflyer_id = appsflyer_id;
    }

    public int getAppsflyer_id() {
        return appsflyer_id;
    }

    @Override
    public String toString() {
        return "DecryptDataResult{" +
                "name:'" + name + '\'' +
                ", wapurl:'" + wapurl + '\'' +
                ", iswap:" + iswap +
                ", splash:'" + splash + '\'' +
                ", downurl:'" + downurl + '\'' +
                ", version:" + version +
                ", appsflyer_id:" + appsflyer_id +
                '}';
    }
}
