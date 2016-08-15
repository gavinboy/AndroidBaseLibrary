package com.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class FixGridView extends GridView {

	public FixGridView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public FixGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
	    int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
	    super.onMeasure(widthMeasureSpec, expandSpec);  
	}
}
