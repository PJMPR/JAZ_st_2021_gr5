package org.example.caching.generic;

import java.util.List;

public class CacheItem<T> extends org.example.caching.CacheItem {
    public T item;
    String key;
    public CacheItem(String key, T item){
        this.key = key;
        this.item = item;
    }
    public String getKey(){
        return key;
    }

    public <T> T getItem(){
        return (T) item;
    }
}
