package org.example.caching;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cache {
    private static final Cache instance = new Cache();
    private List<CacheItem> caches = new ArrayList<>();

    public static Cache getInstance() {
        return instance;
    }

    public <T> void add(String key, T item) {
        caches.add(new CacheItem(item, key));
    }

    public <T> T get(String key, Class<T> clazz) {
        return (T) caches.stream()
                .filter(cachedItem -> Objects.equals(cachedItem.getKey(), key))
                .findAny().get().getItem();
    }

    public Object get(String key) {
        return caches.stream()
                .filter(cacheItem -> key.equals(cacheItem.key))
                .map(cacheItem -> cacheItem.item)
                .collect(Collectors.toList());
    }
}