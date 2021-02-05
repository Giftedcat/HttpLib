package com.giftedcat.httplib.repository;

import android.content.Context;

import androidx.annotation.NonNull;

import com.giftedcat.httplib.BuildConfig;
import com.giftedcat.httplib.net.HttpClientHelper;
import com.giftedcat.httplib.net.IResponseCallBack;
import com.giftedcat.httplib.UserLocalData;

import java.util.Map;
import java.util.TreeMap;


public class UserRepository {

    private static UserRepository INSTANCE;
    HttpClientHelper clientHelper;
    private UserLocalData mUserLocalData;

    private Context mContext;

    public static UserRepository getInstance(Context mContext, @NonNull UserLocalData userLocalData) {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository(mContext, userLocalData);
        }
        return INSTANCE;
    }

    private UserRepository(Context mContext, @NonNull UserLocalData userLocalData) {
        this.mContext = mContext;
        mUserLocalData = userLocalData;
        clientHelper = HttpClientHelper.getInstance(mContext, BuildConfig.SERVEL_URL);
    }

    /**
     * 账号登录
     */
    public void login(String login, String pwd, IResponseCallBack callBack) {
        Map<String, String> options = new TreeMap<>();
        options.put("login", login);
        options.put("passwd", pwd);
        clientHelper.login(options, callBack);
    }

}
