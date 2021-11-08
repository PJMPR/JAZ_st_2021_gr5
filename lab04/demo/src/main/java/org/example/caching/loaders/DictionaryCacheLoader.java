package org.example.caching.loaders;

import org.example.caching.Cache;
import org.example.caching.providers.DictionaryProviders.DictionaryFileProvider;
import org.example.caching.providers.DictionaryProviders.DictionaryProvider;
import java.io.File;

public class DictionaryCacheLoader implements CacheLoader {

    private Cache cache = Cache.getInstance();
    private DictionaryProvider fileProvider = new DictionaryFileProvider();

    public void load() {
        // I store all csv files in folder named resources
        File folder = new File("src/main/resources");
        String[] filesToLoad = folder.list();

        // fileProvider gives us Dictionaries List that we can now put in our cache
        cache.add("dictionaries", fileProvider.provide(filesToLoad));
    }
}
