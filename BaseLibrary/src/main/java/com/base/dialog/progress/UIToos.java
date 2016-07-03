package com.base.dialog.progress;

import android.content.Context;

/**
 * Author:yuanzeyao<br/>
 * Date:16/7/3 下午3:27
 * Email:yuanzeyao@qiyi.com
 */
public class UIToos {
    private static float scale;

    public static int dpToPixel(float dp, Context context) {
        if (scale == 0) {
            scale = context.getResources().getDisplayMetrics().density;
        }
        return (int) (dp * scale);
    }
}
