package com.feicui.edu.gitdriod.login;

/**
 * Created by Administrator on 16-8-29.
 */
public interface LoginView {

    /**
     * 1.直接跳转到主页面
     * 2.显示信息
     * 3.显示我们的加载的画面
     * 4.页面进行刷新，重新进行请求
     */

    void gotoMainActivity();

    void showMessage(String msg);

    void showProgress();

    void resetWebView();
}
