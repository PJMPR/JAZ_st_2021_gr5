package org.example.caching.providers;

import org.example.model.Dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DictionaryFileProvider implements DictionaryProvider {

    public ArrayList<Dictionary> provide() {
        return readDataFromFile();
    }

    private ArrayList<Dictionary> readDataFromFile() {
        ArrayList<Dictionary> elements = new ArrayList<>();
        Path pathToFile = Paths.get("dictionaries.csv");

        try (BufferedReader br = Files.newBufferedReader(pathToFile)) {
            String line = br.readLine();

            while (line != null) {
                String[] attributes = line.split(",");
                Dictionary item = createDictionaryItem(attributes);

                elements.add(item);

                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return elements;
    }

    private Dictionary createDictionaryItem(String[] data) {
        return new Dictionary(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2], data[3], data[4]);
    }
}
