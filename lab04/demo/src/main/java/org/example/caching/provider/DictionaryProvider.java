package org.example.caching.provider;

import org.example.model.Dictionary;

import java.util.List;

public interface DictionaryProvider {
    List<Dictionary> provide();
}
