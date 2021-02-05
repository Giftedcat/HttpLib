package com.giftedcat.httplib.net;

import android.content.Context;

import com.giftedcat.httplib.model.BaseResponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author：GiftedCat
 */

public class RetrofitClient {

  /**
   * @param mContext 上下文对象
   * @param observable 被观察者
   * @param callBack 回调接口
   * @param <T> 实体类
   */
  public static <T extends BaseResponse> void request(Context mContext, Observable<T> observable,
                                                      final IResponseCallBack<T> callBack) {
    observable.subscribeOn(Schedulers.io())//指定Observable自身在io线程中执行
        .observeOn(AndroidSchedulers.mainThread())//指定一个观察者在主线程中国观察这个Observable
        .subscribe(new CallBackObserver<T>(mContext, callBack));//订阅

  }

}
