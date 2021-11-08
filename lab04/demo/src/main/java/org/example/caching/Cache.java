package org.example.caching;

import java.util.ArrayList;
import java.util.List;

public class Cache {

    // Cache must be Singleton
    private static final Cache cacheInstance = new Cache();
    private List<CacheItem> cacheItems = new ArrayList<>();

    public static Cache getInstance(){
        return cacheInstance;
    }

    public <T> void add(String key, T item){
        cacheItems.add(new CacheItem(key, item));
    }

    public <T> T get(String key, Class<T> clazz){

        // First we need to find CacheItem that meets requirements
        return (T) cacheItems.stream()
                .filter(item -> key.equals(item.getKey())
                        && clazz.equals(item.getItem().getClass()))
        // Then we have to get this item.
                .findAny().get().getItem();
    }

    public Object get(String key){

        // Same as get(String key, Class<T> clazz) but without specifying class
        return cacheItems.stream()
                .filter(cacheItem -> cacheItem.getKey().equals(key))
                .findAny().get().getItem();
    }
}
