package com.hejunlin.liveplayback.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by HM on 2017/3/14 21:12
 */

public class MyGridView extends GridView {

    public MyGridView(Context context) {
        this(context, null);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @Override
    public boolean isInTouchMode() {
        return !(hasFocus() && !super.isInTouchMode());
    }

    private void init(Context context, AttributeSet attrs) {
        this.setChildrenDrawingOrderEnabled(true);
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        if (this.getSelectedItemPosition() != -1) {
            if (i + this.getFirstVisiblePosition() == this.getSelectedItemPosition()) {// 这是原本要在最后一个刷新的item
                return childCount - 1;
            }
            if (i == childCount - 1) {// 这是最后一个需要刷新的item
                return this.getSelectedItemPosition() - this.getFirstVisiblePosition();
            }
        }
        return i;
    }

    public void setDefualtSelect(int pos) {
        requestFocusFromTouch();
        setSelection(pos);
    }

}
