package org.example.caching;

import org.example.caching.help.CacheElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cache {
    private static final Cache cache = new Cache();
    private List<CacheElement> cacheItems = new ArrayList<>();


    public static Cache getInstance(){
        return cache;
    }


    public <T> void add(String key, T item){
        CacheElement cache = new CacheElement();
        cache.key = key;
        cache.item = item;
        cacheItems.add(cache);
    }

    public <T> T get(String key, Class<T> clazz){
        return (T) cacheItems.stream()
                .filter(item -> key.equals(item.key) && clazz.equals(item.item.getClass()))
                .findAny().get().item;
    }

    public Object get(String key){
        return cacheItems.stream()
                .filter(cacheItem -> key.equals(cacheItem.key))
                .map(cacheItem -> cacheItem.item)
                .collect(Collectors.toList());
    }
}
