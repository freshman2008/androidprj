package com.exmaple.recyclerviewtest3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.List;
import java.util.Map;

public class MyHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private Context context;
    private List<String> picList;
    private List<Map<String, Object>> channelList;
    private List<Map<String, Object>> channelList2;
//    private List<Integer> girlList;
    private List<MyItem> girlList;
    private List<String> normalList;
    private List<FliperItem> fliperItems;
    private OnItemClickListener onItemClickListener;

    private final int BANNER_VIEW_TYPE = 0;//轮播图
    private final int CHANNEL_VIEW_TYPE = 1;//频道
    private final int SECOND_CHANNEL_VIEW_TYPE = 2;//频道
    private final int GIRL_VIEW_TYPE = 3;//美女
    private final int NORMAL_VIEW_TYPE = 4;//正常布局

    public MyHomeAdapter(Context context,
                         List<String> picList,
                         List<Map<String, Object>> channelList,
                         List<Map<String, Object>> channelList2,
                         List<MyItem> girlList,
                         List<FliperItem> fliperItems,
                         List<String> normalList) {
        this.context = context;
        this.picList = picList;
        this.channelList = channelList;
        this.channelList2 = channelList2;
        this.girlList = girlList;
        this.normalList = normalList;
        this.fliperItems = fliperItems;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 轮播图的ViewHolder
     */
    public class BannerHolder extends RecyclerView.ViewHolder {
        private Banner banner;

        public BannerHolder(View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner);
        }
    }

    /**
     * 频道列表的ViewHolder
     */
    public class ChannelHolder extends RecyclerView.ViewHolder {
        public ImageView channelItemImage1;
        public ImageView channelItemImage2;
        public ImageView channelItemImage3;
        public ImageView channelItemImage4;
        public ImageView channelItemImage5;
        public ImageView channelItemImage6;
        public ImageView channelItemImage7;
        public ImageView channelItemImage8;
        public TextView channelItemText1;
        public TextView channelItemText2;
        public TextView channelItemText3;
        public TextView channelItemText4;
        public TextView channelItemText5;
        public TextView channelItemText6;
        public TextView channelItemText7;
        public TextView channelItemText8;

        public ChannelHolder(View itemView) {
            super(itemView);
            channelItemImage1 = itemView.findViewById(R.id.channel_image1);
            channelItemImage2 = itemView.findViewById(R.id.channel_image2);
            channelItemImage3 = itemView.findViewById(R.id.channel_image3);
            channelItemImage4 = itemView.findViewById(R.id.channel_image4);
            channelItemImage5 = itemView.findViewById(R.id.channel_image5);
            channelItemImage6 = itemView.findViewById(R.id.channel_image6);
            channelItemImage7 = itemView.findViewById(R.id.channel_image7);
            channelItemImage8 = itemView.findViewById(R.id.channel_image8);

            channelItemText1 = itemView.findViewById(R.id.channel_text1);
            channelItemText2 = itemView.findViewById(R.id.channel_text2);
            channelItemText3 = itemView.findViewById(R.id.channel_text3);
            channelItemText4 = itemView.findViewById(R.id.channel_text4);
            channelItemText5 = itemView.findViewById(R.id.channel_text5);
            channelItemText6 = itemView.findViewById(R.id.channel_text6);
            channelItemText7 = itemView.findViewById(R.id.channel_text7);
            channelItemText8 = itemView.findViewById(R.id.channel_text8);
        }
    }

    public class SecondChannelHolder extends RecyclerView.ViewHolder {
        private RecyclerView channelRvView2;
        private ViewFlipper viewFlipper;

        public SecondChannelHolder(@NonNull View itemView) {
            super(itemView);
            channelRvView2 = itemView.findViewById(R.id.channel_rv_view2);
            viewFlipper = itemView.findViewById(R.id.viewflipper);
        }
    }

    /**
     * 美女的ViewHolder
     */
    public class GirlHolder extends RecyclerView.ViewHolder {
        private RecyclerView girlView;

        public GirlHolder(View itemView) {
            super(itemView);
            girlView = itemView.findViewById(R.id.girl_rv_view);
        }
    }

    /**
     * 正常布局的ViewHolder
     */
    public class NormalHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public NormalHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.normal_text);
        }
    }

    /**
     * 关联布局
     */
    private View inflate(ViewGroup parent, int resId) {
        return LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case BANNER_VIEW_TYPE:
                viewHolder = new BannerHolder(inflate(parent, R.layout.item_banner));
                break;
            case CHANNEL_VIEW_TYPE:
                viewHolder = new ChannelHolder(inflate(parent, R.layout.item_channel));
                break;
            case SECOND_CHANNEL_VIEW_TYPE:
                viewHolder = new SecondChannelHolder(inflate(parent, R.layout.item_second_channel));
                break;
            case GIRL_VIEW_TYPE:
                viewHolder = new GirlHolder(inflate(parent, R.layout.item_girl));
                break;
            case NORMAL_VIEW_TYPE:
                viewHolder = new NormalHolder(inflate(parent, R.layout.item_normal));
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        //判断不同的ViewHolder做不同的处理
        if (viewHolder instanceof BannerHolder) {//轮播图
            BannerHolder bannerHolder = (BannerHolder) viewHolder;
            //调用设置轮播图相关方法
            setBanner(bannerHolder);
        } else if (viewHolder instanceof ChannelHolder) {//频道
            ChannelHolder channelHolder = (ChannelHolder) viewHolder;
            //设置频道
            setChannel(channelHolder);
        } else if (viewHolder instanceof SecondChannelHolder) {
            SecondChannelHolder secondChannelHolder = (SecondChannelHolder) viewHolder;

            RecyclerView recyclerView = secondChannelHolder.channelRvView2;
            recyclerView.setHasFixedSize(true);
            GridLayoutManager mLayoutManager = new GridLayoutManager(context, 5);
            recyclerView.setLayoutManager(mLayoutManager);
            RecyclerViewCornerRadius radiusItemDecoration = new RecyclerViewCornerRadius(recyclerView);
            radiusItemDecoration.setCornerRadius(AppUtils.dpTopx(context, 5));
            radiusItemDecoration.setItemOffsets(8, 5);
            recyclerView.addItemDecoration(radiusItemDecoration);
            MyChannelViewAdapter adapter = new MyChannelViewAdapter(context, channelList2);
            recyclerView.setAdapter(adapter);

            ViewFlipper flipper = secondChannelHolder.viewFlipper;

            for (int i = 0; i < fliperItems.size(); i++) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_view_flipper, flipper, false);
                TextView type = view.findViewById(R.id.item_type);
                TextView content = view.findViewById(R.id.item_content);

                type.setText(fliperItems.get(i).getType());
                content.setText(fliperItems.get(i).getContent());
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                flipper.addView(view);
            }
            flipper.startFlipping();
        } else if (viewHolder instanceof GirlHolder) {//美女
//            GirlHolder girlHolder = (GirlHolder) viewHolder;
//            GridViewAdapter adapter = new GridViewAdapter(context, girlList);
//            girlHolder.girlView.setAdapter(adapter);
//            girlHolder.itemView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Toast.makeText(context, "美女" + position, Toast.LENGTH_SHORT).show();
//                }
//            });
            GirlHolder girlHolder = (GirlHolder) viewHolder;
            girlHolder.girlView.setHasFixedSize(true);
            GridLayoutManager mGirdLayoutManager = new GridLayoutManager(context, 2);
            girlHolder.girlView.setLayoutManager(mGirdLayoutManager);
//            girlHolder.girlView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
            girlHolder.girlView.addItemDecoration(new RecyclerItemDecoration(15,2));

            MyGirdViewAdapter adapter = new MyGirdViewAdapter(context, girlList);
            girlHolder.girlView.setAdapter(adapter);

        } else if (viewHolder instanceof NormalHolder) {//正常布局
            NormalHolder normalHolder = (NormalHolder) viewHolder;
            normalHolder.textView.setText(/*normalList.get(position - 3)*/"hello");
            normalHolder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "点击了" + normalList.get(position - 3), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 设置轮播图
     *
     * @param bannerHolder
     */
    private void setBanner(BannerHolder bannerHolder) {
        //设置banner样式
        bannerHolder.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        bannerHolder.banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        bannerHolder.banner.setImages(picList);
        //设置banner动画效果
        bannerHolder.banner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
//            bannerHolder.banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        bannerHolder.banner.isAutoPlay(true);
        //设置轮播时间
//            bannerHolder.banner.setDelayTime(3500);
        //设置指示器位置（当banner模式中有指示器时）
        bannerHolder.banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        bannerHolder.banner.start();
    }

    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);
        }
    }

    /**
     * 设置频道
     *
     * @param channelHolder
     */
    private void setChannel(ChannelHolder channelHolder) {
        Glide.with(context).load(channelList.get(0).get("pic")).into(channelHolder.channelItemImage1);
        channelHolder.channelItemText1.setText(channelList.get(0).get("title").toString());

        Glide.with(context).load(channelList.get(1).get("pic")).into(channelHolder.channelItemImage2);
        channelHolder.channelItemText2.setText(channelList.get(1).get("title").toString());

        Glide.with(context).load(channelList.get(2).get("pic")).into(channelHolder.channelItemImage3);
        channelHolder.channelItemText3.setText(channelList.get(2).get("title").toString());

        Glide.with(context).load(channelList.get(3).get("pic")).into(channelHolder.channelItemImage4);
        channelHolder.channelItemText4.setText(channelList.get(3).get("title").toString());

        Glide.with(context).load(channelList.get(4).get("pic")).into(channelHolder.channelItemImage5);
        channelHolder.channelItemText5.setText(channelList.get(4).get("title").toString());

        Glide.with(context).load(channelList.get(5).get("pic")).into(channelHolder.channelItemImage6);
        channelHolder.channelItemText6.setText(channelList.get(5).get("title").toString());

        Glide.with(context).load(channelList.get(6).get("pic")).into(channelHolder.channelItemImage7);
        channelHolder.channelItemText7.setText(channelList.get(6).get("title").toString());

        Glide.with(context).load(channelList.get(7).get("pic")).into(channelHolder.channelItemImage8);
        channelHolder.channelItemText8.setText(channelList.get(7).get("title").toString());
    }

    /**
     * 获取item的类型
     *
     * @param position item的位置
     * @return item的类型
     * 有几种type就回在onCreateViewHolder方法中引入几种布局,也就是创建几个ViewHolder
     */
    @Override
    public int getItemViewType(int position) {
        /*
        区分item类型,返回不同的int类型的值
        在onCreateViewHolder方法中用viewType来创建不同的ViewHolder
         */
        if (position == 0) {//第0个位置是轮播图
            return BANNER_VIEW_TYPE;
        } else if (position == 1) {//第一个是频道布局
            return CHANNEL_VIEW_TYPE;
        } else if (position == 2) {
            return SECOND_CHANNEL_VIEW_TYPE;
        } else if (position == 3) {//第2个位置是美女布局
            return GIRL_VIEW_TYPE;
        } else {//其他位置返回正常的布局
            return NORMAL_VIEW_TYPE;
        }
    }

    @Override
    public int getItemCount() {
//        int count = 0;
//        if (normalList != null) {
//            count += 1;//normalList.size();
//        }
//        if (picList != null) {
//            count += 1;
//        }
//        if (channelList != null) {
//            count += 1;
//        }
//        if (girlList != null) {
//            count += 1;
//        }
//        return count;
        return 5;
    }
}
