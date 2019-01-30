package com.glinlf.growth.utils;

import java.util.UUID;

/**
 * @author created by glinlf
 * @date 2019/1/29
 * @remark id 生成策略
 */
public class IdKit {

    /**
     * 生成 UUID
     *
     * @return
     */
    public static String UUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
    }


    /**
     * 获得10位 unix时间戳
     *
     * @return
     */
    public static Long getUnixTime() {
        return Long.valueOf(String.valueOf(System.currentTimeMillis()).substring(0, 10));
    }
}
