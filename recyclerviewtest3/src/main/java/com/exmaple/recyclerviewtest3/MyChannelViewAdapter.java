package com.exmaple.recyclerviewtest3;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Map;

public class MyChannelViewAdapter extends RecyclerView.Adapter<MyChannelViewAdapter.ViewHolder> {
    private List<Map<String, Object>> mDataList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView channel_icon;
        public TextView channel_text;


        public ViewHolder(View v) {
            super(v);
            channel_icon = (ImageView) v.findViewById(R.id.channel_icon);
            channel_text = (TextView) v.findViewById(R.id.channel_text);
        }
    }

    public MyChannelViewAdapter(Context context, List<Map<String, Object>> mDataList) {
        this.mContext = context;
        this.mDataList = mDataList;
    }

    @Override
    public MyChannelViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_item_view, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Glide.with(mContext).load(mDataList.get(position).get("pic")).into(holder.channel_icon);
        holder.channel_text.setText(mDataList.get(position).get("title").toString());

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