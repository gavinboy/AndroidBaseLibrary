package com.tom.lib.sample.ptr;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.tom.lib.sample.R;
import com.tom.lib.sample.databinding.SimpleDataBind;

/**
 * Author:yuanzeyao<br/>
 * Date:16/7/21 下午5:45
 * Email:yuanzeyao@163.com
 */
public class SimplePtrActivity extends Activity {
    private SimpleDataBind mBind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind= DataBindingUtil.setContentView(this, R.layout.simple_prt_activity);
    }
}
