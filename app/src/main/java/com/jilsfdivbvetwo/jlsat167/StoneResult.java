package com.jilsfdivbvetwo.jlsat167;

public class StoneResult {

    private int code;
    private String msg;
    private String data;
    private long now;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getNow() {
        return now;
    }

    @Override
    public String toString() {
        return "StoneResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                ", now=" + now +
                '}';
    }
}
