package org.example.caching.loaders;
import org.example.caching.Cache;
import org.example.caching.providers.DictionaryFileProvider;
import org.example.model.Dictionary;
import java.util.ArrayList;
import java.util.List;

public class DictionaryCacheLoader implements CacheLoader{

    //private Cache cache = new Cache();
    private DictionaryFileProvider provider = new DictionaryFileProvider();
    private String fileName = "src/main/java/org/example/caching/loaders/dictionaries.csv";
    private List<Dictionary> dictionariesList = new ArrayList<>();

    @Override
    public void load(){
        dictionariesList = provider.provide(fileName);
        dictionariesList.stream().forEach(dictionary -> Cache.getInstance().add("dictionaries", dictionary));
    }
}
