package org.example.caching.loaders;

import org.example.caching.Cache;
import org.example.caching.help.ItemsProvider;

import java.util.Dictionary;
import java.util.List;

public class DictionaryCacheLoader implements CLoader{
    public final Cache cache = Cache.getInstance();

    public void load(){
        ItemsProvider provider = new ItemsProvider();
        List<Dictionary> records = provider.providedList();
        String file = provider.file();
        for (Dictionary record:
                records) {
            cache.add(file, record);
        }
        }
    }
