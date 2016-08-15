package com.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;


public class FlowLayout extends ViewGroup {
    private static final String TAG = FlowLayout.class.getSimpleName();

    private List<Integer> mLineHieght = new ArrayList<Integer>();

    private List<List<View>> mAllViews = new ArrayList<List<View>>();

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        // warp_content
        int width = 0, height = 0;
        int lineWidth = 0, lineHeight = 0;
        int viewCount = getChildCount();
        for (int i = 0; i < viewCount; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

            MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();
            int childWidth = childView.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            int childHeight = childView.getMeasuredHeight() + params.topMargin + params.bottomMargin;

            if (childWidth + lineWidth > widthSize) {
                // 开始新的一行，重置宽高
                width = Math.max(lineWidth, width);
                lineWidth = childWidth;
                // 记录行高
                height += lineHeight;
                lineHeight = childHeight;
            } else {
                // 没有换行, 追加行宽
                lineWidth += childWidth;
                lineHeight = Math.max(childHeight, lineHeight);
            }

            // 追加最后一行
            if (i == viewCount - 1) {
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }
        }

        int sizeWidth = (widthMode == MeasureSpec.EXACTLY ? widthSize : width);
        int sizeHeight = (heightMode == MeasureSpec.EXACTLY ? heightSize : height);

        setMeasuredDimension(sizeWidth, sizeHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mAllViews.clear();
        mLineHieght.clear();

        int width = getWidth();
        int lineWidth = 0, lineHeight = 0;
        int viewCount = getChildCount();
        List<View> lineViews = new ArrayList<View>();

        for (int i = 0; i < viewCount; i++) {
            View childView = getChildAt(i);
            MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            // 开启新的一行
            if (lineWidth + childWidth + params.leftMargin + params.rightMargin > width) {
                mLineHieght.add(lineHeight);
                mAllViews.add(lineViews);

                lineWidth = 0;
                lineHeight = childHeight + params.topMargin + params.bottomMargin;
                lineViews = new ArrayList<View>();
            }

            lineWidth += childWidth + params.leftMargin + params.rightMargin;
            lineHeight = Math.max(lineHeight, childHeight + params.topMargin + params.bottomMargin);
            lineViews.add(childView);
        }
        // 添加最后一行
        mAllViews.add(lineViews);
        mLineHieght.add(lineHeight);

        int left = 0, top = 0;
        int lineNum = mAllViews.size();
        for (int i = 0; i < lineNum; i++) {
            lineViews = mAllViews.get(i);
            lineHeight = mLineHieght.get(i);

            for (int j = 0; j < lineViews.size(); j++) {
                View childView = lineViews.get(j);
                if (childView.getVisibility() == GONE)
                    continue;

                MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();
                // 对view进行布局
                int lc = left + params.leftMargin;
                int tc = top + params.topMargin;
                int rc = lc + childView.getMeasuredWidth();
                int bc = tc + childView.getMeasuredHeight();
                childView.layout(lc, tc, rc, bc);

                left += childView.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            }

            left = 0;
            top += lineHeight;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}

