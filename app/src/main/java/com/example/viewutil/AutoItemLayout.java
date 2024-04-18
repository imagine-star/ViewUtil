package com.example.viewutil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/*
 * @author: LiuHaoQi
 * @description: 根据屏幕宽度自动换行的容器View，用来解决GridLayout无法灵活展示长度不一的标签的问题，（动态添加子项）使用时，正常addView即可，无需设置参数
 * @date: 2022/12/29 14:44
 */
public class AutoItemLayout extends LinearLayout {

    public AutoItemLayout(Context context) {
        this(context, null);
    }

    public AutoItemLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private int getViewWidth(View view) {
        int spec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        view.measure(spec, spec);
        return view.getMeasuredWidth();
    }

    private int getViewHeight(View view) {
        int spec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        view.measure(spec, spec);
        return view.getMeasuredHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int count = getChildCount();
        int measureHeight = 0;
        int lastRight = 0;
        int tempHeight = 0;
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                final LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
                int childLong = lastRight + layoutParams.getMarginStart() + getViewWidth(child) + layoutParams.getMarginEnd();
                if (childLong > getMeasuredWidth()) {
                    measureHeight = measureHeight + tempHeight;
                    tempHeight = 0;
                    lastRight = layoutParams.getMarginStart() + getViewWidth(child) + layoutParams.getMarginEnd();
                    int compareHeight = layoutParams.topMargin + getViewHeight(child) + layoutParams.bottomMargin;
                    tempHeight = Math.max(compareHeight, tempHeight);
                } else {
                    int compareHeight = layoutParams.topMargin + getViewHeight(child) + layoutParams.bottomMargin;
                    tempHeight = Math.max(compareHeight, tempHeight);
                    lastRight = lastRight + layoutParams.getMarginStart() + getViewWidth(child) + layoutParams.getMarginEnd();
                }
            }
        }
        if (tempHeight != 0) {
            measureHeight += tempHeight;
        }
        setMeasuredDimension(getMeasuredWidth(), measureHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        int lastRight = 0;
        int lastBottom = 0;
        int left, top, right, bottom;
        int tempHeight = 0;
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                final LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
                int childLong = lastRight + layoutParams.getMarginStart() + getViewWidth(child) + layoutParams.getMarginEnd();
                if (childLong > getMeasuredWidth()) {
                    lastBottom = lastBottom + tempHeight;
                    tempHeight = 0;
                    left = layoutParams.getMarginStart();
                    top = lastBottom + layoutParams.topMargin;
                    right = layoutParams.getMarginStart() + getViewWidth(child);
                    bottom = lastBottom + layoutParams.topMargin + getViewHeight(child);
                    lastRight = layoutParams.getMarginStart() + getViewWidth(child) + layoutParams.getMarginEnd();

                    int compareHeight = layoutParams.topMargin + getViewHeight(child) + layoutParams.bottomMargin;
                    tempHeight = Math.max(compareHeight, tempHeight);
                } else {
                    left = lastRight + layoutParams.getMarginStart();
                    top = lastBottom + layoutParams.topMargin;
                    right = lastRight + layoutParams.getMarginStart() + getViewWidth(child);
                    bottom = lastBottom + layoutParams.topMargin + getViewHeight(child);
                    lastRight = lastRight + layoutParams.getMarginStart() + getViewWidth(child) + layoutParams.getMarginEnd();

                    int compareHeight = layoutParams.topMargin + getViewHeight(child) + layoutParams.bottomMargin;
                    tempHeight = Math.max(compareHeight, tempHeight);
                }
                child.layout(left, top, right, bottom);
            }
        }
    }

}
