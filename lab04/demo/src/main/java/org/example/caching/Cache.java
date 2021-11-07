package org.example.caching;

import org.example.caching.provider.CacheItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cache {
    private static final Cache instance = new Cache();
    private List<CacheItem> cacheItemsList = new ArrayList<>();

    public static Cache getInstance() {
        return instance;
    }

    public <T> void add(String key, T item) {
        CacheItem cacheItem = new CacheItem();
        cacheItem.key = key;
        cacheItem.item = item;

        cacheItemsList.add(cacheItem);
    }

    public <T> T get(String key, Class<T> clazz) {
        return (T) cacheItemsList.stream().filter(item -> key.equals(item.key) && clazz.equals(item.item.getClass())).findAny().get().item;
    }

    public Object get(String key) {
        return cacheItemsList.stream().filter(cacheItem -> key.equals(cacheItem.key)).map(cacheItem -> cacheItem.item).collect(Collectors.toList());
    }
}
