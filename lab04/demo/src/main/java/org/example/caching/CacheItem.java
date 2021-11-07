package org.example.caching;

import java.util.List;

public class CacheItem {
    public List items;
    String key;
    CacheItem (String key, List items){
        this.key = key;
        this.items = items;
    }
    public CacheItem(){}

    public String getKey(){
        return key;
    }

    public List getItems(){
        return items;
    }
}


