package com.troy.sendcard.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.troy.sendcard.R;
import com.troy.sendcard.presenter.ILoginPresenter;
import com.troy.sendcard.presenter.impl.LoginPresenterImpl;
import com.troy.sendcard.view.LoginView;

import butterknife.Bind;

/**
 * Created by chenlongfei on 16/4/4.
 */
public class LoginActivity extends BaseActivity implements LoginView {
    @Bind(R.id.et_user_name)
    EditText mUserName;
    @Bind(R.id.et_pw)
    EditText mPassword;
    @Bind(R.id.btn_login)
    Button mLogin;
    private ProgressDialog mProgressDialog;
    private ILoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginPresenter = new LoginPresenterImpl(this, this);
        initView();
        initListener();
    }


    private void initView() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
    }

    private void initListener() {
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }


    private void submit() {
        mUserName.setError(null);
        mPassword.setError(null);
        if (TextUtils.isEmpty(getUserName())) {
            mUserName.setError(getString(R.string.username_empty_tips));
            mUserName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(getPassword())) {
            mPassword.setError(getString(R.string.password_empty_tips));
            mPassword.requestFocus();
            return;
        }
        mLoginPresenter.requestLogin(getUserName(), getPassword());
    }


    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void showLoading(String tips) {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.setMessage(tips);
            mProgressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        mProgressDialog.dismiss();
    }

    @Override
    public void showToast(String tips) {
        super.showToast(tips);
    }

    @Override
    public void loginFail() {

    }

    @Override
    public void loginSuccess() {
        go(MainActivity.class);
    }

    @Override
    public String getUserName() {
        return mUserName.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return mPassword.getText().toString().trim();
    }
}
