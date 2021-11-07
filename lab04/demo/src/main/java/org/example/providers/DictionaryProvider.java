package org.example.providers;

import org.example.model.Dictionary;

import java.util.List;

public interface DictionaryProvider {
    List<Dictionary> provide();
}
