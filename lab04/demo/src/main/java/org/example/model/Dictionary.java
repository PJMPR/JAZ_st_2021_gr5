package org.example.model;

public class Dictionary {

    private int id;
    private int intKey;
    private String stringKey;
    private String value;
    private String dictionaryName;

    public Dictionary() {
    }

    public Dictionary(int id, int intKey, String stringKey, String value, String dictionaryName) {
        this.id = id;
        this.intKey = intKey;
        this.stringKey = stringKey;
        this.value = value;
        this.dictionaryName = dictionaryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIntKey() {
        return intKey;
    }

    public void setIntKey(int intKey) {
        this.intKey = intKey;
    }

    public String getStringKey() {
        return stringKey;
    }

    public void setStringKey(String stringKey) {
        this.stringKey = stringKey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName;
    }
}
