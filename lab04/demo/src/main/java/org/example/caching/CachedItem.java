package org.example.caching;

import java.util.ArrayList;

public class CachedItem <T> implements CachedItems{
    public CachedItem(T item){
        ArrayList<T> items = new ArrayList<T>(item);
    }
}
