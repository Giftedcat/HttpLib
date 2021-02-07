package com.giftedcat.httplib.repository;

import android.content.Context;

import com.giftedcat.httplib.BuildConfig;
import com.giftedcat.httplib.net.HttpClientHelper;
import com.giftedcat.httplib.net.IResponseCallBack;

import java.io.File;

/**
 * @author GiftedCat
 * @date 17/2/9
 */

public class GeneralRepository {
    private static GeneralRepository INSTANCE;

    private Context mContext;

    HttpClientHelper clientHelper;

    public static GeneralRepository getInstance(Context mContext) {
        INSTANCE = new GeneralRepository(mContext);
        return INSTANCE;
    }

    private GeneralRepository(Context mContext) {
        this.mContext = mContext;
        clientHelper = HttpClientHelper.getInstance(mContext, BuildConfig.SERVEL_URL);
    }

    /**
     * 上传头像
     */
    public void uploadImage(File file, IResponseCallBack callBack) {
        clientHelper.uploadImage(file, callBack);
    }

}
