package org.example.caching.loaders;

import org.example.caching.Cache;
import org.example.providers.DictionaryFileProvider;
import org.example.providers.DictionaryProvider;

public class DictionaryCacheLoader implements CacheLoader{
    public Cache cache;
    public DictionaryProvider provider = new DictionaryFileProvider();

    @Override
    public void load(){
        cache = Cache.getInstance();
        cache.add("dictionaries", provider.provide());
    }
}
