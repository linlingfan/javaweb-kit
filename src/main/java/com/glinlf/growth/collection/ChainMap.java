package com.glinlf.growth.collection;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * @author glinlf
 * @create 2018-09-06
 * 链式map
 */
public class ChainMap<K, V> implements Serializable {

    private Map<K, V> map = new HashMap<>();

    public ChainMap() {
    }

    public Map<K, V> getMap() {
        return map;
    }

    public void setMap(Map<K, V> map) {
        this.map = map;
    }

    public ChainMap push(K key, V value) {
        if (value instanceof ChainMap) value = (V) ((ChainMap) value).toMap();
        else if (value instanceof ChainList) value = (V) ((ChainList) value).toList();
        else if (value instanceof ChainSet) value = (V) ((ChainSet) value).toSet();

        this.map.put(key, value);
        return this;
    }

    public Map<K, V> toMap() {
        return this.map;
    }
}
