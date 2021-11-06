package org.example.caching.loaders;

import org.example.caching.Cache;
import org.example.model.Dictionary;

import java.util.List;

public class DictionaryCacheLoader {
    private final Cache cache = Cache.getInstance();

    public void load(){
        DictionaryCacheProvider provider = new DictionaryCacheProvider();
        List<List<String>> records = provider.getRecords();
        String key = provider.getFileName();

        for (List<String> record:
             records) {
            Dictionary dictionary = new Dictionary(Integer.parseInt(record.get(0)), Integer.parseInt(record.get(1)), record.get(2), record.get(3), record.get(4));
            cache.add(key, dictionary);
        }
    }
}