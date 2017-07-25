package com.hejunlin.liveplayback.contacts;

import android.os.Environment;

import com.hejunlin.liveplayback.R;

/**
 * Created by HM on 2017/3/14 21:46
 */

public class Contacts {
    public static String DEFAULT_IP = "192.168.0.195";
    public static String DEFAULT_PATH = "smb://" + DEFAULT_IP + "/";
    public static String DEFAULT_UAERNAME = "kupaworld";
    public static String DEFAULT_PASSWORD = "kupa1806";
    public static final String BASE_PATH = Environment.getExternalStorageDirectory().toString() + "/KupaTv";
    public static String DEFAULT_SMB_PATH = "smb://" + Contacts.DEFAULT_UAERNAME + ":" + Contacts.DEFAULT_PASSWORD + "@"
            + Contacts.DEFAULT_IP + "/video/";
    public static String SHENZHEN_CITYID = "101280601";
    public static final String CONTENT_TYPE = "text/html; charset=\"utf-8\"";
    public static final String SEARCH_ALLMOVIE_BYTYPE = "http://www.kupaworld.cn:1226/HotelTV/loadMovieServlet";
    /* 预设的组合快捷键数组:菜单键+上 */
    public static final String[] keyStrings = {"KEYCODE_MENU", "KEYCODE_DPAD_UP"};
    /* 系统设置的包名和类名 */
    public static final String packageName = "com.android.settings";
    public static final String className = "com.android.settings.Settings";

    public static final String[] mDataList = new String[]{
            "CCTV1高清", "香港卫视", "CHC家庭影院", "深圳卫视", "湖南卫视", "CCTV3高清", "CCTV5高清",
            "CCTV6高清", "CCTV15",
    };
    public static final String[] mUrlList = new String[]{
            "http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8",
            "http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8",
            "http://182.245.29.105:92/376/376.m3u8",
            "http://182.245.29.105:92/190/190.m3u8",
            "http://182.245.29.105:92/125/125.m3u8",
            "http://ivi.bupt.edu.cn/hls/cctv3hd.m3u8",
            "http://ivi.bupt.edu.cn/hls/cctv5hd.m3u8",
            "http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8",
            "http://h5live.gslb.cmvideo.cn/envivo_x/SD/cctv15/450/index.m3u8?msisdn=0455655485170&mdspid=&spid=699004&netType=1&sid=2202203578&pid=2028597139×tamp=20170425164812&SecurityKey=20170425164812&Channel_ID=1004_10010001005&ProgramID=608807408&ParentNodeID=-99&assertID=2202203578&encrypt=b4432309e8c79bf48d75a2113cdf2a97",
    };
    public static int[] POPULAR_MOVIES_IMG = {
            R.mipmap.popular_testmovie_cikexintiao, R.mipmap.popular_testmovie_gongfuyujia, R.mipmap.popular_testmovie_heyuenannv,
            R.mipmap.popular_testmovie_jingangkuloudao, R.mipmap.popular_testmovie_jinganglang, R.mipmap.popular_testmovie_jixiantegong,
            R.mipmap.popular_testmovie_legaobianfuxia, R.mipmap.popular_testmovie_meinvyuyeshou, R.mipmap.popular_testmovie_shenghuaweiji,
            R.mipmap.popular_testmovie_testmovie_ailezhicheng, R.mipmap.popular_yitiaogoudeshiming, R.mipmap.popular_zuizonghuanxiang,
            R.mipmap.popular_tiancaibushou, R.mipmap.popular_xingqiudazhan, R.mipmap.popular_yeseliaoren,
            R.mipmap.popular_testmovie_cikexintiao, R.mipmap.popular_testmovie_gongfuyujia, R.mipmap.popular_testmovie_heyuenannv,
            R.mipmap.popular_testmovie_jingangkuloudao, R.mipmap.popular_testmovie_jinganglang, R.mipmap.popular_testmovie_jixiantegong,
            R.mipmap.popular_testmovie_legaobianfuxia, R.mipmap.popular_testmovie_meinvyuyeshou, R.mipmap.popular_testmovie_shenghuaweiji,
            R.mipmap.popular_testmovie_testmovie_ailezhicheng, R.mipmap.popular_yitiaogoudeshiming, R.mipmap.popular_zuizonghuanxiang,
            R.mipmap.popular_tiancaibushou, R.mipmap.popular_xingqiudazhan, R.mipmap.popular_yeseliaoren,
            R.mipmap.popular_testmovie_cikexintiao, R.mipmap.popular_testmovie_gongfuyujia, R.mipmap.popular_testmovie_heyuenannv,
            R.mipmap.popular_testmovie_jingangkuloudao, R.mipmap.popular_testmovie_jinganglang, R.mipmap.popular_testmovie_jixiantegong,
            R.mipmap.popular_testmovie_legaobianfuxia, R.mipmap.popular_testmovie_meinvyuyeshou, R.mipmap.popular_testmovie_shenghuaweiji,
            R.mipmap.popular_testmovie_testmovie_ailezhicheng, R.mipmap.popular_yitiaogoudeshiming, R.mipmap.popular_zuizonghuanxiang,
            R.mipmap.popular_tiancaibushou, R.mipmap.popular_xingqiudazhan, R.mipmap.popular_yeseliaoren
    };
    public static String[] POPULAR_MOVIES_NAME = {
            "刺客信条", "功夫瑜伽", "合约男女", "金刚骷髅岛", "金刚狼3", "极限特工", "乐高蝙蝠侠", "美女与野兽", "生化危机",
            "爱乐之城", "一条狗的使命", "最终幻想", "天才捕手", "星球大战", "夜色撩人", "刺客信条", "功夫瑜伽", "合约男女",
            "金刚骷髅岛", "金刚狼3", "极限特工", "乐高蝙蝠侠", "美女与野兽", "生化危机", "爱乐之城", "一条狗的使命", "最终幻想",
            "天才捕手", "星球大战", "夜色撩人", "刺客信条", "功夫瑜伽", "合约男女", "金刚骷髅岛", "金刚狼3", "极限特工", "乐高蝙蝠侠", "美女与野兽", "生化危机", "爱乐之城", "一条狗的使命", "最终幻想",
            "天才捕手", "星球大战", "夜色撩人"
    };

    public static int[] SCIENCE_MOVIES_IMG = {
            R.mipmap.science__chaonengluzhandui, R.mipmap.science_bianxingjingang, R.mipmap.science_chaoti,
            R.mipmap.science_cikexintiao, R.mipmap.science_daqun, R.mipmap.science_jainglin,
            R.mipmap.science_jinganglang3, R.mipmap.science_mingribianyuan, R.mipmap.science_qiyiboshi,
            R.mipmap.science_shenghuaweiji, R.mipmap.science_taikonglvke, R.mipmap.science_xibushijie,
            R.mipmap.science_xingjichuanyue, R.mipmap.science_xingqiudazhan, R.mipmap.science_zuizhonghuabnxing,
    };
    public static String[] SCIENCE_MOVIES_NAME = {
            "超能陆战队", "变形金刚", "超体", "刺客信条", "大群", "降临", "金刚狼3", "明日边缘", "奇异博士",
            "生化危机", "太空旅客", "西部世界", "星际穿越", "星球大战", "最终幻想"
    };

    public static int[] ROMANTIC_MOVIES_IMG = {
            R.mipmap.romantic_aiyuezhicheng, R.mipmap.romantic_chulianzhejianxiaoshi, R.mipmap.romantic_dahuaxiyou,
            R.mipmap.romantic_jiandietongmeng, R.mipmap.romantic_nidemingzi, R.mipmap.romantic_pengranxindong,
            R.mipmap.romantic_qiyueyuansheng, R.mipmap.romantic_qingsheng, R.mipmap.romantic_shikonglianlvren,
            R.mipmap.romantic_shijiezhiwai, R.mipmap.romantic_weishenmeshita, R.mipmap.romantic_wozuihaopengyoudehunli,
            R.mipmap.romantic_wushiduhui, R.mipmap.romantic_yinweiai, R.mipmap.romantic_yujiannizhiqian, R.mipmap.romantic_yueguangnanhai,
    };
    public static String[] ROMANTIC_MOVIES_NAME = {
            "爱乐之城", "初恋这件小事", "大话西游", "间谍同盟 Allied",
            "你的名字", "怦然心动", "七月与安生", "情圣", "时空恋旅人",
            "世界之外 The Space Between Us", "为什么是他", "我最好朋友的婚礼",
            "五十度灰", "因为爱", "遇见你之前", "月光男孩 Moonlight"
    };
    public static int[] COSTUME_MOVIES_IMG = {
            R.mipmap.costume_dahuatianxian, R.mipmap.costume_daoshixiashan, R.mipmap.costume_direnjie,
            R.mipmap.costume_huangfeihong, R.mipmap.costume_langkejianxin, R.mipmap.costume_longmenfeijia,
            R.mipmap.costume_qinshimingyue, R.mipmap.costume_qundao, R.mipmap.costume_sanshaoyedejian,
            R.mipmap.costume_shifu, R.mipmap.costume_sidamingbu, R.mipmap.costume_wanwanmeixiangdao,
            R.mipmap.costume_weicheng, R.mipmap.wohucanglong, R.mipmap.xiuchundao
    };
    public static String[] COSTUME_MOVIES_NAME = {
            "大话天仙", "道士下山", "狄仁杰之神都龙王", "黄飞鸿之英雄有梦",
            "浪客剑心：京都大火篇", "龙门飞甲", "秦时明月之龙腾万里", "群盗：民乱的时代",
            "三少爷的剑", "师父", "四大名捕2", "万万没想到大电影",
            "危城", "卧虎藏龙：青冥宝剑 ", "绣春刀"
    };

    public static int[] ACTION_MOVIES_IMG = {
            R.mipmap.action_gongfuyujia, R.mipmap.action_jixiantegong, R.mipmap.action_shenghuaweiji,
            R.mipmap.action_danaotianzhu, R.mipmap.action_diezhongdie, R.mipmap.action_fengkuangdemaikesi,
            R.mipmap.action_haoyongqijiaolong, R.mipmap.action_jixieshi2, R.mipmap.action_mimitegong,
            R.mipmap.science_qiyiboshi, R.mipmap.action_shouhuzhe, R.mipmap.action_suduyujiqing7,
            R.mipmap.action_wangpaitegong, R.mipmap.action_yiren, R.mipmap.action_zhiquweihushan
    };
    public static String[] ACTION_MOVIES_NAME = {
            "功夫瑜伽", "极限特工-终极回归", "生化危机：终章", "大闹天竺", "碟中谍5：神秘国度",
            "疯狂的麦克斯4：狂暴之路", "豪勇七蛟龙", "机械师2：复活", "秘密特工 The Man from U.N.C.L.E.", "奇异博士",
            "守护者：世纪战元", "速度与激情7", "王牌特工：特工学院", "蚁人",
            "智取威虎山"
    };

    //推荐
    public static String[] GOODS_RECOMMEND_NAME = {
            "原瓶进口 智利蜜雪莉雅佳美娜红葡萄酒 红酒750ml",
            "拾初花酒～调情礼盒三支装，给生活调个情",
            "锅包肉",
            "番茄鱼片汤",
            "蒜蓉椒盐虾",
            "古法花生糖 纯麦芽糖熬制 300g",
            "果木熏烤八爪鱼足片章鱼足片海味零食180g",
            "烤鱼片零食150g",
            "俞家 / 艾草糍粑 / 糯米年糕 / 100g*5",
            "潮汕枇杷果 潮汕风味蜜饯果脯 广东特产",
            "温州城市礼品 瓯柑蜜炼500g",

    };
    public static String[] GOODS_RECOMMEND_PRICE = {
            "￥ 79.00",
            "￥ 298.00",
            "￥ 26.00",
            "￥ 32.00",
            "￥ 68.00",
            "￥ 33.80",
            "￥ 25.80",
            "￥ 28.80",
            "￥ 28.80",
            "￥ 36.80",
            "￥ 38.80",

    };
    public static int[] GOODS_RECOMMEND_PICTURE = {
            R.mipmap.goods_wine_one, R.mipmap.goods_wine_two, R.mipmap.goods_repast_one, R.mipmap.goods_repast_two, R.mipmap.goods_repast_three,
            R.mipmap.goods_snacks_one, R.mipmap.goods_snacks_two, R.mipmap.goods_snacks_three, R.mipmap.goods_specialty_nine,
            R.mipmap.goods_specialty_ten, R.mipmap.goods_specialty_eleven
    };


    //烟酒
    public static String[] GOODS_TOBACCO_NAME = {
            "原瓶进口 智利蜜雪莉雅佳美娜红葡萄酒 红酒750ml",
            "拾初花酒～调情礼盒三支装，给生活调个情",
            "蔡健雅拜访法国进口回声园克雷圣盾干红葡萄酒",
            "德国啤酒 进口啤酒 奥丁格啤酒白啤酒500ml*24听整箱装",
            "燃点白酒焰火38度浓香型38度500ml",
            "JHBROS（家和兄弟）铁塔威士忌375ml",
            "JHBROS（家和兄弟）B-15威士忌160ml×2",
            "皮特诺尔潘诺2012 Pittnauer Pannobile 2012",
            "法国帕库城堡红葡萄酒 波尔多AOC 花果香气息 巴黎农业大赛金奖",
            "旗达冰酒 Tschida Eiswein",
            "阿根廷圣保罗酒庄原瓶进口高档红酒 奥加阿丰素干红葡萄酒750ml",
            "【干红】赤鹰哈维斯红葡萄酒 RED EMPIRE",
            "【起泡酒】迪马普罗塞克DIAMA PROSECCO 意大利起泡酒",
            "【干红】卡斯柯520干红葡萄酒 2010 CASK520",
            "意大利恋爱季桃红高泡葡萄酒 原瓶进口低度起泡酒女士气泡酒香槟",
            "普洱茶烟 不含尼古丁 烟支茶 可以喝的茶烟",
    };
    public static String[] GOODS_TOBACCO_PRICE = {
            "￥ 79.00",
            "￥ 298.00",
            "￥ 488.00",
            "￥ 269.00",
            "￥ 79.00",
            "￥ 98.00",
            "￥ 98.00",
            "￥ 618.00",
            "￥ 138.00",
            "￥ 599.00",
            "￥ 298.00",
            "￥ 128.00",
            "￥ 358.00",
            "￥ 1680.00",
            "￥ 68.00",
            "￥ 199.00",
    };
    public static int[] GOODS_TOBACCO_PICTURE = {
            R.mipmap.goods_wine_one, R.mipmap.goods_wine_two, R.mipmap.goods_wine_three,
            R.mipmap.goods_wine_four, R.mipmap.goods_wine_five, R.mipmap.goods_wine_six,
            R.mipmap.goods_wine_seven, R.mipmap.goods_wine_eight, R.mipmap.goods_wine_nine,
            R.mipmap.goods_wine_ten, R.mipmap.goods_wine_eleven, R.mipmap.goods_wine_twelve,
            R.mipmap.goods_wine_thirteen, R.mipmap.goods_wine_fourteen, R.mipmap.goods_wine_fiveteen,
            R.mipmap.goods_wine_sixteen
    };

    //餐饮
    public static String[] GOODS_REPAST_NAME = {
            "锅包肉",
            "番茄鱼片汤",
            "蒜蓉椒盐虾",
            "飞饼蔬菜鸡蛋挞",
            "XO酱脆皮肠炒面",
            "木须肉",
            "燕麦香米饼",
            "无花果百合莲子糖水",
            "彩虹土豆丝披萨",
            "美式炖鸡汤",
            "泰式咖喱蟹",
            "意大利荷包肉",
            "水煮肉片",
            "麻婆豆腐",
            "松蘑炖鸡",
            "红焖羊肉",
            "菠萝咕咾肉",
            "小笨鸡炖蘑菇",
            "上海油炸臭豆腐",
            "贝尼奥 果蔬汁饮料 330ml",
    };
    public static String[] GOODS_REPAST_PRICE = {
            "￥ 26.00",
            "￥ 32.00",
            "￥ 68.00",
            "￥ 24.00",
            "￥ 33.00",
            "￥ 28.00",
            "￥ 19.00",
            "￥ 19.00",
            "￥ 48.00",
            "￥ 52.00",
            "￥ 58.00",
            "￥ 29.00",
            "￥ 58.00",
            "￥ 32.00",
            "￥ 26.00",
            "￥ 52.00",
            "￥ 68.00",
            "￥ 32.00",
            "￥ 64.00",
            "￥ 26.00",
            "￥ 199.00"
    };
    public static int[] GOODS_REPAST_PICTURE = {
            R.mipmap.goods_repast_one, R.mipmap.goods_repast_two, R.mipmap.goods_repast_three,
            R.mipmap.goods_repast_four, R.mipmap.goods_repast_five, R.mipmap.goods_repast_six,
            R.mipmap.goods_repast_seven, R.mipmap.goods_repast_eight, R.mipmap.goods_repast_nine,
            R.mipmap.goods_repast_ten, R.mipmap.goods_repast_eleven, R.mipmap.goods_repast_twelve,
            R.mipmap.goods_repast_thirteen, R.mipmap.goods_repast_fourteen, R.mipmap.goods_repast_fiveteen,
            R.mipmap.goods_repast_sisteen, R.mipmap.goods_repast_seventeen, R.mipmap.goods_repast_eighteen,
            R.mipmap.goods_repast_nineteen, R.mipmap.goods_repast_twenty, R.mipmap.goods_repast_twenty_one,
    };

    //零食
    public static String[] GOODS_SNACKS_NAME = {
            "古法花生糖 纯麦芽糖熬制 300g",
            "果木熏烤八爪鱼足片章鱼足片海味零食180g",
            "烤鱼片零食150g",
            "冻干草莓干 草莓干脆片水果干 办公室小零食 25g*5包",
            "【天然果干】综合水果脆片35g/袋 6种水果滋味",
            "龙井茶糕 江南特色糕点 200g",
            "手工牛轧饼干200g 原味/抹茶/蔓越莓 台湾风味零食小吃",
            "可可酒渍樱桃大cookie 7枚",
            "巧克力大cookie 50g* 7枚",
            "茉莉花饼 茉上花开 余香绕枕",
            "马其顿进口 芬奇榛仁酱夹心饼干 80g",
            "马其顿进口 芬奇果酱夹心饼干 165g",
            "良品铺子芒果干108gx2袋",
            "盼盼法式小面包奶香味440g",
            "喜之郎cici果汁果冻爽特惠装150g*5/怀旧零食品",
            "百草味 台湾特色糕点 凤梨酥300g",
    };
    public static String[] GOODS_SNACKS_PRICE = {
            "￥ 33.80",
            "￥ 25.80",
            "￥ 28.80",
            "￥ 39.50",
            "￥ 13.90",
            "￥ 32.00",
            "￥ 24.80",
            "￥ 59.00",
            "￥ 49.00",
            "￥ 22.00",
            "￥ 11.90",
            "￥ 17.90",
            "￥ 18.90",
            "￥ 10.80",
            "￥ 12.90",
            "￥ 29.80",
    };
    public static int[] GOODS_SNACKS_PICTURE = {
            R.mipmap.goods_snacks_one, R.mipmap.goods_snacks_two, R.mipmap.goods_snacks_three,
            R.mipmap.goods_snacks_four, R.mipmap.goods_snacks_five, R.mipmap.goods_snacks_six,
            R.mipmap.goods_snacks_seven, R.mipmap.goods_snacks_eight, R.mipmap.goods_snacks_nine,
            R.mipmap.goods_snacks_ten, R.mipmap.goods_snacks_eleven, R.mipmap.goods_snacks_twelve,
            R.mipmap.goods_snacks_thirteen, R.mipmap.goods_snacks_fourteen, R.mipmap.goods_snacks_fiveteen,
            R.mipmap.goods_snacks_sixteen
    };

    //特产
    public static String[] GOODS_SPECIALTY_NAME = {
            "青品尚 东北传统手工大煎饼 香甜易消化 零添加 4份/袋（约155g）",
            "等一味食材 潮汕手撕土猪肉条 香辣 手工农家自制 潮汕特产180g",
            "嘉禾屿 元贝瑶柱 扇贝干煲粥 无盐干贝 海鲜干货200g",
            "等一味|手工山楂球 雪丽球 潮汕特产零食小吃280g",
            "嘉禾屿 厦门海蛎干生蚝干 牡蛎干 海鲜水产干货180g",
            "嘉禾屿 野生虾干 淡干金钩海米虾米虾仁 海鲜干货150g",
            "小七陈卤 香辣牛肚 零食卤味 150克",
            "俞家 / 黑糖年糕 / 红糖年糕 / 500g",
            "俞家 / 艾草糍粑 / 糯米年糕 / 100g*5",
            "潮汕枇杷果 潮汕风味蜜饯果脯 广东特产",
            "温州城市礼品 瓯柑蜜炼500g",
    };
    public static String[] GOODS_SPECIALTY_PRICE = {
            "￥ 8.00",
            "￥ 29.00",
            "￥ 158.00",
            "￥ 16.00",
            "￥ 28.00",
            "￥ 60.00",
            "￥ 29.80",
            "￥ 16.80",
            "￥ 28.80",
            "￥ 36.00",
            "￥ 38.00",
    };
    public static int[] GOODS_SPECIALTY_PICTURE = {
            R.mipmap.goods_specialty_one, R.mipmap.goods_specialty_two, R.mipmap.goods_specialty_three,
            R.mipmap.goods_specialty_four, R.mipmap.goods_specialty_five, R.mipmap.goods_specialty_six,
            R.mipmap.goods_specialty_seven, R.mipmap.goods_specialty_eight, R.mipmap.goods_specialty_nine,
            R.mipmap.goods_specialty_ten, R.mipmap.goods_specialty_eleven
    };

    //礼品
    public static String[] GOODS_PRESENT_NAME = {
            "植物私塾 酸梅汤 4罐装礼盒",
            "小巨蛋T1便携式茶具礼品套装砂岩釉茶盒版（极客黑）",
            "农家自产纯天然蜂蜜礼品礼盒 送礼 野生火山荔枝蜜",
            "纯手工刺绣土布 中国风商务礼品",
            "等蜂来蜂蜜 四川金堂田园蜜 618g",
            "梳心礼盒套装 结发梳心",
    };
    public static String[] GOODS_PRESENT_PRICE = {
            "￥ 158.00",
            "￥ 368.00",
            "￥ 189.00",
            "￥ 296.00",
            "￥ 69.00",
            "￥ 196.00",
    };
    public static int[] GOODS_PRESENT_PICTURE = {
            R.mipmap.goods_present_one, R.mipmap.goods_present_two, R.mipmap.goods_present_three,
            R.mipmap.goods_present_four, R.mipmap.goods_present_five, R.mipmap.goods_present_six,
    };

    //设备
    public static String[] GOODS_EQUIPMENT_NAME = {
            "全高清玻璃镜头智能微投KUPA F1",
            "交互智能投触一体机KUPA P1",
            "全高清激光投影电视KUPA P2",
            "微型超短焦投影KUPA P3",
    };
    public static String[] GOODS_EQUIPMENT_PRICE = {
            "新品上市",
            "￥ 29800.00",
            "￥ 39800.00",
            "新品上市",
    };
    public static int[] GOODS_EQUIPMENT_PICTURE = {
            R.mipmap.goods_equipment_one, R.mipmap.goods_equipment_two, R.mipmap.goods_equipment_three,
            R.mipmap.goods_equipment_four
    };
}
