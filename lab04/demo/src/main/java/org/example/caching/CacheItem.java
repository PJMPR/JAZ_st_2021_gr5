package org.example.caching;

public class CacheItem <T> {
    public String key;
    public T item;


    public CacheItem (String key, T item) {
        this.key = key;
        this.item = item;
    }
    public CacheItem () {
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

    public void setItem(T item) {
        this.item = item;
    }
}
