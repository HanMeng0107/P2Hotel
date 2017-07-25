package com.hejunlin.liveplayback.utils;

import com.hejunlin.liveplayback.R;
import com.hejunlin.liveplayback.entity.Movie;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by HM on 2017/4/6 15:51
 */

public class InitMovies {
    public static List<Movie> initLocalMovies(List<Movie> movieInfos) {
        List<Movie> movies = new ArrayList<>();
        Movie movie = null;
        String movieName;
        for (int i = 0; i < movieInfos.size(); i++) {
            movieName = movieInfos.get(i).getMovieName();
            if (movieName.equals("极乐空间.mp4")) {
                movie = new Movie();
                movie.setMovieName(movieName);
                movie.setMovieGrade("6.2分");
                movie.setMovieDateAndArea("2013-09-05");
                movie.setMovieType("动作 / 科幻 / 剧情");
                movie.setMovieDuration("109分钟");
                movie.setMovieDirector("尼尔·布洛姆坎普");
                movie.setMovieProtagonist("马特·达蒙 / 朱迪·\n" +
                        "福斯特 / 沙尔托·科普雷 / 艾莉丝·布拉加 / 迭戈·鲁纳");
                movie.setMovieAbstract("22世纪中叶，地球并未借助文明和科技的进步变得更加\n" +
                        "美好，反而在各种贪欲荼毒之下变成毫无希望的废墟荒原。少数极度富有之人抛弃生身故乡，" +
                        "在漂浮于地球之上的空间站上建立了宛若天堂般美好的乐土，继续过着享乐奢靡的幸福生活。" +
                        "而绝大多数的穷苦人不得不留在千疮百孔的地球上，在绝望的混乱之中苟延残喘，同时被迫忍受各种严苛可笑的联邦法规的压迫。");
                movie.setMoviePreviewPath("android.resource://com.hejunlin.liveplayback/" + R.raw.jlkj);
                movie.setMoviePlayPath(movieInfos.get(i).getMoviePlayPath());
                movie.setMovieThumb(R.mipmap.local_jilekongjian);
                movies.add(movie);
            } else if (movieName.equals("愤怒的小鸟.mp4")) {
                movie = new Movie();
                movie.setMovieName(movieName);
                movie.setMovieGrade("7.0分");
                movie.setMovieDateAndArea("2016-05-20");
                movie.setMovieType("喜剧 / 动作 / 动画");
                movie.setMovieDuration("97分钟");
                movie.setMovieDirector("费格尔·雷利 / 克雷·卡提斯");
                movie.setMovieProtagonist("杰森·苏戴奇斯 / 乔什·加德 / 丹尼·麦克布耐德 / 玛娅·鲁道夫 / 西恩·潘\n");
                movie.setMovieAbstract("在一座与世隔绝的小岛上，生活着种类繁多且快乐无忧的小鸟。不过他们中间总有异类存在，" +
                        "比如离群索居的胖红（杰森·苏戴奇斯 Jason Sudeikis 配音），孤儿出身且有些另类的怪异容貌让他成为其他鸟儿嘲笑和奚落的对象。" +
                        "久而久之，胖红也养成了促狭易怒的性格。在被法官叛出参 加情绪管理课程期间，他结识了速度极快的飞镖黄（乔什·加德 Josh Gad 配音）和一不小心便会引起爆炸的炸弹黑（丹尼·麦克布耐德 Danny McBride 配音）。" +
                        "三个怪胎由此闹出不少的笑话。未过多久，神秘的绿猪莱纳德（比尔·哈德尔 Bill Hader 配音）带着手下来到小岛，他们展现出友善的面孔，" +
                        "可又仿佛在暗中策划这什么。 ");
                movie.setMoviePreviewPath("android.resource://com.hejunlin.liveplayback/" + R.raw.fndxn);
                movie.setMoviePlayPath(movieInfos.get(i).getMoviePlayPath());
                movie.setMovieThumb(R.mipmap.local_fennudexiaoniao);
                movies.add(movie);
            } else if (movieName.equals("鸷鸟.mp4")) {
                movie = new Movie();
                movie.setMovieName(movieName);
                movie.setMovieGrade("6.2分");
                movie.setMovieDateAndArea("2013-09-05");
                movie.setMovieType("动作 / 科幻 / 剧情");
                movie.setMovieDuration("109分钟");
                movie.setMovieDirector("尼尔·布洛姆坎普");
                movie.setMovieProtagonist("马特·达蒙 / 朱迪·\n" +
                        "福斯特 / 沙尔托·科普雷 / 艾莉丝·布拉加 / 迭戈·鲁纳");
                movie.setMovieAbstract("22世纪中叶，地球并未借助文明和科技的进步变得更加\n" +
                        "美好，反而在各种贪欲荼毒之下变成毫无希望的废墟荒原。少数极度富有之人抛弃生身故乡，" +
                        "在漂浮于地球之上的空间站上建立了宛若天堂般美好的乐土，继续过着享乐奢靡的幸福生活。" +
                        "而绝大多数的穷苦人不得不留在千疮百孔的地球上，在绝望的混乱之中苟延残喘，同时被迫忍受各种严苛可笑的联邦法规的压迫。");
                movie.setMoviePreviewPath("android.resource://com.hejunlin.liveplayback/" + R.raw.zn);
                movie.setMoviePlayPath(movieInfos.get(i).getMoviePlayPath());
                movie.setMovieThumb(R.mipmap.local_zhiniao);
                movies.add(movie);
            } else if (movieName.equals("韩国MTV.mp4")) {
                movie = new Movie();
                movie.setMovieName(movieName);
                movie.setMovieGrade("4.9分");
                movie.setMovieDateAndArea("2016年 | 中国大陆");
                movie.setMovieType("剧情 / 动作 / 武侠 / 古装");
                movie.setMovieDuration("107分钟");
                movie.setMovieDirector("尔冬升");
                movie.setMovieProtagonist("林更新 / 何润东 / 江一燕 / 蒋梦婕 / 鲍起静 / 顾曹斌");
                movie.setMovieAbstract("“剑气纵横三万里，一剑光寒十九洲”，神剑山庄三少爷谢晓峰（林更新 饰）惊才绝艳，十年来历经上千大战未尝一败，" +
                        "被天下人尊为“剑神”。传奇剑客燕十三（何润东 饰）一直以谢晓峰为目标，苦修剑道，终于在生死边缘悟出惊天地泣鬼神的“绝命十三剑”。" +
                        "一时之间，江湖沸腾，然而，就在燕十三赶到神剑山庄下战书的时候，迎接他的，却是三少爷谢晓峰的灵柩。燕十三因痛失对手大感失望，" +
                        "此时神秘女子慕容秋荻（江一燕 饰）出现并告知燕十三，谢晓峰并没有死，要想找到他决战，燕十三就必须要替她杀一个人……");
                movie.setMoviePreviewPath("android.resource://com.hejunlin.liveplayback/" + R.raw.mtv);
                movie.setMoviePlayPath(movieInfos.get(i).getMoviePlayPath());
                movie.setMovieThumb(R.mipmap.local_mtv);
                movies.add(movie);
            } else if (movieName.equals("世界杯.mp4")) {
                movie = new Movie();
                movie.setMovieName(movieName);
                movie.setMovieGrade("4.9分");
                movie.setMovieDateAndArea("2016年 | 中国大陆");
                movie.setMovieType("剧情 / 动作 / 武侠 / 古装");
                movie.setMovieDuration("107分钟");
                movie.setMovieDirector("尔冬升");
                movie.setMovieProtagonist("林更新 / 何润东 / 江一燕 / 蒋梦婕 / 鲍起静 / 顾曹斌");
                movie.setMovieAbstract("“剑气纵横三万里，一剑光寒十九洲”，神剑山庄三少爷谢晓峰（林更新 饰）惊才绝艳，十年来历经上千大战未尝一败，" +
                        "被天下人尊为“剑神”。传奇剑客燕十三（何润东 饰）一直以谢晓峰为目标，苦修剑道，终于在生死边缘悟出惊天地泣鬼神的“绝命十三剑”。" +
                        "一时之间，江湖沸腾，然而，就在燕十三赶到神剑山庄下战书的时候，迎接他的，却是三少爷谢晓峰的灵柩。燕十三因痛失对手大感失望，" +
                        "此时神秘女子慕容秋荻（江一燕 饰）出现并告知燕十三，谢晓峰并没有死，要想找到他决战，燕十三就必须要替她杀一个人……");
                movie.setMoviePreviewPath("android.resource://com.hejunlin.liveplayback/" + R.raw.sjb);
                movie.setMoviePlayPath(movieInfos.get(i).getMoviePlayPath());
                movie.setMovieThumb(R.mipmap.local_shijiebei);
                movies.add(movie);
            } else {
                movie = new Movie();
                movie.setMovieName(movieName);
                movie.setMovieGrade("4.9分");
                movie.setMovieDateAndArea("2016年 | 中国大陆");
                movie.setMovieType("剧情 / 动作 / 武侠 / 古装");
                movie.setMovieDuration("107分钟");
                movie.setMovieDirector("尔冬升");
                movie.setMovieProtagonist("林更新 / 何润东 / 江一燕 / 蒋梦婕 / 鲍起静 / 顾曹斌");
                movie.setMovieAbstract("“剑气纵横三万里，一剑光寒十九洲”，神剑山庄三少爷谢晓峰（林更新 饰）惊才绝艳，十年来历经上千大战未尝一败，" +
                        "被天下人尊为“剑神”。传奇剑客燕十三（何润东 饰）一直以谢晓峰为目标，苦修剑道，终于在生死边缘悟出惊天地泣鬼神的“绝命十三剑”。" +
                        "一时之间，江湖沸腾，然而，就在燕十三赶到神剑山庄下战书的时候，迎接他的，却是三少爷谢晓峰的灵柩。燕十三因痛失对手大感失望，" +
                        "此时神秘女子慕容秋荻（江一燕 饰）出现并告知燕十三，谢晓峰并没有死，要想找到他决战，燕十三就必须要替她杀一个人……");
                movie.setMoviePreviewPath("android.resource://com.hejunlin.liveplayback/" + R.raw.sjb);
                movie.setMoviePlayPath(movieInfos.get(i).getMoviePlayPath());
                movie.setMovieThumb(R.mipmap.local_shijiebei);
                movies.add(movie);
            }
        }
        return movies;
    }

    public static List<Movie> initNetWorkMovies(List<Movie> movieInfos) {
        List<Movie> movies = new ArrayList<>();
        Movie movie = null;
        String movieName;
        for (int i = 0; i < movieInfos.size(); i++) {
            movieName = movieInfos.get(i).getMovieName();
            if (movieName.equals("生化危机：终章.mp4")) {
                movie = new Movie();
                movie.setMovieName(movieName);
                movie.setMovieGrade("6.7分");
                movie.setMovieDateAndArea("2017年 | 美国");
                movie.setMovieType("动作 / 科幻 / 恐怖");
                movie.setMovieDuration("106分钟");
                movie.setMovieDirector("保罗·安德森");
                movie.setMovieProtagonist("米拉·乔沃维奇 / 伊恩·格雷 / 艾丽·拉特 / 鲁比·罗丝 / 李准基 / 肖恩·罗伯茨 / 威廉·莱维 / 欧文·马肯 / 罗拉 / 艾尔·安德森 / 密尔顿·施尔 / 西沃恩·霍奇森 / 凯文·奥托 / 保罗·汉普赛尔");
                movie.setMovieAbstract("爱丽丝（米拉·乔沃维奇 Milla Jovovich 饰）在华盛顿特区被威斯克背叛后身陷险境，" +
                        "人类几乎要失去最后的希望。作为唯一的幸存者，也是人类对抗僵尸大军的最后防线，爱丽丝必须回到噩梦开始的地方——浣熊市，" +
                        "才能完成拯救世界救赎人类的正义使命。回归故事发生的起点浣熊市，" +
                        "爱丽丝将和昔日的朋友一起对抗僵尸和最新变种怪物，与 保护伞公司展开了一场惊心动魄的终极决战。");
//                movie.setMoviePreviewPath("smb://kupaworld:kupa1806@192.168.0.195/video/preview/01-生化危机终章预告片.mov");
                movie.setMoviePlayPath(movieInfos.get(i).getMoviePlayPath());
                movie.setMovieThumb(R.mipmap.popular_testmovie_shenghuaweiji);
                movies.add(movie);
            } else if (movieName.equals("你的名字.mp4")) {
                movie = new Movie();
                movie.setMovieName(movieName);
                movie.setMovieGrade("8.5分");
                movie.setMovieDateAndArea("2016年 | 日本");
                movie.setMovieType("剧情 / 爱情 / 动画");
                movie.setMovieDuration("106分钟");
                movie.setMovieDirector("新海诚");
                movie.setMovieProtagonist("神木隆之介 / 上白石萌音 / 长泽雅美 / 市原悦子 / 成田凌 / 悠木碧 / 岛崎信长 / 石川界人 / 谷花音");
                movie.setMovieAbstract("在远离大都会的小山村，住着巫女世家出身的高中女孩宫水三叶（上白石萌音 配音）。" +
                        "校园和家庭的原因本就让她充满烦恼，而近一段时间发生的奇怪事件，又让三叶摸不清头脑。" +
                        "不知从何时起，三叶在梦中就会变成一个住在东京的高中男孩。那里有陌生的同学和朋友，有亲切的前辈和繁华的街道，" +
                        "一切都是如此诱人而真实。另一方面，住在东京的高中男孩立花泷（神木隆之介 配音）则总在梦里来到陌生的小山村，" +
                        "以女孩子的身份过着全新的生活。许是受那颗神秘彗星的影响，立花和三叶在梦中交换了身份。他们以他者的角度体验着对方的人生，" +
                        "这期间有愤怒、有欢笑也有暖心。只是两人并不知道，身份交换的背后隐藏着重大而锥心的秘密……" +
                        "　　本片为2016年度日本本土影片票房冠军。 ");
//                movie.setMoviePreviewPath("smb://kupaworld:kupa1806@192.168.0.195/video/preview/你的名字预告片.mp4");
                movie.setMoviePlayPath(movieInfos.get(i).getMoviePlayPath());
                movie.setMovieThumb(R.mipmap.romantic_nidemingzi);
                movies.add(movie);
            } else if (movieName.equals("湄公河行动.mp4")) {
                movie = new Movie();
                movie.setMovieName(movieName);
                movie.setMovieGrade("8.2分");
                movie.setMovieDateAndArea("2016年 | 中国大陆/香港");
                movie.setMovieType("动作 / 犯罪");
                movie.setMovieDuration("124分钟");
                movie.setMovieDirector("林超贤");
                movie.setMovieProtagonist("张涵予 / 彭于晏 / 孙淳 / 陈宝国 / 冯文娟 / 刘显达 / 赵健 / 吴旭东 / 吴嘉龙 / 卢惠光 / 柏华力·莫高彼斯彻 / 维他亚·潘斯林加姆");
                movie.setMovieAbstract("“金三角湄公河上，一处被称为“鬼门关”的河段，两艘来自中国的商船遭到不明身份之人的枪击袭击。" +
                        "未过多久，泰国军方召开新闻发布会，指责中国商船贩卖毒品。虽然发布会宣称船员全部逃亡，" +
                        "但是十三具遭受残忍杀害的中国船员尸体很快被人发现。这起胆大妄为的案件令中国警方大为震惊，" +
                        "云南省缉毒总队队长高刚（张涵予 饰）受命带特别行动小组前往泰国，并与情报员方新武（彭于晏 饰）合作接洽。" +
                        "根据现有资料显示，这件案子由盘踞在金三角的大毒枭糯卡所为。糯卡贪婪残忍，胆大包天，是湄公河流域上一颗惊扰运输安全的毒瘤。" +
                        "为了将这个恶棍绳之于法，中国、老挝、缅甸开展了三国联合巡逻，集中对糯卡的制毒窝点进行扫荡。" +
                        "而高刚等人也深入最危险境地，与丧失人性的贩毒分子进行惨烈对决……"
                );
//                movie.setMoviePreviewPath("smb://kupaworld:kupa1806@192.168.0.195/video/preview/湄公河行动预告片.mp4");
                movie.setMoviePlayPath(movieInfos.get(i).getMoviePlayPath());
                movie.setMovieThumb(R.mipmap.action_meigonghexingdong);
                movies.add(movie);
            } else if (movieName.equals("情圣.mkv")) {
                movie = new Movie();
                movie.setMovieName(movieName);
                movie.setMovieGrade("6.1分");
                movie.setMovieDateAndArea("2016年 | 中国大陆");
                movie.setMovieType("喜剧");
                movie.setMovieDuration("113分钟");
                movie.setMovieDirector("宋晓飞 / 董旭");
                movie.setMovieProtagonist("肖央 / 闫妮 / 小沈阳 / 乔杉 / 艾伦 / 代乐乐 / 常远 / 李成敏 / 车晓 / 田雨");
                movie.setMovieAbstract("“肖瀚（肖央 饰）和妻子沈红（代乐乐 饰）结婚多年，感情早已经归于平淡，在两人漫长的婚姻生活中，" +
                        "肖瀚一直保持着对妻子的忠诚，直到某日，一位名叫yoyo（李成敏 饰）的美丽女子出现在了肖瀚的身边，" +
                        "令肖瀚再也无法忽略内心里的悸动，他决定正视自己的感情，不再受道德和舆论的约束，大胆追求自己真正渴望的爱情。 \n" +
                        "　　为了成功抱得美人归，肖瀚找到了好友艾木（艾伦 饰）、汤怀（乔杉 饰）和刘磊（小沈阳 饰）等人，" +
                        "几人策划了一个看似万无一失的计谋，胜利的果实势在必得。哪知道在执行计划的过程中，肖瀚的老板玛丽莲（闫妮 饰）几度搅局，" +
                        "闹出了一堆的笑料，不仅如此，敏感的沈红亦预感到丈夫正在“搞事情”。");
//                movie.setMoviePreviewPath("smb://kupaworld:kupa1806@192.168.0.195/video/preview/情圣预告片.mp4");
                movie.setMoviePlayPath(movieInfos.get(i).getMoviePlayPath());
                movie.setMovieThumb(R.mipmap.romantic_qingsheng);
                movies.add(movie);
            } else if (movieName.equals("釜山行.mp4")) {
                movie = new Movie();
                movie.setMovieName(movieName);
                movie.setMovieGrade("8.2分");
                movie.setMovieDateAndArea("2016年 | 韩国");
                movie.setMovieType("动作 / 惊悚 / 灾难");
                movie.setMovieDuration("118分钟");
                movie.setMovieDirector("延尚昊");
                movie.setMovieProtagonist("孔侑 / 郑有美 / 马东锡 / 金秀安 / 金义城 / 崔宇植 / 安昭熙 / 沈恩京 / 禹都临");
                movie.setMovieAbstract("证券公司基金管理人石宇（孔侑 饰）光鲜精干，却也是个重利轻义之徒。妻子为此与之决裂，" +
                        "女儿秀安（金秀安 饰）则对如此自私的父亲越来越失望，决定前往釜山和母亲生活。在秀安生日这天，" +
                        "石宇抽出时间陪伴女儿登上开往釜山的特快列车。而与此同时，城市四处出现了极为可疑的暴动事件。" +
                        "政府极力洗白无法掩盖丧尸肆虐的事实，即便懵然无知的列车乘客也因为不速之客的到来而堕入恐慌绝望的地狱中。开车的刹那，" +
                        "一名感染者冲入车厢，而她很快尸变并对目光所及之处的健康人展开血腥屠杀。未过多久，丧尸便呈几何数爆发性地增长。" +
                        "石宇被迫和幸存者的乘客们在逼仄的空间中奋力求生。" +
                        "　　通往釜山的遥遥旅途布满杀机，危难时刻每个幸存者的人性也承受巨大的考验……");
//                movie.setMoviePreviewPath("smb://kupaworld:kupa1806@192.168.0.195/video/preview/釜山行预告片.mp4");
                movie.setMoviePlayPath(movieInfos.get(i).getMoviePlayPath());
                movie.setMovieThumb(R.mipmap.popular_testmovie_shenghuaweiji);
                movies.add(movie);
            } else if (movieName.equals("速度与激情6.mkv")) {
                movie = new Movie();
                movie.setMovieName(movieName);
                movie.setMovieGrade("7.7分");
                movie.setMovieDateAndArea("2013年 | 美国");
                movie.setMovieType("动作 / 犯罪");
                movie.setMovieDuration("107分钟");
                movie.setMovieDirector("林诣彬");
                movie.setMovieProtagonist("范·迪塞尔 / 保罗·沃克 / 卢克·伊万斯 / 道恩·强森 / 米歇尔·罗德里格兹 / 吉娜·卡拉诺 / 盖尔·加朵 / 杰森·斯坦森 / 埃尔莎·帕塔奇 / 乔丹娜·布鲁斯特 / 姜成镐 / 泰瑞斯·吉布森 / 卢达克里斯");
                movie.setMovieAbstract("俄罗斯莫斯科闹市中央，执行押送任务的军方部队遭到一伙训练有素的匪徒袭击，大战过后的现场惨不忍睹，" +
                        "重要的卫星元件遭到劫持。国际刑警霍布斯（道恩·强森 Dwayne Johnson 饰）查明，" +
                        "包括此案在内的一系列案件均系前特种部队军人欧文·肖（卢克·伊万斯 Luke Evans 饰）所为。" +
                        "为了将这群训练有素、老练凶狠的匪徒绳之于法，他辗转找到隐居世外桃源的多米尼克·托雷多（范·迪塞尔 Vin Diesel 饰）助拳。" +
                        "霍布斯开出的条件令人难以拒绝，他不仅可以将托雷多和布莱恩（保罗·沃克 Paul Walker 饰）一伙从前的犯罪记录一笔勾销，" +
                        "此外欧文一伙中竟然还有本该死去的拉蒂（米歇尔·罗德里格兹 Michelle Rodriguez 饰）。 \n" +
                        "　　前所未有的危险战斗，为了安定平凡的生活，这群亡命之徒再上战场");
//                movie.setMoviePreviewPath("smb://kupaworld:kupa1806@192.168.0.195/video/preview/sudu.mp4");
                movie.setMoviePlayPath(movieInfos.get(i).getMoviePlayPath());
                movie.setMovieThumb(R.mipmap.action_suduyujiqing7);
                movies.add(movie);
            } else if (movieName.equals("血战钢锯岭.mkv")) {
                movie = new Movie();
                movie.setMovieName(movieName);
                movie.setMovieGrade("8.7分");
                movie.setMovieDateAndArea("2016年 | 美国/澳大利亚");
                movie.setMovieType("剧情 / 动作 / 传记 / 历史 / 战争");
                movie.setMovieDuration("139分钟");
                movie.setMovieDirector("梅尔·吉布森");
                movie.setMovieProtagonist("安德鲁·加菲尔德 / 萨姆·沃辛顿 / 文斯·沃恩 / 雨果·维文 / 卢克·布雷西 / 泰莉莎·帕尔墨 / 瑞切尔·格里菲斯 / 纳撒尼尔·布佐尼克 / 理查德·劳斯伯格 / 马特·纳夫莱 / 费拉斯·迪拉尼 / 瑞安·柯尔 / 卢克·佩格勒");
                movie.setMovieAbstract("1945年，第二次世界大战接近尾声，作为邪恶轴心重要成员的日本，其嚣张态势已成强弩之末。是年，" +
                        "决定战局走向的冲绳岛战役拉开序幕，成千上万斗志昂扬的美国大兵被派往冲绳，等待他们的则是敌军重兵防守、凶险异常的钢锯岭。" +
                        "在这群人中间，却有一个不愿拿起武器的军医。他名叫戴斯蒙德·道斯（安德鲁·加菲尔德 Andrew Garfield 饰），" +
                        "来自美国的弗吉尼亚。太平洋爆发之际，瘦弱的戴斯蒙德志愿成为救死扶伤的军医而应征入伍。可因童年和家庭的原因，他始终不愿拿起枪支操练，" +
                        "为此宁愿背上拒服兵役的罪名被送上军事法庭。几经周折，戴斯蒙德最终和战友来到了钢锯岭。枪林弹雨，转瞬之间无数人应声倒地。" +
                        "在信仰和信念的支持下，戴斯蒙德仅凭一己之力拯救了数十条濒死的生命……本片根据真人真事改编。");
//                movie.setMoviePreviewPath("smb://kupaworld:kupa1806@192.168.0.195/video/preview/血战钢锯岭预告片.mp4");
                movie.setMoviePlayPath(movieInfos.get(i).getMoviePlayPath());
                movie.setMovieThumb(R.mipmap.action_xuezhangangjuling);
                movies.add(movie);
            } else if (movieName.equals("刺客信条.mp4")) {
                movie = new Movie();
                movie.setMovieName(movieName);
                movie.setMovieGrade("5.5分");
                movie.setMovieDateAndArea("2017年 | 美国 / 法国 / 香港 / 英国 / 台湾 / 马耳他 / 加拿大");
                movie.setMovieType("动作 / 科幻 / 历史 / 冒险");
                movie.setMovieDuration("116分钟");
                movie.setMovieDirector("贾斯汀·库泽尔 ");
                movie.setMovieProtagonist(" 迈克尔·法斯宾德 / 玛丽昂·歌迪亚 / 杰瑞米·艾恩斯 / 布莱丹·格里森 / 夏洛特·兰普林 / 迈克尔·威廉姆斯 / 丹尼斯·门诺切特 / 亚里安妮·拉贝德 / 赫立德·阿卜杜拉 / 艾斯·戴维斯 / 马蒂亚斯·瓦雷拉 / 卡勒姆·特纳 / 卡洛斯·巴登 / 哈维尔·古铁雷斯 / 霍威克·库区科利安");
                movie.setMovieAbstract("卡勒姆·林奇（迈克尔·法斯宾德 饰）在死刑即将执行之前清醒过来，发现他被索菲娅（玛丽昂·歌迪亚 饰）选中，" +
                        "来参加一个能让人类摆脱暴力冲动的计划。虚拟现实机器Animus能让用户体验祖先的记忆，被绑在机器上之后，" +
                        "卡勒姆·林奇意识到他是生活在西班牙宗教法庭时期一位刺客阿圭拉的后裔，他们寻找的是可以控制自由意志的伊甸园苹果。" +
                        "索菲娅在父亲艾伦（杰瑞米·艾恩斯 饰）施加的压力下不情愿地操纵着卡勒姆·林奇回到过去寻找伊甸园苹果在现代世界的下落，" +
                        "威胁着他身体和心理的健康。但在杀手同行穆萨（迈克尔·威廉姆斯 饰）暗示了卡勒姆·林奇，提醒他艾伦有可能动机不纯之后，" +
                        "卡勒姆·林奇开始重新考虑他的行为和动机，而人类自由意志的命运也变得悬而未决……");
//                movie.setMoviePreviewPath("smb://kupaworld:kupa1806@192.168.0.195/video/preview/刺客信条预告片.mp4");
                movie.setMoviePlayPath(movieInfos.get(i).getMoviePlayPath());
                movie.setMovieThumb(R.mipmap.popular_testmovie_cikexintiao);
                movies.add(movie);
            } else if (movieName.equals("星球大战外传侠盗一号.mkv")) {
                movie = new Movie();
                movie.setMovieName(movieName);
                movie.setMovieGrade("7.3分");
                movie.setMovieDateAndArea("2017年 | 美国");
                movie.setMovieType("动作 / 科幻 / 冒险");
                movie.setMovieDuration("134分钟");
                movie.setMovieDirector("加里斯·爱德华斯");
                movie.setMovieProtagonist("菲丽希缇·琼斯 / 迭戈·鲁纳 / 甄子丹 / 本·门德尔森 / 麦斯·米科尔森 / 艾伦·图代克 / 福里斯特·惠特克 / 姜文 / 里兹·阿迈德 / 乔纳森·阿里斯 / 尤妮斯·奥卢米德 / 克莱尔·陈 / 吉米·斯密茨 / 沃维克·戴维斯 / 马克·普雷斯顿 / 吉娜薇·欧瑞丽 / 詹姆斯·厄尔·琼斯");
                movie.setMovieAbstract("这是一个战火频燃、纷争不断的动荡时代，一群有志之士集结在一起，计划盗走帝国大规模杀伤性武器“死星”的设计图。" +
                        "这个在《星球大战》系列里非常著名的重点事件 ，让一群平凡普通人结成了同盟，决定为世界的改变做出贡献；而在绝密行动的进行中，" +
                        "他们也逐渐成长为顶天立地的英雄。");
//                movie.setMoviePreviewPath("smb://kupaworld:kupa1806@192.168.0.195/video/preview/星球大战外传侠盗一号预告片.mp4");
                movie.setMoviePlayPath(movieInfos.get(i).getMoviePlayPath());
                movie.setMovieThumb(R.mipmap.popular_xingqiudazhan);
                movies.add(movie);
            } else if (movieName.equals("三少爷的剑.mkv")) {
                movie = new Movie();
                movie.setMovieName(movieName);
                movie.setMovieGrade("4.9分");
                movie.setMovieDateAndArea("2016年 | 中国大陆");
                movie.setMovieType("剧情 / 动作 / 武侠 / 古装");
                movie.setMovieDuration("107分钟");
                movie.setMovieDirector("尔冬升");
                movie.setMovieProtagonist("林更新 / 何润东 / 江一燕 / 蒋梦婕 / 鲍起静 / 顾曹斌");
                movie.setMovieAbstract("“剑气纵横三万里，一剑光寒十九洲”，神剑山庄三少爷谢晓峰（林更新 饰）惊才绝艳，十年来历经上千大战未尝一败，" +
                        "被天下人尊为“剑神”。传奇剑客燕十三（何润东 饰）一直以谢晓峰为目标，苦修剑道，终于在生死边缘悟出惊天地泣鬼神的“绝命十三剑”。" +
                        "一时之间，江湖沸腾，然而，就在燕十三赶到神剑山庄下战书的时候，迎接他的，却是三少爷谢晓峰的灵柩。燕十三因痛失对手大感失望，" +
                        "此时神秘女子慕容秋荻（江一燕 饰）出现并告知燕十三，谢晓峰并没有死，要想找到他决战，燕十三就必须要替她杀一个人……");
//                movie.setMoviePreviewPath("smb://kupaworld:kupa1806@192.168.0.195/video/preview/三少爷的剑预告片.mp4");
                movie.setMoviePlayPath(movieInfos.get(i).getMoviePlayPath());
                movie.setMovieThumb(R.mipmap.costume_sanshaoyedejian);
                movies.add(movie);
            } else {
                movie = new Movie();
                movie.setMovieName(movieName);
                movie.setMovieGrade("4.9分");
                movie.setMovieDateAndArea("2016年 | 中国大陆");
                movie.setMovieType("剧情 / 动作 / 武侠 / 古装");
                movie.setMovieDuration("107分钟");
                movie.setMovieDirector("尔冬升");
                movie.setMovieProtagonist("林更新 / 何润东 / 江一燕 / 蒋梦婕 / 鲍起静 / 顾曹斌");
                movie.setMovieAbstract("“剑气纵横三万里，一剑光寒十九洲”，神剑山庄三少爷谢晓峰（林更新 饰）惊才绝艳，十年来历经上千大战未尝一败，" +
                        "被天下人尊为“剑神”。传奇剑客燕十三（何润东 饰）一直以谢晓峰为目标，苦修剑道，终于在生死边缘悟出惊天地泣鬼神的“绝命十三剑”。" +
                        "一时之间，江湖沸腾，然而，就在燕十三赶到神剑山庄下战书的时候，迎接他的，却是三少爷谢晓峰的灵柩。燕十三因痛失对手大感失望，" +
                        "此时神秘女子慕容秋荻（江一燕 饰）出现并告知燕十三，谢晓峰并没有死，要想找到他决战，燕十三就必须要替她杀一个人……");
//                movie.setMoviePreviewPath("smb://kupaworld:kupa1806@192.168.0.195/video/preview/三少爷的剑预告片.mp4");
                movie.setMoviePlayPath(movieInfos.get(i).getMoviePlayPath());
                movie.setMovieThumb(R.mipmap.costume_sanshaoyedejian);
                movies.add(movie);
            }
        }
        return movies;
    }

}
