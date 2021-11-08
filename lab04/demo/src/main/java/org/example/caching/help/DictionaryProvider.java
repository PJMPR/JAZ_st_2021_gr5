package org.example.caching.help;

import org.example.model.Dictionary;

import java.util.List;

public interface DictionaryProvider {
    List<Dictionary> providedList();
    public String file();
}
