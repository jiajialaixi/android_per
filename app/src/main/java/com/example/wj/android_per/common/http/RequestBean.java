package com.example.wj.android_per.common.http;

public class RequestBean<T>{
    private int code;
    private boolean login;
    private String msg;
    private boolean success;
    private T result;

    public int getCode() {
        return code;
    }


    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public RequestBean(int code, boolean login, String msg, boolean success, T result) {
        this.code = code;
        this.login = login;
        this.msg = msg;
        this.success = success;
        this.result = result;
    }

}
