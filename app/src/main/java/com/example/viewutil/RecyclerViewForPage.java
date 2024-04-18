package com.example.viewutil;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_interface.OnChangePageListener;

import java.util.List;

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
    private int itemNumber = 15;
    private int clickPage = 1;
    //用来标记是否正在向上滑动
    private boolean isSlidingUp = false;

    /**
     * 在适配器中发生点击事件时，点击事件处理的最后步骤调用此方法并传入下标和list数据
     * 通过下标计算出当前页，然后删除当前页及之后的所有数据，并且pageNo设置为当前页
     * 在返回刷新时获取当前页的数据
     * @param position
     * @param list
     * @return
     */
    public List<?> setClickPage(int position, List<?> list) {
        clickPage = position/itemNumber + 1;
        pageNo = clickPage;
        for (int index = list.size() - 1; index >= getRemoveIndex(); index--) {
            if (list.size() > index) {
                list.remove(index);
            }
        }
        return list;
    }

    /**
     * 计算应该删除的第一个数据的下标
     * @return
     */
    public int getRemoveIndex() {
        return (clickPage - 1) * itemNumber;
    }

    public int getClickPage() {
        return clickPage;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public int getItemNumber() {
        return itemNumber;
    }

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
        changePageListener.onLoadData(pageNo);
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
        if (pageNo == 0 && this.changePageListener != null) {
            this.changePageListener.onLoadData(pageNo);
        }
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
                    // 判断是否滑动到了最后一个item，并且item数量大于单页数量时，获取下一页内容
                    if (lastItemPosition == (itemCount - 1) && itemCount >= itemNumber) {
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
