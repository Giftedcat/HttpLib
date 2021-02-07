package com.giftedcat.httplib.net;

import android.net.ParseException;

import com.giftedcat.httplib.model.BaseResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * @author：GiftedCat.

 * 时间：On 2019-05-06.

 * 描述：自定义错误解析类
 */
public class ExceptionHandle {

  private static final int TIMEOUT_ERROR = -1;
  private static final int JSON_ERROR = -2;
  private static final int NETWORK_ERROR = -3;
  private static final int OTHER_ERROR = -4;

  private static final String TIMEOUTMSG = "请求超时";
  private static final String JSONMSG = "解析错误";
  private static final String NETWORKMSG = "连接失败";
  private static final String OTHERMSG = "未知错误";

  /**
   * 根据接口定义 401 为token失效 需要重新登录获取新的token
   */
  private static final int TOKENLOGIN = 999;

  public static OkHttpException handleException(Throwable e) {
    OkHttpException ex = null;
    if (e instanceof HttpException) {
      ResponseBody body = ((HttpException) e).response().errorBody();
      try {
        Gson gson = new GsonBuilder().serializeNulls().create();
        String jsonStr = body.string();
        BaseResponse baseBean = gson.fromJson(jsonStr, BaseResponse.class);

        /**
         * token失效 重新登录
         */
        if (baseBean.getCode() == TOKENLOGIN ) {
//          activity.startActivity(LoginActivity.class);
//          activity.finish();
        } else {
          ex = new OkHttpException(baseBean.getCode(), baseBean.getMsg());
        }
      } catch (IOException e1) {
        e1.printStackTrace();
      }
      return ex;
    }else if (e instanceof java.net.SocketTimeoutException
            || e instanceof IOException) {
      ex = new OkHttpException(TIMEOUT_ERROR, TIMEOUTMSG);
      return ex;
    } else if (e instanceof JsonParseException
        || e instanceof JSONException
        || e instanceof ParseException) {
      ex = new OkHttpException(JSON_ERROR, JSONMSG);
      return ex;
    } else if (e instanceof ConnectException) {
      ex = new OkHttpException(NETWORK_ERROR, NETWORKMSG);
      return ex;
    } else {
      ex = new OkHttpException(OTHER_ERROR, OTHERMSG);
      return ex;
    }
  }

}

