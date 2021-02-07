package com.giftedcat.httplib.net;

import android.app.Activity;
import android.content.Context;

import com.giftedcat.httplib.BuildConfig;
import com.giftedcat.httplib.model.BaseResponse;
import com.giftedcat.httplib.utils.DtLog;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 自定义Observer
 * @author GiftedCat
 * */
public class CallBackObserver<T extends BaseResponse> implements Observer<T> {

    IResponseCallBack callBack;
    private Activity activity;

    public CallBackObserver(Context mContext, IResponseCallBack callBack) {
        this.callBack = callBack;
        this.activity = (Activity) mContext;
    }

    @Override
    public void onSubscribe(Disposable d) {
        /**
         * 这里可以 显示加载圈等
         */
    }

    @Override
    public void onNext(T response) {
        if (callBack != null && !activity.isFinishing()) {
            if (BuildConfig.DEBUG){
                DtLog.e("KnowledgeBean:%s", response.toString());
            }
            callBack.onSuccess(response);
            callBack.onFinish();
        }
    }

    @Override
    public void onError(Throwable e) {
        onComplete();
        if (callBack != null && !activity.isFinishing()) {
            OkHttpException exception = ExceptionHandle.handleException(e);
            callBack.onFailed(exception);
            callBack.onFinish();
        }
    }

    @Override
    public void onComplete() {
        /**
         * 这里可以 关闭加载圈等
         */
        callBack.onFinish();
    }

}
