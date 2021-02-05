package com.giftedcat.httplib.net;

/**
 * 自定义回调接口
 * @author GiftedCat
 * */
public interface IResponseCallBack<T> {

    /**
     * 调用成功下一步
     *
     * @param response 数据流
     */
    void onSuccess(T response);

    /**
     * 调用error
     *
     * @param e 异常
     */
    void onFailed(OkHttpException e);

    /**
     * 调用结束
     * */
    default void onFinish(){}

}
