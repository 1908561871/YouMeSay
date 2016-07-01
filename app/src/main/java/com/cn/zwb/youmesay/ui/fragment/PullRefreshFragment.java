package com.cn.zwb.youmesay.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.PtrHandler;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.cn.zwb.youmesay.R;
import com.cn.zwb.youmesay.support.http.bean.HttpRequestResult;
import com.cn.zwb.youmesay.ui.adapter.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangweibo on 2016/6/22.
 */
public abstract class PullRefreshFragment<T> extends BaseFragment implements OnLoadMoreListener, PtrHandler {
    PtrClassicFrameLayout ptrClassicFrameLayout;
    RecyclerView mRecyclerView;
    private List<T> dataList;
    protected BaseRecyclerAdapter<T> adapter;
    private RecyclerAdapterWithHF mAdapter;
    protected  int page;

    @Override
    public void initData() {

        fetch();
    }

    @Override
    public void initView(View view) {
        ptrClassicFrameLayout = (PtrClassicFrameLayout) view.findViewById(R.id.recycler_view_frame);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
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
        mAdapter = new RecyclerAdapterWithHF((RecyclerView.Adapter) adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        ptrClassicFrameLayout.setOnLoadMoreListener(this);
        ptrClassicFrameLayout.setPtrHandler(this);
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



    public void notifyDataChanged(HttpRequestResult<List<T>> result){

        if (page==1){
            dataList.clear();
        }
        page++;
        dataList.addAll(result.getResult());
        adapter.notifyDataSetChanged();
        refreshComplete();
        //判断是否还要加载更多
      /*  if (isLoadMore()){
            if (result.getPage()<result.getTotalPage()){
                pttrRefresh.onFinishLoading(true,false);
            }else{
                pttrRefresh.onFinishLoading(false,false);
            }
        }*/
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


    }


    @Override
    public void loadMore() {

    }



    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {

    }
}
