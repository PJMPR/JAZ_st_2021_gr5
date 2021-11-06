package org.example.caching;

import org.example.model.CacheItem;
import org.example.model.Dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cache {
    private static final Cache instance = new Cache();
    private List<CacheItem> cacheItems = new ArrayList<>();

    public static Cache getInstance(){
        return instance;
    }

    public <T> void add(String key, T item){
        CacheItem cacheItem = new CacheItem();
        cacheItem.key = key;
        cacheItem.item = item;
        cacheItems.add(cacheItem);
    }

    public <T> T get(String key, Class<T> clazz){
        return (T) cacheItems.stream().filter(item -> key.equals(item.key) && clazz.equals(item.item.getClass())).findAny().get().item;
    }

    public Object get(String key){
        return cacheItems.stream().filter(cacheItem -> key.equals(cacheItem.key)).map(cacheItem -> cacheItem.item).collect(Collectors.toList());
    }
}