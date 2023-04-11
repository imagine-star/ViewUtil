package com.example.dynamicviewclass;

import android.view.View;

/**
 * @Author LiuHaoQi
 * @Description 所有自动化生成视图的基类
 * @Date 2023/3/29 9:22
 */
public abstract class MyView {

    /**
     * @return 继承此函数用来获取显示名
     */
    public abstract String getShowName();

    /**
     * 当外部值发生改变时，调用此函数以改变内部显示（TextView做选择项时用的比较多）
     * 为了兼容获取显示值和对应code值，此处要求同时传入name和code
     *
     * @param name：传入的显示值
     * @param code：传入显示值对应的code
     */
    public abstract void setValue(String name, String code);

//    /*
//     * @author: LiuHaoQi
//     * @description: 继承此函数用来获取需要上传的值(显示用)
//     * @date: 2023/3/29 9:29
//     */
//    public abstract String getShowValue();

    /**
     * @return 继承此函数用来获取需要上传的值
     */
    public abstract Object getValue();

    /**
     * @return 继承此函数用来直接获取需要添加进显示的外层View
     */
    public abstract View getView();

    /**
     * @return 获取是否必填
     */
    public abstract boolean getNeedEdit();

    /**
     * @return 获取对应的服务器传输使用的key值
     */
    public abstract String getServiceKey();

}
