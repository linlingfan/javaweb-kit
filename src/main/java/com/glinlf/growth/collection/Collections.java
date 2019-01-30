package com.glinlf.growth.collection;

/**
 * @author created by glinlf
 * @date 2018/7/24
 * @remark
 */
public class Collections {

    /**
     * return a chain mapper which can push infinite
     *
     * @return
     */
    public static ChainMap chainMap() {
        return new ChainMap();
    }

    /**
     * return a chain list which can push infinite
     *
     * @return
     */
    public static ChainList chainList() {
        return new ChainList();
    }

    /**
     * return a chain set which can push infinite
     *
     * @return
     */
    public static ChainSet chainSet() {
        return new ChainSet();
    }

}
