package org.example.tests;

import org.example.caching.Cache;
import org.example.caching.loaders.DictionaryCacheLoader;
import org.example.model.Dictionary;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestDictionaryCacheLoader {

    @Test
    public void test_should_check_if_loading_cache_from_file_works_properly(){
        new DictionaryCacheLoader().load();
        List<Dictionary> dictionaries = (List<Dictionary>) Cache.getInstance().get("dictionaries");
        assertThat(dictionaries, hasSize(10));
        List<String> dictionaryNames = dictionaries.stream().map(x->x.getDictionaryName()).distinct().toList();
        assertThat(dictionaryNames, hasItems("Region","City", "AddressType", "UserType"));
    }
}
