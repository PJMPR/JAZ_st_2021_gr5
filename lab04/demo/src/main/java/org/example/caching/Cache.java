package org.example.caching;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cache {

    private List<CacheItem> items = new ArrayList<>();
    private static volatile Cache instance = null;

    private Cache() {
        if (instance != null) {
            throw new RuntimeException("Not allowed. Please use getInstance() method.");
        }
    }

    public static Cache getInstance(){
        if (instance == null) {
            synchronized (Cache.class) {
                if (instance == null) {
                    instance = new Cache();
                }
            }
        }
        return instance;
    }


    public <T> void add(String key, T item){
        CacheItem cacheItem = new CacheItem();
        cacheItem.item = item;
        cacheItem.key = key;
        items.add(cacheItem);
    }

    public <T> T get(String key, Class<T> clazz){
        return (T) items.stream().filter(item -> key.equals(item.key) && clazz.equals(item.item.getClass())).findAny().get().item;
    }

    public Object get(String key){
        return items.stream().filter(cacheItem -> key.equals(cacheItem.key)).map(cacheItem -> cacheItem.item).collect(Collectors.toList());
    }
}

