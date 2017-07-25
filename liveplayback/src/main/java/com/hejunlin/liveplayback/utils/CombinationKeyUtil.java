package com.hejunlin.liveplayback.utils;

import android.view.KeyEvent;

/**
 * @author xiong_it
 * @des 组合快捷键值匹配工具类
 * @time 2015-3-11
 * @link http://blog.csdn.net/xiong_it
 */
public class CombinationKeyUtil {
    private String[] mKeyStrings;//自定义的遥控器按键组合
    private int index = 0;
    private int length = 0;

    public CombinationKeyUtil(String[] keyStrings) {
        mKeyStrings = keyStrings;
        length = mKeyStrings.length;
    }

    public boolean isMatch(int keyCode) {
        boolean isMatch = false;

        String keyCodeToString = KeyEvent.keyCodeToString(keyCode);//将按键值转为字符串
        String keyCodeIndexString = mKeyStrings[index];//取出预设好的按键数组中的某一位字符串

        if (keyCodeToString.equals(keyCodeIndexString)) {//如果按下的键值与预设的键值相同，继续对比下一个键值
            index++;
            if (length == index) {
                isMatch = true;
                index = 0;
            } else {
                isMatch = false;
            }
        } else {
            index = 0;
        }
        return isMatch;
    }
}
