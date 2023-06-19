package com.example.dynamicviewclass;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author LiuHaoQi
 * @Description
 * @Date 2023/6/19 16:48
 */
public class MyPictureView extends MyView {

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
     * 非必填，最大选择数量
     * */
    private int maxLength = 9;
    /*
     * 非必填，图片列表
     * */
    private List<String> pictureList = new ArrayList<>();

    /**
     * 是否是网络图片
     */
    private boolean netPicture;

    private LinearLayout linearLayout;

    private View.OnClickListener listener = null;
    private View.OnClickListener deleteClick = null;
    private PictureShowAdapter adapter = null;

    public MyPictureView(Context context) {
        this.context = context;
    }

    /**
     * 必填项，服务器传输对应的key
     * @param serviceKey
     * @return
     */
    public MyPictureView serviceKey(String serviceKey) {
        this.serviceKey = serviceKey;
        return this;
    }

    /**
     * 必填项，左边展示的文字
     * @param name
     * @return
     */
    public MyPictureView name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 非必填，图片列表
     * showValue：显示的值
     * @param pictureList
     * @return
     */
    public MyPictureView pictureList(List<String> pictureList) {
        this.pictureList = pictureList;
        return this;
    }

    /**
     * 非必填，右边无内容时展示的内容
     * @param hintName
     * @return
     */
    public MyPictureView hintName(String hintName) {
        this.hintName = hintName;
        return this;
    }

    /**
     * 非必填，能否编辑，默认可编辑
     * @param canEdit
     * @return
     */
    public MyPictureView canEdit(boolean canEdit) {
        this.canEdit = canEdit;
        return this;
    }

    /**
     * 非必填，是否必填，默认非必填
     * @param needEdit
     * @return
     */
    public MyPictureView needEdit(boolean needEdit) {
        this.needEdit = needEdit;
        return this;
    }

    /**
     * 非必填，是否显示必填红点，默认不显示
     * @param needShowRed
     * @return
     */
    public MyPictureView needShowRed(boolean needShowRed) {
        this.needShowRed = needShowRed;
        return this;
    }

    /**
     * 非必填，最大选择数量，默认为9
     * @param maxLength
     * @return
     */
    public MyPictureView maxLength(int maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public MyPictureView netPicture(boolean netPicture) {
        this.netPicture = netPicture;
        return this;
    }

    public MyPictureView listener(View.OnClickListener listener) {
        this.listener = listener;
        return this;
    }

    public MyPictureView deleteClick(View.OnClickListener deleteClick) {
        this.deleteClick = deleteClick;
        return this;
    }

    public MyPictureView build() {
        if (TextUtils.isEmpty(name)) {
            return null;
        }
        if (TextUtils.isEmpty(serviceKey)) {
            return null;
        }
        drawView();
        return this;
    }

    private void drawView() {
        linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams linParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(linParams);
        linearLayout.setBackgroundColor(context.getResources().getColor(R.color.white));
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView textView = new TextView(context);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textParams.setMargins(DensityUtil.dip2px(context, 23), DensityUtil.dip2px(context, 15),
                0, DensityUtil.dip2px(context, 15));
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

        RecyclerView recyclerView = new RecyclerView(context);
        LinearLayout.LayoutParams recyclerParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        recyclerParams.setMargins(DensityUtil.dip2px(context, 23), 0, DensityUtil.dip2px(context, 23), 0);
        recyclerView.setLayoutParams(recyclerParams);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
        recyclerView.setLayoutManager(layoutManager);
        if (canEdit) {
            adapter = new PictureShowAdapter(context, pictureList, maxLength, listener, deleteClick, netPicture);
        } else {
            adapter = new PictureShowAdapter(context, pictureList, maxLength, null, null, netPicture);
        }
        recyclerView.setAdapter(adapter);
        linearLayout.addView(recyclerView);
    }
    
    @Override
    public String getShowName() {
        return name;
    }

    @Override
    public void setValue(String name, String code) {

    }

    @Override
    public Object getValue() {
        return pictureList;
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

    public PictureShowAdapter getAdapter() {
        return adapter;
    }
}
