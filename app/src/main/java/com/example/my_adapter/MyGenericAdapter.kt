package com.example.my_adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.my_interface.IMyGenericListener

/**
 * 通用adapter，用接口传入viewBinding进行绑定，
 * 然后通过接口把viewBinding的操作开放给调用者，
 * 避免每次新建adapter的模板代码
 */
class MyGenericAdapter(list: MutableList<Any>, myGenericListener: IMyGenericListener) : RecyclerView.Adapter<MyGenericAdapter.MyHolder>() {

    private var list = mutableListOf<Any>()
    private var myGenericListener: IMyGenericListener
    init {
        this.list = list
        this.myGenericListener = myGenericListener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun upDate(list: MutableList<Any>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class MyHolder(viewBinding: ViewBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        var itemBinding: ViewBinding
        init {
            itemBinding = viewBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val viewBinding = myGenericListener.setViewBinding(parent)
        return MyHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        myGenericListener.getViewBinding(holder.itemBinding, position)
    }

}