package org.example.caching;


public class CachedItem<T> {

    T item;
    String key;

    public CachedItem(String key, T item) {
        this.key = key;
        this.item = item;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


}
