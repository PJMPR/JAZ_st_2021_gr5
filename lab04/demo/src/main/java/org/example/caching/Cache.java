package org.example.caching;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Cache {
    public List<CacheItem> cacheItems = new ArrayList<>();
    public static Cache cacheInstance = new Cache();

    public static Cache getInstance(){
        return cacheInstance;
    }


    public <T> void add(String key, T item){
        CacheItem cacheItem = new CacheItem();
        cacheItem.key = key;
        cacheItem.item = item;
        cacheItems.add(cacheItem);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> clazz){
        return (T) Objects.requireNonNull(cacheItems.stream().filter(cacheItem -> key.equals(cacheItem.key)
                && clazz.equals(cacheItem.item.getClass())).findAny().get().item);
    }

    public Object get(String key){
        return cacheItems.stream().filter(cacheItem -> key.equals(cacheItem.key)).map(cacheItem -> cacheItem.item).collect(Collectors.toList());
    }
}
