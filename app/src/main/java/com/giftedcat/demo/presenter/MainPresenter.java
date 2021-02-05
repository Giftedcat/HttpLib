package com.giftedcat.demo.presenter;

import com.giftedcat.demo.activity.MainActivity;
import com.giftedcat.demo.utils.ToastUtil;
import com.giftedcat.httplib.net.IResponseCallBack;
import com.giftedcat.httplib.net.OkHttpException;
import com.giftedcat.httplib.model.user.UserResponse;
import com.giftedcat.httplib.utils.DtLog;

import java.io.File;


public class MainPresenter extends BasePresenter<MainActivity>{

    /**
     * 账号密码登录
     */
    public void login(String login, String pwd) {
        userRepository.login(login, pwd, new IResponseCallBack<UserResponse>() {
            @Override
            public void onSuccess(UserResponse response) {
                DtLog.d("", "");
            }

            @Override
            public void onFailed(OkHttpException e) {
                ToastUtil.showToast(e.getEmsg());
            }
        });
    }

    /**
     * 上传头像
     */
    public void uploadImage(File file) {
        generalRepository.uploadImage(file, new IResponseCallBack() {
            @Override
            public void onSuccess(Object response) {

            }

            @Override
            public void onFailed(OkHttpException e) {
                ToastUtil.showToast(e.getEmsg());
            }
        });
    }

}
