package com.giftedcat.httplib.net;

import com.giftedcat.httplib.model.BaseResponse;
import com.giftedcat.httplib.utils.ConstantIntValue;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 对返回code不正确的进行处理
 * @author GiftedCat
 * @date 17/2/20
 */

public class MagicBoxObservableCallHelper<T extends BaseResponse> {

    public  Observable<T> map(Observable<T> call) {
        return call.subscribeOn(Schedulers.newThread()).observeOn(Schedulers.io()).flatMap(new Function<T, ObservableSource<T>>() {
            @Override
            public ObservableSource<T> apply(T baseResponse) throws Exception {
                if (getCodeState(baseResponse.getCode())) {
                    return Observable.just(baseResponse);
                } else {
                    return Observable.error(new OkHttpException(baseResponse.getCode(), baseResponse.getMsg()));
                }
            }
        });
    }

    /**
     * @param code
     * @return 根据code得出接口调用是否成功
     */
    private boolean getCodeState(int code) {
        if (code == ConstantIntValue.ZERO ||
                code == ConstantIntValue.TWO_HUNDRED) {
            return true;
        } else {
            return false;
        }
    }
}
