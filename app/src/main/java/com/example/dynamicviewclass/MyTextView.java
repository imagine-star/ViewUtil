package com.example.dynamicviewclass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myview.R;

import java.util.List;

/**
 * @Author LiuHaoQi
 * @Description 自动化生成的显示View
 * @Date 2023/3/29 9:31
 */
public class MyTextView extends MyView {

    /*
     * 必填项，上下文
     * */
    private Context context;
    /*
     * 必填项，左边展示的文字
     * */
    private String name = "";
    /*
     * 必填项，服务器传输对应的key
     * */
    private String serviceKey = "";
    /*
     * 非必填，是否必填
     * */
    private boolean needEdit = false;
    /*
     * 非必填，是否显示必填红点
     * */
    private boolean needShowRed = false;
    /*
     * 非必填，右边无内容时展示的内容
     * */
    private String hintName = "";
    /*
     * 非必填，点击事件
     * */
    private View.OnClickListener listener = null;
    /*
     * 非必填，右边内容的值
     * showValue：需要上传显示的值时，用此值
     * codeValue：需要上传显示对应的code时，用此值
     * */
    private String showValue;
    private String codeValue;

    public final static int SHOW_VALUE = 1;
    public final static int CODE_VALUE = 2;
    /*
     * 设定一个特殊值，用来判断此textView获取的是显示值还是value
     * */
    private int valueType = SHOW_VALUE;

    private TextView textView;
    private LinearLayout linearLayout;

    public MyTextView(Context context) {
        this.context = context;
    }

    /**
     * 必填项，服务器传输对应的key
     * @param serviceKey
     * @return
     */
    public MyTextView serviceKey(String serviceKey) {
        this.serviceKey = serviceKey;
        return this;
    }

    /**
     * 必填项，左边展示的文字
     * @param name
     * @return
     */
    public MyTextView name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 非必填，右边内容的值
     * showValue：显示的值
     * @param value
     * @return
     */
    public MyTextView value(String name, String code) {
        showValue = name;
        codeValue = code;
        return this;
    }

    /**
     * 非必填，右边无内容时展示的内容
     * @param hintName
     * @return
     */
    public MyTextView hintName(String hintName) {
        this.hintName = hintName;
        return this;
    }

    /**
     * 非必填，是否必填，默认非必填
     * @param needEdit
     * @return
     */
    public MyTextView needEdit(boolean needEdit) {
        this.needEdit = needEdit;
        return this;
    }

    /**
     * 非必填，是否显示必填红点，默认不显示
     * @param needShowRed
     * @return
     */
    public MyTextView needShowRed(boolean needShowRed) {
        this.needShowRed = needShowRed;
        return this;
    }

    public MyTextView listener(View.OnClickListener listener) {
        this.listener = listener;
        return this;
    }

    public MyTextView build() {
        if (TextUtils.isEmpty(name)) {
            return null;
        }
        if (TextUtils.isEmpty(serviceKey)) {
            return null;
        }
        drawView();
        return this;
    }

//    public MyTextView(Context context, String serviceKey, String name) {
//        this(context, serviceKey, name, "", "", false, false, null);
//    }
//
//    public MyTextView(Context context, String serviceKey, String name, View.OnClickListener listener) {
//        this(context, serviceKey, name, "", "", false, false, listener);
//    }
//
//    public MyTextView(Context context, String serviceKey, String name, String value, String hintName, boolean needEdit, boolean needShowRed, View.OnClickListener listener) {
//        this.context = context;
//        this.serviceKey = serviceKey;
//        this.name = name;
//        this.showValue = value;
//        this.hintName = hintName;
//        this.needShowRed = needShowRed;
//        this.listener = listener;
//        drawView(context, name, value, hintName, needShowRed, listener);
//        this.needEdit = needEdit;
//    }

    @Override
    public String getShowName() {
        return name;
    }

    @Override
    public void setValue(String name, String code) {
        showValue = name;
        codeValue = code;
        if (textView != null) {
            textView.setText(name);
        }
    }

    @Override
    public void setValue(List<String> list) {

    }

    public String getShowValue() {
        return showValue;
    }

    public String getCodeValue() {
        return codeValue;
    }

    @Override
    public Object getValue() {
        return getCodeValue();
    }

    @Override
    public View getView() {
        return linearLayout;
    }

    @Override
    public boolean getNeedEdit() {
        return needEdit;
    }

    @Override
    public String getServiceKey() {
        return serviceKey;
    }

    public void setValueType(int valeType) {
        this.valueType = valeType;
    }

    @SuppressLint("SetTextI18n")
    private void drawView() {
        linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        linearLayout.setBackgroundColor(context.getResources().getColor(R.color.white));
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout childLin = new LinearLayout(context);
        LinearLayout.LayoutParams linParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        childLin.setLayoutParams(linParams);
        childLin.setGravity(Gravity.CENTER_VERTICAL);
        childLin.setOrientation(LinearLayout.HORIZONTAL);

        TextView textView = new TextView(context);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        if (needShowRed) {
            textParams.setMargins(DensityUtil.dip2px(context, 12), 0, 0, 0);
            Drawable drawable = context.getResources().getDrawable(R.drawable.icon_xing);
            drawable.setBounds(0, 0, DensityUtil.dip2px(context, 10), DensityUtil.dip2px(context, 10));
            textView.setCompoundDrawables(drawable, null, null, null);
        } else {
            textParams.setMargins(DensityUtil.dip2px(context, 23), 0, 0, 0);
        }

        textView.setLayoutParams(textParams);
        textView.setTextColor(context.getResources().getColor(R.color.text_color));
        textView.setTextSize(14);
        textView.setText(name + "：");
        childLin.addView(textView);

        TextView showText = new TextView(context);
        LinearLayout.LayoutParams editParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        showText.setLayoutParams(editParams);
        showText.setPadding(0, DensityUtil.dip2px(context, 15), DensityUtil.dip2px(context, 23), DensityUtil.dip2px(context, 15));
        showText.setSingleLine(true);
        showText.setOnClickListener(listener);
        showText.setTextColor(context.getResources().getColor(R.color.text_color));
        showText.setTextSize(14);
        showText.setText(showValue);
        showText.setHint(hintName);
        this.textView = showText;
        childLin.addView(showText);

        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageParams.setMargins(0, 0, DensityUtil.dip2px(context, 23), 0);
        imageView.setLayoutParams(imageParams);
        imageView.setBackgroundResource(R.drawable.next_step);
        childLin.addView(imageView);

        linearLayout.addView(childLin);

        View line = new View(context);
        LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(context, 0.5f));
        lineParams.setMargins(DensityUtil.dip2px(context, 23), 0, DensityUtil.dip2px(context, 23), 0);
        line.setLayoutParams(lineParams);
        line.setBackgroundColor(context.getResources().getColor(R.color.divider));
        linearLayout.addView(line);
    }

}
