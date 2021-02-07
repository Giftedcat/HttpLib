package com.giftedcat.httplib.net;

import android.content.Context;

import androidx.annotation.NonNull;

import com.giftedcat.httplib.api.GeneralApi;
import com.giftedcat.httplib.api.UserApi;
import com.giftedcat.httplib.model.general.GeneralResponse;
import com.giftedcat.httplib.model.user.UserResponse;
import com.giftedcat.httplib.utils.EncryptionUtil;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClientHelper {

    UserApi userApi;
    GeneralApi generalApi;

    MagicBoxObservableCallHelper helper;

    Context mContext;

    public HttpClientHelper(Context mContext, String apiUrl) {
        helper = new MagicBoxObservableCallHelper();
        this.mContext = mContext;
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient mOkHttpClient = (new OkHttpClient.Builder()
                .writeTimeout(10L, TimeUnit.SECONDS)
                .readTimeout(10L, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();

                        builder.addHeader("Accept-Language", Locale.getDefault().getLanguage());
                        builder.addHeader("signature", EncryptionUtil.encryptionParams(builder));

                        return chain.proceed(builder.build());
                    }
                })).build();

        Retrofit retrofit = (new Retrofit.Builder()).baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient).build();
        this.userApi = retrofit.create(UserApi.class);
        this.generalApi = retrofit.create(GeneralApi.class);
    }

    public static HttpClientHelper getInstance(Context mContext, String apiUrl) {
        return new HttpClientHelper(mContext, apiUrl);
    }

    public void login(Map<String, String> options, IResponseCallBack callBack) {
        Observable<UserResponse> observable = userApi.login(options);
        RetrofitClient.request(mContext, helper.map(observable), callBack);
    }

    public void uploadImage(File file, IResponseCallBack callBack) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        Observable<GeneralResponse> observable = generalApi.uploadImage(part);
        RetrofitClient.request(mContext, helper.map(observable), callBack);
    }


}
