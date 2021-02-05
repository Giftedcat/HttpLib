package com.giftedcat.httplib.net;

import android.content.Context;

import androidx.annotation.NonNull;

import com.giftedcat.httplib.UserLocalData;
import com.giftedcat.httplib.repository.GeneralRepository;
import com.giftedcat.httplib.repository.UserRepository;

/**
 *
 * @author GiftedCat
 * @date 17/2/23
 */

public class Injection {
    /**
     * 用户信息相关业务
     *
     * @param context
     * @return
     */

    public static UserRepository provideUserRepository(@NonNull Context context) {
        return UserRepository.getInstance(context, UserLocalData.getInstance(context.getApplicationContext()));
    }

    /**
     * 通用业务
     * @param context
     * @return
     */
    public static GeneralRepository provideGeneralRepository(@NonNull Context context) {
        return GeneralRepository.getInstance(context);
    }


}
