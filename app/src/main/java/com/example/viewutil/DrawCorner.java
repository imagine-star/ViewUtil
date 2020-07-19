package com.example.viewutil;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

public class DrawCorner {

    /*
    * 默认绘制圆角函数，背景白色，半径20
    * */
    public static GradientDrawable drawCorner() {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(20);
        drawable.setColor(Color.WHITE);
        return drawable;
    }

    /*
     * 默认绘制圆角函数，背景白色
     * 参数：
     * radius：圆角半径
     * */
    public static GradientDrawable drawCorner(int radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(radius);
        drawable.setColor(Color.WHITE);
        return drawable;
    }

    /*
     * 默认绘制圆角函数，背景白色
     * 参数：
     * radii：四个圆角半径，依次是左上、右上、右下、左下
     * */
    public static GradientDrawable drawCorner(float[] radii) {
        if (radii == null || radii.length != 8) {
            radii = new float[]{0, 0, 0, 0, 0, 0, 0, 0};
        }
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadii(radii);
        drawable.setColor(Color.WHITE);
        return drawable;
    }

    /*
     * 默认绘制圆角函数
     * 参数：
     * radius：圆角半径
     * color：背景色
     * */
    public static GradientDrawable drawCorner(int radius, int color) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(radius);
        drawable.setColor(color);
        return drawable;
    }

    /*
     * 默认绘制圆角函数
     * 参数：
     * radii：四个圆角半径，依次是左上x、左上y、右上x、右上y、右下x、右下y、左下x、左下y
     * color：背景色
     * */
    public static GradientDrawable drawCorner(float[] radii, int color) {
        if (radii == null || radii.length != 8) {
            radii = new float[]{0, 0, 0, 0, 0, 0, 0, 0};
        }
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadii(radii);
        drawable.setColor(color);
        return drawable;
    }

    /*
     * 默认绘制圆角函数
     * 参数：
     * radius：圆角半径
     * colors：渐变背景色
     * */
    public static GradientDrawable drawCorner(int radius, int[] colors) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(radius);
        drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        drawable.setColors(colors);
        return drawable;
    }

    /*
     * 默认绘制圆角函数
     * 参数：
     * radii：四个圆角半径，依次是左上x、左上y、右上x、右上y、右下x、右下y、左下x、左下y
     * colors：渐变背景色
     * */
    public static GradientDrawable drawCorner(float[] radii, int[] colors) {
        if (radii == null || radii.length != 8) {
            radii = new float[]{0, 0, 0, 0, 0, 0, 0, 0};
        }
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadii(radii);
        drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        drawable.setColors(colors);
        return drawable;
    }

    /*
     * 默认绘制圆角函数
     * 参数：
     * radius：圆角半径
     * colors：渐变背景色
     * orientation：渐变类型（ 0：上到下，1：右上到左下，2：右到左，3：右下到左上，4：下到上，5：左下到右上，6：左到右，7：左上到右下 ）
     * */
    public static GradientDrawable drawCorner(int radius, int[] colors, int orientation) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(radius);
        drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        switch (orientation) {
            case 0:
                drawable.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
                break;
            case 1:
                drawable.setOrientation(GradientDrawable.Orientation.TR_BL);
                break;
            case 2:
                drawable.setOrientation(GradientDrawable.Orientation.RIGHT_LEFT);
                break;
            case 3:
                drawable.setOrientation(GradientDrawable.Orientation.BR_TL);
                break;
            case 4:
                drawable.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
                break;
            case 5:
                drawable.setOrientation(GradientDrawable.Orientation.BL_TR);
                break;
            case 6:
                drawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
                break;
            case 7:
                drawable.setOrientation(GradientDrawable.Orientation.TL_BR);
                break;
        }
        drawable.setColors(colors);
        return drawable;
    }

    /*
     * 默认绘制圆角函数
     * 参数：
     * radii：四个圆角半径，依次是左上、右上、右下、左下
     * colors：渐变背景色
     * orientation：渐变类型（ 0：上到下，1：右上到左下，2：右到左，3：右下到左上，4：下到上，5：左下到右上，6：左到右，7：左上到右下 ）
     * */
    public static GradientDrawable drawCorner(float[] radii, int[] colors, int orientation) {
        if (radii == null || radii.length != 8) {
            radii = new float[]{0, 0, 0, 0, 0, 0, 0, 0};
        }
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadii(radii);
        drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        switch (orientation) {
            case 0:
                drawable.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
                break;
            case 1:
                drawable.setOrientation(GradientDrawable.Orientation.TR_BL);
                break;
            case 2:
                drawable.setOrientation(GradientDrawable.Orientation.RIGHT_LEFT);
                break;
            case 3:
                drawable.setOrientation(GradientDrawable.Orientation.BR_TL);
                break;
            case 4:
                drawable.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
                break;
            case 5:
                drawable.setOrientation(GradientDrawable.Orientation.BL_TR);
                break;
            case 6:
                drawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
                break;
            case 7:
                drawable.setOrientation(GradientDrawable.Orientation.TL_BR);
                break;
        }
        drawable.setColors(colors);
        return drawable;
    }

    /*
     * 默认绘制圆角函数
     * 参数：
     * radius：圆角半径
     * colors：渐变背景色
     * type：渐变类型（ GradientDrawable.LINEAR_GRADIENT：垂直渐变，GradientDrawable.SWEEP_GRADIENT：扫描式渐变，GradientDrawable.RADIAL_GRADIENT：圆形渐变 ）*/
//    public static GradientDrawable drawCorner(int radius, int[] colors, int type) {
//        GradientDrawable drawable = new GradientDrawable();
//        drawable.setCornerRadius(radius);
//        drawable.setGradientType(type);
//        drawable.setColors(colors);
//        return drawable;
//    }

}
