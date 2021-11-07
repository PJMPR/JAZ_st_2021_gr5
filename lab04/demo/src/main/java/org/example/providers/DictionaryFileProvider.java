package org.example.providers;

import java.io.*;
import org.example.model.Dictionary;

import java.util.ArrayList;
import java.util.List;

public class DictionaryFileProvider implements DictionaryProvider {
    @Override
    public List<Dictionary> provide() {
        List<Dictionary> dictionaries = new ArrayList<>();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("dictionaries.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = null;
        while (true) {
            try {
                if ((line = bufferedReader.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] temp = line.split(",");
            Dictionary dict = new Dictionary();
            dict.setId(Integer.parseInt(temp[0]));
            dict.setIntKey(Integer.parseInt(temp[1]));
            dict.setStringKey(temp[2]);
            dict.setValue(temp[3]);
            dict.setDictionaryName(temp[4]);
            dictionaries.add(dict);
        }
        return dictionaries;
    }
}
