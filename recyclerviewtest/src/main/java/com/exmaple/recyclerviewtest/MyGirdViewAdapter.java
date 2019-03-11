package com.exmaple.recyclerviewtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyGirdViewAdapter extends RecyclerView.Adapter<MyGirdViewAdapter.ViewHolder> {
    private List<MyItem> mDataList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView title;
        public TextView datetime;
        public ImageView bookmark;

        public ViewHolder(View v) {
            super(v);
            icon = (ImageView) v.findViewById(R.id.item_icon);
            title = (TextView) v.findViewById(R.id.item_title);
            datetime = (TextView) v.findViewById(R.id.item_datetime);
            bookmark = (ImageView) v.findViewById(R.id.item_bookmark);
        }
    }

    public MyGirdViewAdapter(Context context, List<MyItem> mDataList) {
        this.mContext = context;
        this.mDataList = mDataList;
    }

    @Override
    public MyGirdViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gird_item_view, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        ImageView icon = holder.icon;
        TextView title = holder.title;
        TextView datetime = holder.datetime;
        ImageView bookmark = holder.bookmark;
        icon.setImageBitmap(mDataList.get(position).getIcon());
//        title.setText("今天天气好晴朗");
        datetime.setText("2019-09-11 10:12:36");
//        tv.setText(mDataList.get(position));

        //在mTextView上设置listener的话，之后点击mTextView才能起作用，点击holder上的其他view不起作用
        //在holder.itemView上设置listener的话，之后点击整个item都起作用
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "Click on item " + position, Toast.LENGTH_SHORT).show();
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, position);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                Toast.makeText(mContext, "Long Click on item " + position, Toast.LENGTH_SHORT).show();
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemLongClick(v, position);
                }
                // 如果返回true，则会屏蔽点击事件(短按)，否则在长按事件触发后还会触发点击事件
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}