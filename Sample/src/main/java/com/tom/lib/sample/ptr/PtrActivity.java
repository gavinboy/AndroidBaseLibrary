package com.tom.lib.sample.ptr;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.tom.lib.sample.R;
import com.tom.lib.sample.databinding.PtrBinding;

/**
 * Author:yuanzeyao<br/>
 * Date:16/7/21 下午5:39
 * Email:yuanzeyao@qiyi.com
 */
public class PtrActivity extends Activity {
    private PtrBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this, R.layout.ptr_activity);
        mBinding.setPresenter(this);
    }

    public void showSimplePtr(View mView){
        Intent mIntent=new Intent(this,SimplePtrActivity.class);
        this.startActivity(mIntent);
    }

}
