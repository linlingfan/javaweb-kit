package com.glinlf.growth.collection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author created by glinlf
 * @date 2018/7/24
 * @remark
 */
public class ChainList<T> implements Serializable {

    private List<T> list = new ArrayList<>();

    public ChainList() {
    }

    public ChainList(List<T> list) {
        this.list = list;
    }

    public ChainList<T> setList(List<T> list) {
        this.list = list;
        return this;
    }

    public ChainList<T> push(T t) {
        if (t instanceof ChainList) t = (T) ((ChainList) t).toList();
        else if (t instanceof ChainMap) t = (T) ((ChainMap) t).toMap();
        else if (t instanceof ChainSet) t = (T) ((ChainSet) t).toSet();

        this.list.add(t);
        return this;
    }

    public List<T> toList() {
        return list;
    }

}
