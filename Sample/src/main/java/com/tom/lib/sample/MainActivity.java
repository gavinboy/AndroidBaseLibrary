package com.tom.lib.sample;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.tom.lib.sample.databinding.ActivityMainBinding;
import com.tom.lib.sample.dialog.DialogSampleActivity;
import com.tom.lib.sample.indicator.IndicatorActivity;
import com.tom.lib.sample.ptr.PtrActivity;

public class MainActivity extends Activity {
    private ActivityMainBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        mBinding.setListener(this);
    }

    public void showDialogActivity(View mView){
        Intent intent=new Intent(this, DialogSampleActivity.class);
        this.startActivity(intent);

    }

    public void showIndicatorActivity(View mView){
        Intent intent=new Intent(this, IndicatorActivity.class);
        this.startActivity(intent);
    }

    public void showPtrActivity(View mView){
        Intent intent=new Intent(this, PtrActivity.class);
        this.startActivity(intent);
    }
}
