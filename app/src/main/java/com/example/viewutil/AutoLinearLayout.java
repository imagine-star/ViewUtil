package com.example.viewutil;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.model.AutoViewDataModel;
import com.example.my_interface.IViewInsideClick;
import com.example.myview.R;

import java.util.ArrayList;
import java.util.List;

public class AutoLinearLayout extends LinearLayout {

    private final Context context;
    private List<AutoViewDataModel> viewList = new ArrayList<>();
    private int leftMargin = 10, topMargin = 5, rightMargin = 10, bottomMargin = 5;
    private int leftPadding = 7, topPadding = 3, rightPadding = 7, bottomPadding = 3;
    private int left = 10, right = 10;

    private IViewInsideClick viewInsideClick;

    public void setViewInsideClick(IViewInsideClick viewInsideClick) {
        this.viewInsideClick = viewInsideClick;
    }

    /*
    * 接收列表类型的View传入
    * */
    public void setData(List<AutoViewDataModel> viewList) {
        this.viewList = viewList;
        init();
    }

    /*
    * 接收View对象放入列表
    * */
    public void addData(AutoViewDataModel dataModel) {
        this.viewList.add(dataModel);
        init();
    }

    /*
    * 设置子项的左右边距（计算宽度使用）不传默认左右边距各占10dp
    * */
    public void setItemMargin(int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
        this.leftMargin = leftMargin;
        this.topMargin = topMargin;
        this.rightMargin = rightMargin;
        this.bottomMargin = bottomMargin;
        init();
    }

    public void setItemPadding(int leftPadding, int topPadding, int rightPadding, int bottomPadding) {
        this.leftPadding = leftPadding;
        this.topPadding = topPadding;
        this.rightPadding = rightPadding;
        this.bottomPadding = bottomPadding;
        init();
    }

    /*
    * 设置本View的左右边距（计算宽度使用）不传默认左右边距各占10dp
    * */
    public void setOutMargin(int left, int right) {
        this.left = left;
        this.right = right;
        init();
    }

    private int getPx(int dip) {
        return DensityUtil.dip2px(context, dip);
    }

    public AutoLinearLayout(Context context) {
        this(context, null);
    }

    public AutoLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private int getViewWidth(View view) {
        int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(spec, spec);
        return view.getMeasuredWidth();
    }

    private void init() {
        this.removeAllViews();
        this.setOrientation(VERTICAL);

        WindowManager windowManager = ((Activity) context).getWindowManager();
        int width = windowManager.getDefaultDisplay().getWidth() - getPx(left) - getPx(right);

        LinearLayout lineLin = new LinearLayout(context);
        LinearLayout.LayoutParams lineLinParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lineLin.setLayoutParams(lineLinParams);
        this.addView(lineLin);

        for (AutoViewDataModel dataModel : viewList) {

            TextView textView = new TextView(context);
            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textParams.setMargins(getPx(leftMargin), getPx(topMargin), getPx(rightMargin), getPx(bottomMargin));
            textView.setLayoutParams(textParams);
            textView.setPadding(getPx(leftPadding), getPx(topPadding), getPx(rightPadding), getPx(bottomPadding));
            textView.setBackground(DrawCorner.drawCorner(15, context.getColor(R.color.white)));
            textView.setTextSize(14);
            textView.setTextColor(context.getColor(R.color.blue));
            textView.setTag(dataModel.getId());
            textView.setText(dataModel.getName());
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewInsideClick != null) {
                        viewInsideClick.onChildClick(v);
                        viewInsideClick.getTag(v.getTag().toString());
                    }
                }
            });

            int lineLong = 0;
            for (int i = 0; i < lineLin.getChildCount(); i++) {
                View childView = lineLin.getChildAt(i);
                lineLong += getPx(leftMargin);
                lineLong += getPx(rightMargin);
                lineLong += getViewWidth(childView);
            }

            lineLong += getPx(leftMargin);
            lineLong += getPx(rightMargin);
            lineLong += getViewWidth(textView);

            if (lineLong > width) {
                lineLin = new LinearLayout(context);
                lineLinParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lineLin.setLayoutParams(lineLinParams);
                this.addView(lineLin);
            }

            lineLin.addView(textView);

        }
    }

}
