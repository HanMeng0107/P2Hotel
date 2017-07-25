package com.hejunlin.liveplayback.inter;

import android.view.View;

/**
 * @author HM DateTime 2017��3��14�� ����9:04:57
 * @version 1.0
 */
public interface IMetroViewBorder {

	void onFocusChanged(View target, View oldFocus, View newFocus);

	void onScrollChanged(View target, View attachView);

	void onLayout(View target, View attachView);

	void onTouchModeChanged(View target, View attachView, boolean isInTouchMode);

	void onAttach(View target, View attachView);

	void OnDetach(View targe, View view);

}
