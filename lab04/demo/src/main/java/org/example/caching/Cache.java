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
        CacheItem cacheItem = new CacheItem(key, item);
        if(cachedItems==null){
            cachedItems=new ArrayList<>();
        }
        cachedItems.add(cacheItem);
    }

    public <T> T get(String key, Class<T> clazz) {
        return (T) cachedItems.stream()
                .filter(cacheItem -> key.equals(cacheItem.key))
                .findFirst().get().item;
    }

    public Object get(String key) {
        return cachedItems.stream()
                .filter(cacheItem -> cacheItem.getKey().equals(key))
                .findFirst();
    }
}
