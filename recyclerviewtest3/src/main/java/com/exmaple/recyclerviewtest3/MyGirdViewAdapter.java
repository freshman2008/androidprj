package com.exmaple.recyclerviewtest3;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyGirdViewAdapter extends RecyclerView.Adapter<MyGirdViewAdapter.ViewHolder> {
    private List<MyItem> mDataList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView goods_pic;
        public TextView goods_flag;
        public TextView goods_msg;
        public TextView goods_price;
        public TextView goods_old_price;
        public ImageView search_category_goods;
        public ImageView delete_goods_item;
        //goods_pic
        //goods_flag
        //goods_msg
        //goods_price
        //search_category_goods
        //delete_goods_item
        //goods_old_price

        public ViewHolder(View v) {
            super(v);
            goods_pic = (ImageView) v.findViewById(R.id.goods_pic);
            goods_flag = (TextView) v.findViewById(R.id.goods_flag);
            goods_msg = (TextView) v.findViewById(R.id.goods_msg);
            goods_price = (TextView) v.findViewById(R.id.goods_price);
            goods_old_price = (TextView) v.findViewById(R.id.goods_old_price);
            search_category_goods = (ImageView) v.findViewById(R.id.search_category_goods);
            delete_goods_item = (ImageView) v.findViewById(R.id.delete_goods_item);
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
        Glide.with(mContext).load(mDataList.get(position).getGoods_pic()).into(holder.goods_pic);
        holder.goods_flag.setText(mDataList.get(position).getGoods_flag());

        String goodMsg = mDataList.get(position).getGoods_msg();
//        SpannableString msp = new SpannableString("1" + goodMsg);
//        Drawable drawable = mContext.getResources().getDrawable(R.mipmap.flag1);
//        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//        msp.setSpan(new ImageSpan(drawable), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.goods_msg.setText(/*msp*/goodMsg);

        holder.goods_old_price.setText("￥" + mDataList.get(position).getGoods_old_price());
        //添加删除线
        holder.goods_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.goods_price.setText("￥" + mDataList.get(position).getGoods_price());

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