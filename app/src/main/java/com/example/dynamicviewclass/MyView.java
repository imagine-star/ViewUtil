package com.example.dynamicviewclass;

import android.view.View;

import java.util.List;

/**
 * @Author LiuHaoQi
 * @Description 所有自动化生成视图的基类
 * @Date 2023/3/29 9:22
 */
public abstract class MyView {

    /*
     * @author: LiuHaoQi
     * @description: 继承此函数用来获取显示名
     * @date: 2023/3/29 9:30
     */
    public abstract String getShowName();

    /*
     * @author: LiuHaoQi
     * @description: 当外部值发生改变时，调用此函数以改变内部显示（TextView做选择项时用的比较多）
     * 为了兼容获取显示值和对应code值，此处要求同时传入name和code
     * @date: 2023/3/29 9:43
     */
    public abstract void setValue(String name, String code);

    public abstract void setValue(List<String> list);

//    /*
//     * @author: LiuHaoQi
//     * @description: 继承此函数用来获取需要上传的值(显示用)
//     * @date: 2023/3/29 9:29
//     */
//    public abstract String getShowValue();

    /*
     * @author: LiuHaoQi
     * @description: 继承此函数用来获取需要上传的值
     * @date: 2023/3/29 9:29
     */
    public abstract Object getValue();

    /*
     * @author: LiuHaoQi
     * @description: 继承此函数用来直接获取需要添加进显示的外层View
     * @date: 2023/3/29 9:29
     */
    public abstract View getView();

    /*
     * @author: LiuHaoQi
     * @description: 获取是否必填
     * @date: 2023/3/29 10:02
     */
    public abstract boolean getNeedEdit();

    /*
     * @author: LiuHaoQi
     * @description: 获取对应的服务器传输使用的key值
     * @date: 2023/3/29 12:18
     */
    public abstract String getServiceKey();

}