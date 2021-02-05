package com.giftedcat.httplib.api;

import com.giftedcat.httplib.model.general.GeneralResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface GeneralApi {

    /**
     * 上传头像
     */
    @Multipart
    @POST("/xxx/xxx/xxx")
    Observable<GeneralResponse> uploadImage(@Part MultipartBody.Part imgs);

}
