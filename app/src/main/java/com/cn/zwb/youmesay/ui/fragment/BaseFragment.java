package com.cn.zwb.youmesay.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangweibo on 2016/3/22.
 */
public abstract class BaseFragment extends Fragment {

    private View rootView;

    public final static  String DATA="data";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView!=null)
        {
           ViewGroup parent= (ViewGroup) rootView.getParent();
            if (parent!=null)
            {
                parent.removeView(rootView);
            }

        }else{
            if (getResourceLyoutID()!=0)
            {
                rootView=LayoutInflater.from(getActivity()).inflate(getResourceLyoutID(),null);
            }
            //初始化视图
            initView(rootView);
            //加载数据
            initData();
        }
        return rootView;
    }



    public void startIntent(Class clz,List<? extends Parcelable> object){

        Intent intent=new Intent(getActivity(),clz);
        intent.putParcelableArrayListExtra(DATA, (ArrayList<? extends Parcelable>) object);
        startActivity(intent);
    }

    public void startIntent(Class clz,Parcelable object){
        Intent intent=new Intent(getActivity(), clz);
        intent.putExtra(DATA, object);
        startActivity(intent);
    }

    public void startIntent(Class clz){
        Intent intent=new Intent(getActivity(), clz);

        startActivity(intent);
    }



    /**
     * 初始化数据
     */
    public abstract  void initData();


    /**
     * 视图初始化
     * @param view
     */
    public abstract  void initView(View view);



    /**
     * 布局ID
     * @return
     */
    protected  abstract  int getResourceLyoutID();




}
