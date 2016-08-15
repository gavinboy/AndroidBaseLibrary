package com.base.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 是否允许滑动
 * <p/>
 */
public class ExtendViewPager extends ViewPager {

    private boolean isCanScroll = false;

    public ExtendViewPager(Context context) {
        super(context);
    }

    public ExtendViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        try {
            if (isCanScroll) {
                return super.onTouchEvent(arg0);
            }
        } catch (Exception ex) {
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        try {
            if (isCanScroll) {
                return super.onInterceptTouchEvent(arg0);
            }
        } catch (Exception ex) {
        }
        return false;
    }
}
