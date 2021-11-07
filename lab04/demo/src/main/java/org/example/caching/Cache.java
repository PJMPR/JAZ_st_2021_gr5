package org.example.caching;

import java.util.ArrayList;
import java.util.List;
import org.example.caching.generic.CacheItem;

public class Cache {
    private static Cache instance;
    public List<CacheItem> cachedItems = new ArrayList<>();
    private Cache(){}
    public Cache(List <CacheItem> cachedItems){
        this.cachedItems=cachedItems;
    }

    public static Cache getInstance(){
        if (instance == null){
            instance = new Cache();
        }
        return instance;
    }

    public <T> void add(String key, T item){
        cachedItems.add(new CacheItem(key, item));
    }

    public <T> T get(String key, Class<T> clazz){
        for (CacheItem cachedItem:cachedItems){
            if (cachedItem.getKey().equals(key)){
                return (T) cachedItem.getItem();
            }
        }
        return null;
    }

    public Object get(String key){
        for (CacheItem cachedItem:cachedItems){
            if (cachedItem.getKey().equals(key)){
                return cachedItem.getItem();
            }
        }
        return null;
    }
}
