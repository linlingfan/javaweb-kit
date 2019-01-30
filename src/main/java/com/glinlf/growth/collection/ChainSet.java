package com.glinlf.growth.collection;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author created by glinlf
 * @date 2018/7/24
 * @remark
 */
public class ChainSet<T> implements Serializable {

    private Set<T> set = new HashSet<>();

    public ChainSet() {
    }

    public ChainSet(Set<T> set) {
        this.set = set;
    }

    public ChainSet<T> push(T t) {
        if (t instanceof ChainList) t = (T) ((ChainList) t).toList();
        else if (t instanceof ChainMap) t = (T) ((ChainMap) t).toMap();
        else if (t instanceof ChainSet) t = (T) ((ChainSet) t).toSet();
        set.add(t);
        return this;
    }

    public Set<T> toSet() {
        return set;
    }

    public Set<T> toSet(ChainSet<T> cs) {
        return set;
    }
}
