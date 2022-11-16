package com.giftedcat.demo.presenter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.giftedcat.beammvp.bijection.BeamBasePresenter;
import com.giftedcat.httplib.net.Injection;
import com.giftedcat.httplib.repository.GeneralRepository;
import com.giftedcat.httplib.repository.UserRepository;


public class BasePresenter <T> extends BeamBasePresenter<T> {

    public UserRepository userRepository;
    public GeneralRepository generalRepository;

    @Override
    protected void onCreate(@NonNull T view, Bundle savedState) {
        super.onCreate(view, savedState);

        userRepository = Injection.provideUserRepository((Context) view);
        generalRepository = Injection.provideGeneralRepository((Context) view);
    }

}
