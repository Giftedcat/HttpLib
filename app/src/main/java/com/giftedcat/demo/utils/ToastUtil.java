package com.giftedcat.demo.utils;

import android.widget.Toast;

import androidx.annotation.StringRes;

import com.giftedcat.demo.application.MainApplication;


/**
 * Created by TangTianYi on 2017/1/13.
 */

public class ToastUtil {
    private ToastUtil() {
    }

    public static void showToast(String msg) {
        if (msg != null)
            Toast.makeText(MainApplication.sharedInstance(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(@StringRes int resid) {
        String msg = MainApplication.sharedInstance().getString(resid);
        Toast.makeText(MainApplication.sharedInstance(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 当出现因为网络相关异常时，弹出此提示
     */
    public static void showToastErrorForNet() {
        showToast("网络异常");
    }

    /**
     * 当出现因为我写的代码有问题时，弹出此提示
     */
    public static void showToastErrorForMe() {
        showToast("代码异常");
    }

    /**
     * 当出现因为我写的代码有问题时，弹出此提示
     */
    public static void showToastErrorForServer() {
        showToast("网络返回错误");
    }
}
