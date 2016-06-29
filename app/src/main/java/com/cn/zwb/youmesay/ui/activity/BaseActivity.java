package com.cn.zwb.youmesay.ui.activity;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.cn.zwb.youmesay.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhangweibo on 2016/3/22.
 */
public class BaseActivity extends AppCompatActivity {

    public final static String DATA = "data";



    private long exitTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }



    /**
     * 传递序列化的列表 默认key是DATA
     *
     * @param clz
     * @param object
     */
    public void startIntent(Class clz, List<? extends Parcelable> object) {

        Intent intent = new Intent(this, clz);
        intent.putParcelableArrayListExtra(DATA, (ArrayList<? extends Parcelable>) object);
        startActivity(intent);
    }

    /**
     * 设置头部布局
     *
     * @param isBack    是否触发回退按键
     * @param titleID 标题名
     */
    public void setHeader(boolean isBack, int titleID) {

        if (isBack) {
            View ivBack = findViewById(R.id.iv_back);
            ivBack.setVisibility(View.VISIBLE);
            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        setHeader(titleID);
    }

    public void setHeader(int titleID) {

        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(getResources().getString(titleID));

    }

    /**
     * 传递序列化的对象 默认key是DATA
     *
     * @param clz
     * @param object
     */

    public void startIntent(Class clz, Parcelable object) {
        Intent intent = new Intent(this, clz);
        intent.putExtra(DATA, object);
        startActivity(intent);
    }

    public void startIntent(Class clz) {
        Intent intent = new Intent(this, clz);
        startActivity(intent);
    }

    /**
     * 设置右边标题和监听
     * @param title
     * @param onClickListener
     */
    public void setHeaderRightMenuText(int title, View.OnClickListener onClickListener){

        TextView tvMenu = (TextView) findViewById(R.id.tv_menu);
        tvMenu.setVisibility(View.VISIBLE);
        tvMenu.setText(getResources().getString(title));
        tvMenu.setOnClickListener(onClickListener);

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }








}
