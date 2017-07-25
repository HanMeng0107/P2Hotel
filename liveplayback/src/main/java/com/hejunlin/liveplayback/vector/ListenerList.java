package com.hejunlin.liveplayback.vector;

import java.util.Vector;

/**
 * Created by HM on 2017/3/14 21:30
 */

public class ListenerList extends Vector
{
    /** 添加对象到  ListenerList 集合中，如果集合中包含此对象返回false，否则集合中添加对象返回true*/
    @Override
    public boolean add(Object obj)
    {
        if (0 <= indexOf(obj))
            return false;
        return super.add(obj);
    }
}

