package org.example.caching.loaders;

import org.example.caching.Cache;
import org.example.model.Dictionary;
import org.example.provider.DictionaryFileProvider;
import org.example.provider.DictionaryProvider;

import java.util.List;

public class DictionaryCacheLoader implements CacheLoader{

    public static Cache cache=Cache.getInstance();

    public DictionaryProvider dictionaryProvider;

    public void load() {
        DictionaryFileProvider provider = new DictionaryFileProvider();
        List<Dictionary> listOfItems = provider.provide();
        String fileName = provider.fileName();

        System.out.println("");

        for (Dictionary listOfItem : listOfItems) {
            cache.add(fileName, listOfItem);
        }
    }
}
