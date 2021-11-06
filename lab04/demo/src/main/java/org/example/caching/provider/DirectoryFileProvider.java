package org.example.caching.provider;

import org.example.model.Dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DirectoryFileProvider implements DictionaryProvider {

    @Override
    public List<Dictionary> provide() {
        return readItemsFromCSV();
    }

    private static List<Dictionary> readItemsFromCSV() {
        List<Dictionary> dictionaryList = new ArrayList<>();
        Path pathToFile = Paths.get("src/main/java/org/example/caching/input/dictionaries.csv");

        try (BufferedReader br = Files.newBufferedReader(pathToFile)) {
            String line = br.readLine();

            while (line != null) {
                String[] attributes = line.split(",");
                Dictionary item = createItem(attributes);

                dictionaryList.add(item);

                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return dictionaryList;
    }

    private static Dictionary createItem(String[] attribute) {
        int id = Integer.parseInt(attribute[0]);
        int intKey = Integer.parseInt(attribute[1]);
        String stringKey = attribute[2];
        String value = attribute[3];
        String dictionaryName = attribute[4];

        return new Dictionary(id, intKey, stringKey, value, dictionaryName);
    }
}
