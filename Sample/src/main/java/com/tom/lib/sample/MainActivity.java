package com.tom.lib.sample;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.tom.lib.sample.databinding.ActivityMainBinding;
import com.tom.lib.sample.dialog.DialogSampleActivity;

public class MainActivity extends Activity {
    private ActivityMainBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        mBinding.setVariable(com.tom.lib.sample.BR.listener,this);
    }

    public void showDialogActivity(View mView){
        Intent intent=new Intent(this, DialogSampleActivity.class);
        this.startActivity(intent);

    }
}
