package com.giftedcat.httplib;

import android.content.Context;

import androidx.annotation.NonNull;

import com.giftedcat.httplib.utils.SpEditor;

/**
 * 在这里保存和获取用户全局信息
 * @author GiftedCat
 * */
public class UserLocalData {

    private static UserLocalData INSTANCE;
    private SpEditor spEditor;

    private UserLocalData(@NonNull Context context) {
        spEditor = SpEditor.getInstance(context);
    }

    public static UserLocalData getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new UserLocalData(context);
        }
        return INSTANCE;
    }

}
