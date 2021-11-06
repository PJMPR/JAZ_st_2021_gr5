package org.example.caching;

import java.util.ArrayList;
import java.util.List;

public class Cache {
    private static Cache cache;
    List<CachedItems> cachedItems = new ArrayList<>();

    public static Cache getInstance(){
        if (cache != null)
            cache = new Cache();

        return cache;
    }

    public <T> void add(T item){
        
    }


    public <T> void add(String key, T item){

    }

    public <T> T get(String key, Class<T> clazz){
        return (T) clazz.cast(new Object());
    }

    public Object get(String key){
        return null;
    }
}
