package com.hejunlin.liveplayback.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 扩展Gridview.必需嵌套滚动窗口.
 * @author hailongqiu
 *
 */
public class MyExpendGridView extends GridView {
	public MyExpendGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyExpendGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
