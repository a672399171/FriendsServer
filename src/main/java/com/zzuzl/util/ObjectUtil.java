package com.zzuzl.util;

/**
 * Created by zhanglei53 on 2016/8/19.
 */
public class ObjectUtil {

    public static boolean hasNull(Object... args) {
        for(Object object : args) {
            if(object == null) {
                return true;
            }
        }
        return false;
    }
}
