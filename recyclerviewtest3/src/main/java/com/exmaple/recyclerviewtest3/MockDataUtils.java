package com.exmaple.recyclerviewtest3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockDataUtils {
    private static String bannerFirst = "https://img.zcool.cn/community/016c355652c7516ac7251c943f32da.jpg";
    private static String bannerSecond = "https://img.zcool.cn/community/01881a5652c7516ac7251c94522683.jpg";
    private static String bannerThird = "https://img.zcool.cn/community/01c35e5652c76e32f87512f6883563.jpg";
    private static String bannerFourth = "https://img.zcool.cn/community/0187da5652c76e32f87512f692d6d1.jpg";

    public static List<String> getBannerData() {
        //轮播图链接
        List<String> banneUrl = new ArrayList<>();

        banneUrl.add(bannerFirst);
        banneUrl.add(bannerSecond);
        banneUrl.add(bannerThird);
        banneUrl.add(bannerFourth);

        return banneUrl;
    }

    public static List<Map<String, Object>> getChannelData() {
        List<Map<String, Object>> channelList = new ArrayList<Map<String, Object>>();

        Map<String, Object> map1 = new HashMap<>();
        map1.put("pic", R.mipmap.channel1);
        map1.put("title", "频道1");
        channelList.add(map1);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("pic", R.mipmap.channel2);
        map2.put("title", "频道2");
        channelList.add(map2);

        Map<String, Object> map3 = new HashMap<>();
        map3.put("pic", R.mipmap.channel3);
        map3.put("title", "频道3");
        channelList.add(map3);

        Map<String, Object> map4 = new HashMap<>();
        map4.put("pic", R.mipmap.channel4);
        map4.put("title", "频道4");
        channelList.add(map4);

        Map<String, Object> map5 = new HashMap<>();
        map5.put("pic", R.mipmap.channel5);
        map5.put("title", "频道5");
        channelList.add(map5);

        Map<String, Object> map6 = new HashMap<>();
        map6.put("pic", R.mipmap.channel6);
        map6.put("title", "频道6");
        channelList.add(map6);

        Map<String, Object> map7 = new HashMap<>();
        map7.put("pic", R.mipmap.channel7);
        map7.put("title", "频道7");
        channelList.add(map7);

        Map<String, Object> map8 = new HashMap<>();
        map8.put("pic", R.mipmap.channel8);
        map8.put("title", "频道8");
        channelList.add(map8);

        return channelList;
    }

    public static List<MyItem> getGirlData() {
        String msgs[] = {
                "迪巧钙维生素D颗粒1g/袋×15袋",
                "朗迪 碳酸钙D3颗粒30袋 儿童、孕妇、老年人补钙产品",
                "金丐 醋酸钙颗粒(无糖型) 15包 预防和治疗钙缺乏症骨质疏松 佝偻病 补钙 套餐一：3盒",
                "澳洲Bio island原装进口婴幼儿宝宝儿童/乳钙补钙/补充DHA/儿童补锌片 4周+鱼油DHA90粒",
                "澳洲直采Bio Island 佰澳朗德 比奥岛 婴幼儿补钙牛乳提取液体乳钙 90粒/瓶 1个月以上",
                "爱乐维 复合维生素片30片 补充叶酸拜耳进口分装 孕妇怀孕备孕及哺乳期妇女补充维生素矿物质",
        };

        String flags[] = {
                "自营",
                "京东超市",
                "自营",
                "自营",
                "京东超市",
                "自营",
        };

        int pics[] = {
                R.mipmap.goods1,
                R.mipmap.goods2,
                R.mipmap.goods3,
                R.mipmap.goods4,
                R.mipmap.goods5,
                R.mipmap.goods6,
        };

        String prices[] = {
                "38.90",
                "199.00",
                "28.80",
                "122.00",
                "98.90",
                "999.00",
        };

        String oldprices[] = {
                "48.90",
                "210.00",
                "33.80",
                "126.00",
                "99.90",
                "1100.90",
        };

        //轮播图链接
        List<MyItem> itemList = new ArrayList<MyItem>();
        for (int i=0; i<6; i++) {
            MyItem item = new MyItem();
            item.setGoods_pic(pics[i]);
            item.setGoods_flag(flags[i]);
            item.setGoods_msg(msgs[i]);
            item.setGoods_price(prices[i]);
            item.setGoods_old_price(oldprices[i]);
            itemList.add(item);
        }


        return itemList;
    }
}
