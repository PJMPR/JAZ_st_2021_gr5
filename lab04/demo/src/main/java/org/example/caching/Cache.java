package org.example.caching;

import java.util.ArrayList;
import java.util.List;

public class Cache {
    public static Cache instance;
    public List<CacheItem> cachedItems;

    public static Cache getInstance() {
        if (instance == null) {
            instance = new Cache();
        }
        return instance;
    }


    public <T> void add(String key, T item) {
        CacheItem<T> cacheItem = new CacheItem<T>(key, item);
        if(cachedItems==null){
            cachedItems=new ArrayList<>();
        }
        cachedItems.add(cacheItem);
    }

    public <T> T get(String key, Class<T> clazz) {
        return  clazz.cast(cachedItems.stream()
                .filter(cacheItem -> cacheItem.getKey().equals(key))
                .findFirst());
    }

    public Object get(String key) {
        return cachedItems.stream()
                .filter(cacheItem -> cacheItem.getKey().equals(key))
                .findFirst();
    }
}
