package com.cn.zwb.youmesay.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.cn.zwb.youmesay.R;
import com.cn.zwb.youmesay.support.http.bean.HttpRequestResult;
import com.cn.zwb.youmesay.support.widget.pullrefresh.PullToRefreshRecyclerView;
import com.cn.zwb.youmesay.ui.adapter.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangweibo on 2016/6/22.
 */
public abstract class PullRefreshFragment<T> extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, PullToRefreshRecyclerView.PagingableListener {
    PullToRefreshRecyclerView pttrRefresh;
    private List<T> dataList;
    protected BaseRecyclerAdapter<T> adapter;
    protected  int page;

    @Override
    public void initData() {

        fetch();
    }

    @Override
    public void initView(View view) {
        pttrRefresh= (PullToRefreshRecyclerView) view.findViewById(R.id.pttr_refresh);
        initPullRefreshView();
    }

    @Override
    protected int getResourceLyoutID() {
        return R.layout.fragment_pullrefresh;
    }

    /**
     * 刷新控件初始化
     */
    public void initPullRefreshView(){
        dataList=new ArrayList<>();
        adapter=getAdapter();
        adapter.updateList(dataList);
        pttrRefresh.setSwipeEnable(true);
        pttrRefresh.setLayoutManager(new LinearLayoutManager(getActivity()));
        pttrRefresh.setOnRefreshListener(this);
        if (isLoadMore()){
            pttrRefresh.setPagingableListener(this);
            pttrRefresh.onFinishLoading(true,false);
        }
        pttrRefresh.setAdapter(adapter);
    }


    abstract  boolean isLoadMore();
    /**
     * 设置当前的adapter
     * @return
     */
    abstract  BaseRecyclerAdapter<T> getAdapter();

    /**
     * 获取数据
     */
    abstract void fetch();

    @Override
    public void onRefresh() {
        page=1;
        fetch();
    }

    @Override
    public void onLoadMoreItems() {
        fetch();
    }


    public void notifyDataChanged(HttpRequestResult<List<T>> result){

        if (page==1){
            dataList.clear();
        }
        page++;
        dataList.addAll(result.getResult());
        adapter.notifyDataSetChanged();
        refreshComplete();
        //判断是否还要加载更多
        if (isLoadMore()){
            if (result.getPage()<result.getTotalPage()){
                pttrRefresh.onFinishLoading(true,false);
            }else{
                pttrRefresh.onFinishLoading(false,false);
            }
        }
    }

    /**
     * 用于测试数据
     * @param result
     */
    public void notifyDataChangedTest(List<T> result){

        if (page==1){
            dataList.clear();
        }
        dataList.addAll(result);
        adapter.notifyDataSetChanged();
        refreshComplete();
        //判断是否还要加载更多
    }


    public void refreshComplete(){

        pttrRefresh.setOnLoadMoreComplete();
        pttrRefresh.setOnRefreshComplete();

    }



}
