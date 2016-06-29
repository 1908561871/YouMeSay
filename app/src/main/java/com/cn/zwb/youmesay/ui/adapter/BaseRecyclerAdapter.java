package com.cn.zwb.youmesay.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerAdapter<T>.ViewHolder> {
    private List<T> items = Collections.emptyList();
    private SparseBooleanArray selectedItems;
    protected Context context;
    protected final  String DATA="data";
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view = LayoutInflater.from(context).inflate(getAdapterContentViewID(), parent, false);
        return new ViewHolder(view);
    }

    /**
     * 获取当前的视图
     * @return
     */
    abstract  int getAdapterContentViewID();

    /**
     * 设置数据
     * @param t
     */
    abstract  void setData(T t,ViewHolder holder);

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.populate(items.get(position));
    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateList(List<T> thingList) {
        this.items = thingList;
        notifyDataSetChanged();
    }

    public void toggleSelection(int pos) {
        if (selectedItems.get(pos, false)) {
            selectedItems.delete(pos);
        }
        else {
            selectedItems.put(pos, true);
        }
        notifyItemChanged(pos);
    }

    public void clearSelections() {
        selectedItems.clear();
        notifyDataSetChanged();
    }

    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<Integer>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }

    abstract  void handleClick(T item);


    public class ViewHolder extends RecyclerView.ViewHolder{


        private SparseArray<View> mViews;
        public ViewHolder(View itemView) {
            super(itemView);
            mViews=new SparseArray<>();
        }

        public void populate(final T item) {
            setData(item,this);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleClick(item);
                }
            });
        }






        /**
         * 通过viewId获取控件
         *
         * @param viewId
         * @return
         */
        public <T extends View> T getView(int viewId)
        {
            View view = mViews.get(viewId);
            if (view == null)
            {
                view = itemView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }



        //设置图片网络地址
        public ViewHolder setImageUrl(int viewId,String url)
        {
            SimpleDraweeView view=getView(viewId);
            view.setImageURI(Uri.parse(url));
            return this;
        }


        public ViewHolder setImageResource(int viewId, int resId)
        {
            ImageView view = getView(viewId);
            view.setImageResource(resId);
            return this;
        }

        public ViewHolder setImageBitmap(int viewId, Bitmap bitmap)
        {
            ImageView view = getView(viewId);
            view.setImageBitmap(bitmap);
            return this;
        }

        public ViewHolder setImageDrawable(int viewId, Drawable drawable)
        {
            ImageView view = getView(viewId);
            view.setImageDrawable(drawable);
            return this;
        }

        public ViewHolder setBackgroundColor(int viewId, int color)
        {
            View view = getView(viewId);
            view.setBackgroundColor(color);
            return this;
        }

        public ViewHolder setBackgroundRes(int viewId, int backgroundRes)
        {
            View view = getView(viewId);
            view.setBackgroundResource(backgroundRes);
            return this;
        }

        public ViewHolder setTextColor(int viewId, int textColor)
        {
            TextView view = getView(viewId);
            view.setTextColor(context.getResources().getColor(textColor));
            return this;
        }


        @SuppressLint("NewApi")
        public ViewHolder setAlpha(int viewId, float value)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            {
                getView(viewId).setAlpha(value);
            } else
            {
                // Pre-honeycomb hack to set Alpha value
                AlphaAnimation alpha = new AlphaAnimation(value, value);
                alpha.setDuration(0);
                alpha.setFillAfter(true);
                getView(viewId).startAnimation(alpha);
            }
            return this;
        }

        public ViewHolder setVisible(int viewId, boolean visible)
        {
            View view = getView(viewId);
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
            return this;
        }


        public ViewHolder setHide(int viewId, boolean visible)
        {
            View view = getView(viewId);
            view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
            return this;
        }

        public ViewHolder linkify(int viewId)
        {
            TextView view = getView(viewId);
            Linkify.addLinks(view, Linkify.ALL);
            return this;
        }

        public ViewHolder setTypeface(Typeface typeface, int... viewIds)
        {
            for (int viewId : viewIds)
            {
                TextView view = getView(viewId);
                view.setTypeface(typeface);
                view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
            }
            return this;
        }

        public ViewHolder setProgress(int viewId, int progress)
        {
            ProgressBar view = getView(viewId);
            view.setProgress(progress);
            return this;
        }

        public ViewHolder setProgress(int viewId, int progress, int max)
        {
            ProgressBar view = getView(viewId);
            view.setMax(max);
            view.setProgress(progress);
            return this;
        }

        public ViewHolder setMax(int viewId, int max)
        {
            ProgressBar view = getView(viewId);
            view.setMax(max);
            return this;
        }

        public ViewHolder setRating(int viewId, float rating)
        {
            RatingBar view = getView(viewId);
            view.setRating(rating);
            return this;
        }

        public ViewHolder setRating(int viewId, float rating, int max)
        {
            RatingBar view = getView(viewId);
            view.setMax(max);
            view.setRating(rating);
            return this;
        }

        public ViewHolder setTag(int viewId, Object tag)
        {
            View view = getView(viewId);
            view.setTag(tag);
            return this;
        }

        public ViewHolder setTag(int viewId, int key, Object tag)
        {
            View view = getView(viewId);
            view.setTag(key, tag);
            return this;
        }

        public ViewHolder setChecked(int viewId, boolean checked)
        {
            Checkable view = (Checkable) getView(viewId);
            view.setChecked(checked);
            return this;
        }

        /**
         * 设置TextView的值
         *
         * @param viewId
         * @param text
         * @return
         */
        public ViewHolder setText(int viewId, String text)
        {
            TextView tv = getView(viewId);
            tv.setText(text);
            return this;
        }


    }


    public List<T> getItems() {
        return items;
    }
}
