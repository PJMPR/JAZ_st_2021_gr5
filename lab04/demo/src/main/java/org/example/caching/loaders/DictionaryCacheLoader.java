package org.example.caching.loaders;

import org.example.caching.Cache;
import org.example.caching.provider.ItemsFromFileProvider;
import org.example.model.Dictionary;

import java.util.List;

public class DictionaryCacheLoader implements CacheLoader {
    private final Cache cache = Cache.getInstance();

    @Override
    public void load() {
        ItemsFromFileProvider provider = new ItemsFromFileProvider();
        List<Dictionary> listOfItems = provider.provideListOfItems();
        String fileName = provider.fileName();

        System.out.println("");

        for (Dictionary listOfItem : listOfItems) {
            cache.add(fileName, listOfItem);
        }
    }
}
