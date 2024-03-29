package com.giftedcat.demo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.giftedcat.beammvp.bijection.RequiresPresenter;
import com.giftedcat.demo.R;
import com.giftedcat.demo.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

@RequiresPresenter(MainPresenter.class)
public class MainActivity extends BaseActivity<MainPresenter> {

    Unbinder unbinder;

    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.et_pwd)
    EditText etPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
    }

    @OnClick({R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                /** 登录*/
                getPresenter().login(etPhoneNum.getText().toString(), etPwd.getText().toString());
                break;
            default:
                break;
        }
    }

}
