package org.example.caching;

public class CacheItem {
    public Object item;
    public String key;

    public CacheItem(Object item, String key) {
        this.item = item;
        this.key = key;
    }

    public Object getItem() {
        return item;
    }

    public String getKey() {
        return key;
    }
}