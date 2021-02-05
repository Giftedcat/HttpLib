package com.giftedcat.httplib.api;

import com.giftedcat.httplib.model.user.UserResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserApi {

    /**
     * 账号登录
     */
    @FormUrlEncoded
    @POST("/xxx/xxx/xxx")
    Observable<UserResponse> login(@FieldMap Map<String, String> postRequest);

}
