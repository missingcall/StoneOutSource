package com.jilsfdivbvetwo.jlsat167;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    private PopupWindow loadingPopup;


    protected abstract void fillWidget();

    protected abstract View getActivityLayoutView();

    protected void init() {
    }

    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void loadData();

    protected abstract void obtainData();

    protected void setFlag() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // tim.com.libnetwork.base.HBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFlag();


        initWindow();
        setContentView(R.layout.layout_web);

        init();
        obtainData();
        initViews(savedInstanceState);
        fillWidget();
        getWindow().getDecorView().post(new Runnable() { // from class: tim.com.libnetwork.base.BaseActivity.1
            @Override // java.lang.Runnable
            public void run() {
                BaseActivity.this.loadData();
            }
        });
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();

    }

    private void initWindow() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(1024, 1024);
        getWindow().setBackgroundDrawable(null);

    }


}