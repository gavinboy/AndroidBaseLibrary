package com.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class FixListView extends ListView {

	public FixListView(Context context) {
		super(context);
	}

	public FixListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
