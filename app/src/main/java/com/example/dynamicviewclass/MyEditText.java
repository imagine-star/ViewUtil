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

/**
 * @Author LiuHaoQi
 * @Description 自动生成的EditText类
 * @Date 2023/3/29 10:21
 */
public class MyEditText extends MyView {

    /*
     * 必填项，上下文
     * */
    private Context context;
    /*
     * 必填项，左边展示的文字
     * */
    private String name;
    /*
     * 必填项，服务器传输对应的key
     * */
    private String serviceKey;
    /*
    * 非必填，输入类型
    * */
    private int inputType = InputType.TYPE_NULL;
    /*
     * 非必填，单位
     * */
    private String unit = "";
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
    private String hintName;
    /*
    * 非必填，最大输入长度
    * */
    private int maxLength = 0;
    /*
     * 非必填，右边内容的值
     * showValue：需要上传显示的值时，用此值
     * codeValue：需要上传显示对应的code时，用此值
     * */
    private String showValue;
    private String codeValue;

    private EditText editText;
    private LinearLayout linearLayout;

    public MyEditText(Context context, String serviceKey, String name) {
        this(context, serviceKey, name, "", "", "", true, false, false, 0);
    }

    public MyEditText(Context context, String serviceKey, String name, String hintName) {
        this(context, serviceKey, name, "", "", hintName, true, false, false, 0);
    }

    public MyEditText(Context context, String serviceKey, String name, String value, String unit, String hintName, boolean canEdit, boolean needEdit, boolean needShowRed, int maxLength) {
        this.context = context;
        this.serviceKey = serviceKey;
        this.name = name;
        this.showValue = value;
        this.unit = unit;
        this.hintName = hintName;
        this.canEdit = canEdit;
        this.needShowRed = needShowRed;
        this.maxLength = maxLength;
        drawView(context, name, value, unit, hintName, canEdit, needShowRed, maxLength);
        this.needEdit = needEdit;
    }
    
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
    public void drawView(Context context, String name, String value, String unit, String hintName, boolean canEdit, boolean needShowRed, int maxLength) {

        linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        linearLayout.setBackgroundColor(context.getResources().getColor(R.color.white));
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout childLin = new LinearLayout(context);
        LinearLayout.LayoutParams linParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        childLin.setLayoutParams(linParams);
        childLin.setGravity(Gravity.CENTER_VERTICAL);
        childLin.setOrientation(LinearLayout.HORIZONTAL);

        TextView textView = new TextView(context);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

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

        EditText editText = new EditText(context);
        LinearLayout.LayoutParams editParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        editText.setLayoutParams(editParams);
        editText.setPadding(0, DensityUtil.dip2px(context, 15),
                DensityUtil.dip2px(context, 23), DensityUtil.dip2px(context, 15));
        editText.setBackground(null);
        editText.setSingleLine(true);
        editText.setTextColor(context.getResources().getColor(R.color.text_color));
        editText.setTextSize(14);
        editText.setText(value);
        editText.setHint(hintName);
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
        childLin.addView(editText);

        if (!TextUtils.isEmpty(unit)) {
            TextView unitText = new TextView(context);
            LinearLayout.LayoutParams unitParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            unitParams.setMargins(0, 0, DensityUtil.dip2px(context, 23), 0);
            unitText.setLayoutParams(unitParams);
            unitText.setTextColor(context.getResources().getColor(R.color.text_color));
            unitText.setTextSize(14);
            unitText.setText(unit);
            childLin.addView(unitText);
        }

        linearLayout.addView(childLin);

        View line = new View(context);
        LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(context, 0.5f));
        lineParams.setMargins(DensityUtil.dip2px(context, 23), 0, DensityUtil.dip2px(context, 23), 0);
        line.setLayoutParams(lineParams);
        line.setBackgroundColor(context.getResources().getColor(R.color.divider));
        linearLayout.addView(line);

    }

}
