package com.troy.sendcard.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by chenlongfei on 16/4/2.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
    }

    protected abstract int getContentView();

    protected void go(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        finish();
    }

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
