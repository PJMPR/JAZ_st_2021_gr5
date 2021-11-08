
package org.example.caching.loaders;

import org.example.caching.Cache;
import org.example.caching.provider.FileProvider;
import org.example.model.Dictionary;

import java.util.List;

public class DictionaryCacheLoader implements CacheLoader {
   Cache cache = Cache.getInstance();

    @Override
    public void load() {
        FileProvider provider = new FileProvider();
        List<Dictionary> ItemList = provider.provideItems();

        for (Dictionary item : ItemList) {
            cache.add(provider.getFileName(), item);
        }
    }
}