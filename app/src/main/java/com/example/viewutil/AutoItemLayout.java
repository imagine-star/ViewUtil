package com.example.viewutil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class AutoItemLayout extends LinearLayout {

    public AutoItemLayout(Context context) {
        this(context, null);
    }

    public AutoItemLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private int getViewWidth(View view) {
        int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(spec, spec);
        return view.getMeasuredWidth();
    }

    private int getViewHeight(View view) {
        int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(spec, spec);
        return view.getMeasuredHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int count = getChildCount();
        final int parentLong = getMeasuredWidth();
        int measureHeight = 0;
        int lastRight = 0;
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                final LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
                if (i == 0) {
                    lastRight = layoutParams.getMarginStart() + getViewWidth(child) + layoutParams.getMarginEnd();
                } else {
                    if ((lastRight + layoutParams.getMarginStart() + getViewWidth(child) + layoutParams.getMarginEnd()) > parentLong) {
                        measureHeight = measureHeight + layoutParams.topMargin + getViewHeight(child) + layoutParams.bottomMargin;
                        lastRight = layoutParams.getMarginStart() + getViewWidth(child) + layoutParams.getMarginEnd();
                    } else {
                        lastRight = lastRight + layoutParams.getMarginStart() + getViewWidth(child) + layoutParams.getMarginEnd();
                    }
                }
            }
        }
        setMeasuredDimension(getMeasuredWidth(), measureHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        View lastChild = null;
        int lastRight = 0;
        int lastBottom = 0;
        int left, top, right, bottom;
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                final LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
                if (lastChild == null) {
                    left = layoutParams.getMarginStart();
                    top = layoutParams.topMargin;
                    right = layoutParams.getMarginStart() + getViewWidth(child);
                    bottom = layoutParams.topMargin + getViewHeight(child);
                    lastRight = layoutParams.getMarginStart() + getViewWidth(child) + layoutParams.getMarginEnd();
                } else {
                    int childLong = lastRight + layoutParams.getMarginStart() + getViewWidth(child) + layoutParams.getMarginEnd();
                    if (childLong > getMeasuredWidth()) {
                        lastBottom = lastBottom + layoutParams.topMargin + getViewHeight(child) + layoutParams.bottomMargin;
                        left = layoutParams.getMarginStart();
                        top = lastBottom + layoutParams.topMargin;
                        right = layoutParams.getMarginStart() + getViewWidth(child);
                        bottom = lastBottom + layoutParams.topMargin + getViewHeight(child);
                        lastRight = layoutParams.getMarginStart() + getViewWidth(child) + layoutParams.getMarginEnd();
                    } else {
                        left = lastRight + layoutParams.getMarginStart();
                        top = lastBottom + layoutParams.topMargin;
                        right = lastRight + layoutParams.getMarginStart() + getViewWidth(child);
                        bottom = lastBottom + layoutParams.topMargin + getViewHeight(child);
                        lastRight = lastRight + layoutParams.getMarginStart() + getViewWidth(child) + layoutParams.getMarginEnd();
                    }
                }
                child.layout(left, top, right, bottom);
                lastChild = child;
            }
        }
    }

}
