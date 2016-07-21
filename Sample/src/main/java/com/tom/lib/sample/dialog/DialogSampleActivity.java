package com.tom.lib.sample.dialog;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.base.dialog.pop.ActionSheetDialog;
import com.base.dialog.pop.AlertDialog;
import com.base.dialog.progress.ProgressView;
import com.tom.lib.sample.R;
import com.tom.lib.sample.databinding.DialogLayoutBinding;

/**
 * Author:yuanzeyao<br/>
 * Date:16/7/3 下午3:43
 * Email:yuanzeyao@163.com
 */
public class DialogSampleActivity extends Activity {
    private DialogLayoutBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this, R.layout.dialog_layout);
        mBinding.setListener(this);
    }

    public void showSpinIndeterminateProgress(View mView){
        ProgressView.create(mView.getContext())
                .setStyle(ProgressView.Style.SPIN_INDETERMINATE)
                .setLabel("请稍后...")
                .setCancellable(true)
                .setAnimationSpeed(10)
                .setDimAmount(0.5f)
                .setSize(150,150)
                .show();
    }

    public void showPieIndeterminateProgress(View mView){
        ProgressView hud= ProgressView.create(mView.getContext())
                .setStyle(ProgressView.Style.PIE_DETERMINATE)
                .setLabel("Please wait");
        simulateProgressUpdate(hud);
        hud.show();

    }

    public void showAnnularDeterminateProgress(View mView){
        ProgressView hud = ProgressView.create(mView.getContext())
                .setStyle(ProgressView.Style.ANNULAR_DETERMINATE)
                .setLabel("Please wait")
                .setDetailsLabel("Downloading data");
        simulateProgressUpdate(hud);
        hud.show();
    }

    public void showBarDdeterminateProgress(View mView){
        ProgressView hud = ProgressView.create(mView.getContext())
                .setStyle(ProgressView.Style.BAR_DETERMINATE)
                .setLabel("Please wait");
        simulateProgressUpdate(hud);
        hud.show();
    }

    public void showCustomViewProgress(View mView){
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.ic_launcher);
        ProgressView hud = ProgressView.create(this)
                .setCustomView(imageView)
                .setLabel("This is a custom view");
        hud.show();
    }

    public void showDimBackgroundProgress(View mView){
        ProgressView hud = ProgressView.create(this)
                .setStyle(ProgressView.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f);
        hud.show();
    }

    public void showColorAnimateProgress(View mView){
        ProgressView hud = ProgressView.create(this)
                .setStyle(ProgressView.Style.SPIN_INDETERMINATE)
                .setWindowColor(getResources().getColor(R.color.colorPrimary))
                .setAnimationSpeed(2);
        hud.show();
    }

    public void showActionSheetDialog(View mView){
        new ActionSheetDialog(mView.getContext()).builder()
                .addSheetItem("支付宝", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {

                    }
                }).addSheetItem("微信", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {

            }
        }).show();
    }

    public void showAlertDialog(View mView){
        new AlertDialog(mView.getContext())
                .builder()
                .setTitle("提醒")
                .setMsg("今天连续登陆15天")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
        .show();
    }



    private void simulateProgressUpdate(final ProgressView hud) {
        hud.setMaxProgress(100);
        hud.setProgress(0);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            int currentProgress;
            @Override
            public void run() {
                currentProgress += 1;
                hud.setProgress(currentProgress);
                if (currentProgress == 80) {
                    hud.setLabel("Almost finish...");
                }
                if (currentProgress < 100) {
                    handler.postDelayed(this, 50);
                }
            }
        }, 100);
    }
}
