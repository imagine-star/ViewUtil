package com.example.dynamicviewclass;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

public class DensityUtil {
    /**
     * Transform dip to px.
     */
    public static int dip2px(Context context, float dipValue) {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, r.getDisplayMetrics());
    }

    /**
     * Transform px to px.
     */
    public static int px2dip(Context context, int pxValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pxValue,
                context.getResources().getDisplayMetrics());
    }

    /**
     * Transform px to sp.
     */
    public static int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * Transform sp to px.
     */
    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
