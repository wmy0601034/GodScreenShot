package com.nanningzhuanqian.vscreenshot.model;

import com.nanningzhuanqian.vscreenshot.R;
import java.util.Random;

public class RandomManager {

    private static RandomManager mThis;

    private RandomManager() {
        super();
    }

    public static RandomManager getInstance() {

        if (mThis == null)
            mThis = new RandomManager();

        return mThis;
    }

    public static int[] localAvatarRes = {R.mipmap.app_images_role_10000, R.mipmap.app_images_role_10002,
            R.mipmap.app_images_role_10002, R.mipmap.app_images_role_10003, R.mipmap.app_images_role_10004,
            R.mipmap.app_images_role_10005, R.mipmap.app_images_role_10006, R.mipmap.app_images_role_10007,
            R.mipmap.app_images_role_10008, R.mipmap.app_images_role_10009, R.mipmap.app_images_role_10010,
            R.mipmap.app_images_role_10011, R.mipmap.app_images_role_10012, R.mipmap.app_images_role_10013,
            R.mipmap.app_images_role_10014, R.mipmap.app_images_role_10015, R.mipmap.app_images_role_10016,
            R.mipmap.app_images_role_10017, R.mipmap.app_images_role_10018, R.mipmap.app_images_role_10019,
            R.mipmap.app_images_role_10020, R.mipmap.app_images_role_10021, R.mipmap.app_images_role_10022,
            R.mipmap.app_images_role_10023, R.mipmap.app_images_role_10024, R.mipmap.app_images_role_10025,
            R.mipmap.app_images_role_10026, R.mipmap.app_images_role_10027, R.mipmap.app_images_role_10028,
            R.mipmap.app_images_role_10029, R.mipmap.app_images_role_10030, R.mipmap.app_images_role_10031,
            R.mipmap.app_images_role_10032, R.mipmap.app_images_role_10033, R.mipmap.app_images_role_10034,
            R.mipmap.app_images_role_10035, R.mipmap.app_images_role_10036, R.mipmap.app_images_role_10037,
            R.mipmap.app_images_role_10038, R.mipmap.app_images_role_10039, R.mipmap.app_images_role_10040,
            R.mipmap.app_images_role_10041, R.mipmap.app_images_role_10042, R.mipmap.app_images_role_10043,
            R.mipmap.app_images_role_10044, R.mipmap.app_images_role_10045, R.mipmap.app_images_role_10046,
            R.mipmap.app_images_role_10047, R.mipmap.app_images_role_10048, R.mipmap.app_images_role_10049,
            R.mipmap.app_images_role_10050, R.mipmap.app_images_role_10051, R.mipmap.app_images_role_10052,
            R.mipmap.app_images_role_10053, R.mipmap.app_images_role_10054, R.mipmap.app_images_role_10055,
            R.mipmap.app_images_role_10056, R.mipmap.app_images_role_10057, R.mipmap.app_images_role_10058,
            R.mipmap.app_images_role_10059, R.mipmap.app_images_role_10060, R.mipmap.app_images_role_10061,
            R.mipmap.app_images_role_10062, R.mipmap.app_images_role_10063, R.mipmap.app_images_role_10064,
            R.mipmap.app_images_role_10065, R.mipmap.app_images_role_10066, R.mipmap.app_images_role_10067,
            R.mipmap.app_images_role_10068, R.mipmap.app_images_role_10069, R.mipmap.app_images_role_10070,
            R.mipmap.app_images_role_10071, R.mipmap.app_images_role_10072, R.mipmap.app_images_role_10073,
            R.mipmap.app_images_role_10074, R.mipmap.app_images_role_10075, R.mipmap.app_images_role_10076,
            R.mipmap.app_images_role_10077, R.mipmap.app_images_role_10078, R.mipmap.app_images_role_10079,
            R.mipmap.app_images_role_10080, R.mipmap.app_images_role_10081, R.mipmap.app_images_role_10082,
            R.mipmap.app_images_role_10083, R.mipmap.app_images_role_10084, R.mipmap.app_images_role_10085,
            R.mipmap.app_images_role_10086, R.mipmap.app_images_role_10087, R.mipmap.app_images_role_10088,
            R.mipmap.app_images_role_10089, R.mipmap.app_images_role_10090, R.mipmap.app_images_role_10091,
            R.mipmap.app_images_role_10092, R.mipmap.app_images_role_10093, R.mipmap.app_images_role_10094,
            R.mipmap.app_images_role_10095, R.mipmap.app_images_role_10096, R.mipmap.app_images_role_10097,
            R.mipmap.app_images_role_10098, R.mipmap.app_images_role_10099};

    public static int getAvatarRes() {
        Random random = new Random();
        int position  = random.nextInt(100);
        return localAvatarRes[position];
    }

    public static String [] conversationContents = {"我要做代理","代理怎么做","代理相关","代理","求代理","我想做代理","做代理","想做代理","怎么做代理",
            "代理怎么操作","求做代理"};

    public static String [] conversationContentsFrom = {"群里看到的","朋友介绍的","贴吧看到的","朋友圈看到的","QQ群看到的","微信群看到的","论坛看到的",
            "论坛看到的","公众号看到的","扫码加的你","群里加的你","朋友介绍加的你","贴吧看到加的你","朋友圈看到加的你","Q群看到加的你","微信群加的你","论坛看到加的你"};

    public static String [] contractContents = {"求加","加我","求通过"};

    public static String [] conversationSpace={","," ","!","~","。","!!","!!!"};

    public static String getRandomContent(){
        Random random = new Random();
        int position = random.nextInt(11);
        return conversationContents[position];
    }

    public static String getRandomName() {
        int type = getRealNameType();
        String name = "";
        String firstName = "";
        if (type == NAME_TYPE_PREFIX_NAME) {
            Random randomPreFix = new Random();
            int firstNameIndex =randomPreFix.nextInt(realFirstName.length-1);
            firstName = realFirstName[firstNameIndex];
            int prefixIndex = randomPreFix.nextInt(3);
            name = prefixName[prefixIndex]+firstName;
        } else if (type == NAME_TYPE_POSTFIX_NAME) {
            Random randomPostFix = new Random();
            int firstNameIndex =randomPostFix.nextInt(realFirstName.length-1);
            firstName = realFirstName[firstNameIndex];
            int postfixIndex = randomPostFix.nextInt(8);
            name = firstName+postfixName[postfixIndex];
        } else if (type == 2) {

        }
        return name;
    }

    public static String[] realFirstName = {"王", "李", "张", "刘", "陈", "杨", "黄", "吴", "赵", "周",
            "徐", "孙", "马", "朱", "胡", "林", "郭", "何", "高", "罗", "郑", "梁", "谢", "宋", "唐", "许", "邓", "冯", "韩",
            "曹", "曾", "彭", "萧", "蔡", "潘", "田", "董", "袁", "于", "余", "叶", "蒋", "杜", "苏", "魏", "程", "吕", "丁", "沈",
            "任", "姚", "卢", "傅", "钟", "姜", "崔", "谭", "廖", "范", "汪", "陆", "金", "石", "戴", "贾", "韦", "夏", "邱", "方",
            "侯", "邹", "熊", "孟", "秦", "白", "江", "阎", "薛", "尹", "段", "雷", "黎", "史", "龙", "陶", "贺", "顾", "毛", "郝",
            "龚", "邵", "万", "钱", "严", "赖", "覃", "洪", "武", "莫", "孔", "汤", "向", "常", "温", "康", "施", "文" };

//    public static final String[] realLastName = {
//
//    };

    public static String[] prefixName = {"阿", "老", "小" };

    public static String[] postfixName = {"老板", "总", "科", "董", "哥", "姐", "工", "局" };

    private static final int NAME_TYPE_PREFIX_NAME = 0; //如 老+林
    private static final int NAME_TYPE_POSTFIX_NAME = 1; //如 林+哥
//    private static final int NAME_TYPE_SINGLE_NAME = 2; //单名

    //名字类型
    public static int getRealNameType() {
        Random random = new Random();
        return random.nextInt(2);
    }

    public static long getRandomTime() {
        Random random = new Random();
        int randomInt = random.nextInt(100);
        return System.currentTimeMillis() - randomInt * 60 * 1000;
    }

    public static boolean getBoolean(){
        Random random = new Random();
        return random.nextBoolean();
    }
}
