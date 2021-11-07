package org.example.caching.loaders;

import org.example.caching.Cache;
import org.example.model.Dictionary;

import java.util.List;

public class DictionaryCacheLoader  {
    public final Cache cache = Cache.getInstance();

    public void load(){
        DictionaryCacheProvider provider = new DictionaryCacheProvider();
        List<List<String>> datas = provider.DBProvider();
        String key = provider.getFileName();

        for (List<String> data : datas) {
            Dictionary dictionary = new Dictionary(Integer.parseInt(data.get(0)), Integer.parseInt(data.get(1)), data.get(2), data.get(3), data.get(4));
            cache.add(key, dictionary);
        }
    }
}
