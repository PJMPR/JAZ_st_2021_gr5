package org.example.caching.providers.DictionaryProviders;

import org.example.model.Dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DictionaryFileProvider implements DictionaryProvider {

    private List<Dictionary> dictionaries = new ArrayList<Dictionary>();

    public List<Dictionary> provide(String[] args) {

        // For every file in our resources folder, we read data from and put it in dictionaries List
        Arrays.stream(args).collect(Collectors.toList())
                .forEach(fileName->loadDataFromCSV("src/main/resources/" + fileName));

        return dictionaries;
    }

    private void loadDataFromCSV(String filePath) {
        String line = "";
        String dataSeparator = ",";

        // Putting data from cvg file to dictionary, and adding this dictionary to dictionaries List
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            while ((line = bufferedReader.readLine()) != null) {
                String[] dictionaryData = line.split(dataSeparator);

                int id = Integer.parseInt(dictionaryData[0]);
                int intKey = Integer.parseInt(dictionaryData[1]);

                Dictionary dictionary = new Dictionary(id,
                        intKey, dictionaryData[2], dictionaryData[3], dictionaryData[4]);

                dictionaries.add(dictionary);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}


