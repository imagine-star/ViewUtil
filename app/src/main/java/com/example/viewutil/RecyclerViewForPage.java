package com.example.viewutil;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_interface.OnChangePageListener;

public class RecyclerViewForPage extends RecyclerView {

    private OnChangePageListener changePageListener;

    public RecyclerViewForPage(@NonNull Context context) {
        super(context);
    }

    public RecyclerViewForPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerViewForPage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int pageNo = 1;
    //用来标记是否正在向上滑动
    private boolean isSlidingUp = false;

    /**
     * 重置当前页数据
     */
    public void resetPage() {
        pageNo = 1;
        changePageListener.onLoadData(pageNo);
    }

    /**
     * 为了实现在onResume函数中统一获取新数据/返回刷新数据
     * 在onResume函数中调用此函数即可
     */
    public void refreshPage() {
        int i = 1;
        while (i <= pageNo) {
            changePageListener.onLoadData(i);
            i++;
        }
    }

    /**
     * 主要用作数据刷新，分页情况下
     * 使用方法为在获取数据后判断当前获取页是否是最新页
     * 即调取本函数，传入当前页，返回true即为最新页
     * 如果是最新页，则刷新，否则不刷新
     * 此方法可解决返回刷新时回到顶部的问题
     * @param pageNo
     * @return
     */
    public boolean needRefresh(int pageNo) {
        return this.pageNo == pageNo;
    }

    /**
     * 设置对外的上拉刷新接口
     * 同时设置recyclerView的滑动判断
     * @param changePageListener
     */
    public void setPageListener(OnChangePageListener changePageListener) {
        this.changePageListener = changePageListener;
        this.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滑动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE && manager != null) {
                    //获取最后一个完全显示的itemPosition
                    int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
                    int itemCount = manager.getItemCount();
                    // 判断是否滑动到了最后一个item，并且是向上滑动
                    if (lastItemPosition == (itemCount - 1) && isSlidingUp) {
                        //加载更多
                        if (changePageListener != null) {
                            pageNo++;
                            changePageListener.onLoadData(pageNo);
                        }
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 大于0表示正在向上滑动，小于等于0表示停止或向下滑动
                isSlidingUp = dy > 0;
            }
        });
    }

}
