package org.example.caching.providers.DictionaryProviders;

import org.example.model.Dictionary;

import java.util.List;

public interface DictionaryProvider {

    public List<Dictionary> provide(String[] args);
}
