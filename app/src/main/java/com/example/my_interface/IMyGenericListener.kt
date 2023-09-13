package com.example.my_interface

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

/**
 * 通用适配器的接口，用来传入viewBinding和对viewBinding进行操作
 */
interface IMyGenericListener {

    fun setViewBinding(parent: ViewGroup): ViewBinding

    fun getViewBinding(viewBinding: ViewBinding, position: Int)

}