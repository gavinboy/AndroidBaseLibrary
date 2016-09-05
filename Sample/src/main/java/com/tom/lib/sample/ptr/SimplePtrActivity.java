package com.tom.lib.sample.ptr;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.base.listview.BaseAdapterHelper;
import com.base.listview.QuickAdapter;
import com.base.ptr.PtrClassicFrameLayout;
import com.base.ptr.PtrDefaultHandler;
import com.base.ptr.PtrFrameLayout;
import com.base.ptr.PtrHandler;
import com.base.utils.LocalDisplay;
import com.tom.lib.sample.R;
import com.tom.lib.sample.databinding.SimpleDataBind;
import com.tom.lib.sample.net.NetManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author:yuanzeyao<br/>
 * Date:16/7/21 下午5:45
 * Email:yuanzeyao@163.com
 */
public class SimplePtrActivity extends Activity {
    private int sGirdImageSize;
    private SimpleDataBind mBind;
    private PtrClassicFrameLayout mPtrFrame;
    private GridView mGridView;
    private QuickAdapter<String> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind= DataBindingUtil.setContentView(this, R.layout.simple_prt_activity);
        mPtrFrame=mBind.rotateHeaderGridViewFrame;
        mGridView=mBind.rotateHeaderGridView;
        LocalDisplay.init(this);
        sGirdImageSize =(LocalDisplay.SCREEN_WIDTH_PIXELS - LocalDisplay.dp2px(12 + 12 + 10)) / 2;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SimplePtrActivity.this,"position:"+position,Toast.LENGTH_LONG).show();
            }
        });

        mAdapter=new QuickAdapter<String>(this,R.layout.with_grid_view_item_image_list_grid) {
            @Override
            protected void convert(BaseAdapterHelper helper, String item) {
                ImageView imageView=helper.getView(R.id.with_grid_view_item_image);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                LinearLayout.LayoutParams lyp = new LinearLayout.LayoutParams(sGirdImageSize, sGirdImageSize);
                imageView.setLayoutParams(lyp);
                helper.setImageUrl(R.id.with_grid_view_item_image,item);
            }
        };

        mGridView.setAdapter(mAdapter);

        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame,content,header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                sendRequest();
            }
        });
    }

    private void sendRequest() {

        Call<List<String>> imagesCalls= NetManager.getInstance().create().getImageList();
        imagesCalls.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                mAdapter.clear();
                mAdapter.addAll(response.body());
                mPtrFrame.refreshComplete();
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.d("yzy","onFailure:"+t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
