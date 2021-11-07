package org.example.caching;


public class CachedItem<T> {

    private T item;
    private String key;

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

    public T getItem(){
        return item;
    }
}
