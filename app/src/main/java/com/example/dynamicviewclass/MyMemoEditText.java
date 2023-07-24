package com.example.dynamicviewclass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myview.R;

import java.util.List;

/**
 * @Author LiuHaoQi
 * @Description 备注类特殊输入框通用View
 * @Date 2023/3/29 10:41
 */
public class MyMemoEditText extends MyView {

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
     * 非必填，输入类型
     * */
    private int inputType = InputType.TYPE_NULL;
    /*
     * 非必填，能否编辑
     * */
    private boolean canEdit = true;
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
     * 非必填，最大输入长度
     * */
    private int maxLength = 0;
    /*
     * 非必填，右边内容的值
     * showValue：需要上传显示的值时，用此值
     * codeValue：需要上传显示对应的code时，用此值
     * */
    private String showValue = "";
    private String codeValue = "";

    private EditText editText;
    private LinearLayout linearLayout;

    public MyMemoEditText(Context context) {
        this.context = context;
    }

    /**
     * 必填项，服务器传输对应的key
     * @param serviceKey
     * @return
     */
    public MyMemoEditText serviceKey(String serviceKey) {
        this.serviceKey = serviceKey;
        return this;
    }

    /**
     * 必填项，左边展示的文字
     * @param name
     * @return
     */
    public MyMemoEditText name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 非必填，右边内容的值
     * showValue：显示的值
     * @param value
     * @return
     */
    public MyMemoEditText value(String value) {
        this.showValue = value;
        return this;
    }

    /**
     * 非必填，右边无内容时展示的内容
     * @param hintName
     * @return
     */
    public MyMemoEditText hintName(String hintName) {
        this.hintName = hintName;
        return this;
    }

    /**
     * 非必填，能否编辑，默认可编辑
     * @param canEdit
     * @return
     */
    public MyMemoEditText canEdit(boolean canEdit) {
        this.canEdit = canEdit;
        return this;
    }

    /**
     * 非必填，是否必填，默认非必填
     * @param needEdit
     * @return
     */
    public MyMemoEditText needEdit(boolean needEdit) {
        this.needEdit = needEdit;
        return this;
    }

    /**
     * 非必填，是否显示必填红点，默认不显示
     * @param needShowRed
     * @return
     */
    public MyMemoEditText needShowRed(boolean needShowRed) {
        this.needShowRed = needShowRed;
        return this;
    }

    /**
     * 非必填，最大输入长度，默认为0不限制
     * @param maxLength
     * @return
     */
    public MyMemoEditText maxLength(int maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public MyMemoEditText build() {
        if (TextUtils.isEmpty(name)) {
            return null;
        }
        if (TextUtils.isEmpty(serviceKey)) {
            return null;
        }
        drawView();
        return this;
    }

//    public MyMemoEditText(Context context, String serviceKey, String name) {
//        this(context,serviceKey, name, "", "", true, false, false, 0);
//    }
//
//    public MyMemoEditText(Context context, String serviceKey, String name, String hintName) {
//        this(context, serviceKey, name, "", hintName, true, false, false, 0);
//    }
//
//    public MyMemoEditText(Context context, String serviceKey, String name, String value, String hintName, boolean canEdit, boolean needEdit, boolean needShowRed, int maxLength) {
//        this.context = context;
//        this.serviceKey = serviceKey;
//        this.name = name;
//        this.showValue = value;
//        this.hintName = hintName;
//        this.canEdit = canEdit;
//        this.needShowRed = needShowRed;
//        this.maxLength = maxLength;
//        drawView(context, name, value, hintName, canEdit, needShowRed, maxLength);
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
        if (editText != null) {
            editText.setText(name);
        }
    }

    @Override
    public void setValue(List<String> list) {

    }

    @Override
    public Object getValue() {
        if (editText != null) {
            return editText.getText().toString().trim();
        }
        return null;
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

    @SuppressLint("SetTextI18n")
    private void drawView() {

        linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        linearLayout.setBackgroundColor(context.getResources().getColor(R.color.white));
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView textView = new TextView(context);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (needShowRed) {
            textParams.setMargins(DensityUtil.dip2px(context, 12), DensityUtil.dip2px(context, 15), 0, 0);
            Drawable drawable = context.getResources().getDrawable(R.drawable.icon_xing);
            drawable.setBounds(0, 0, DensityUtil.dip2px(context, 10), DensityUtil.dip2px(context, 10));
            textView.setCompoundDrawables(drawable, null, null, null);
        } else {
            textParams.setMargins(DensityUtil.dip2px(context, 23), DensityUtil.dip2px(context, 15), 0, 0);
        }
        textView.setLayoutParams(textParams);
        textView.setTextColor(context.getResources().getColor(R.color.text_color));
        textView.setTextSize(14);
        textView.setText(name + "：");
        linearLayout.addView(textView);

        EditText editText = new EditText(context);
        LinearLayout.LayoutParams editParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(context, 100), 1);
        editParams.setMargins(DensityUtil.dip2px(context, 23), DensityUtil.dip2px(context, 10),
                DensityUtil.dip2px(context, 23), DensityUtil.dip2px(context, 15));
        editText.setLayoutParams(editParams);
        editText.setPadding(DensityUtil.dip2px(context, 10), DensityUtil.dip2px(context, 16),
                DensityUtil.dip2px(context, 10), DensityUtil.dip2px(context, 16));
        editText.setBackground(context.getResources().getDrawable(R.drawable.background_white_radius_blank));
        editText.setTextColor(context.getResources().getColor(R.color.text_color));
        editText.setTextSize(14);
        editText.setText(showValue);
        editText.setHint(hintName);
        editText.setGravity(Gravity.TOP|Gravity.LEFT);
        if (inputType != InputType.TYPE_NULL) {
            editText.setInputType(inputType);
        }
        if (maxLength != 0) {
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        }
        if (!canEdit) {
            editText.setEnabled(false);
        }
        this.editText = editText;
        linearLayout.addView(editText);

        View line = new View(context);
        LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(context, 0.5f));
        lineParams.setMargins(DensityUtil.dip2px(context, 23), 0, DensityUtil.dip2px(context, 23), 0);
        line.setLayoutParams(lineParams);
        line.setBackgroundColor(context.getResources().getColor(R.color.divider));
        linearLayout.addView(line);

    }

}