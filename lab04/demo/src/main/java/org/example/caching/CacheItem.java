package org.example.caching;

public class CacheItem {
    private Object item;
    private String key;

    public CacheItem(String key, Object item) {
        this.key = key;
        this.item = item;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }
}
