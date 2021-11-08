package org.example.caching.provider;

import org.example.model.Dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileProvider implements DictionaryCacheProvider {

    @Override
    public List<Dictionary> provideItems() {
        return readFile();
    }

    private static List<Dictionary> readFile() {
        String filePath = "src/main/java/org/example/data/dictionaries.csv";
        List<Dictionary> elementsInDictionary = new ArrayList<>();
        Path pathToFile = Paths.get(filePath);

        try (BufferedReader br = Files.newBufferedReader(pathToFile)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                Dictionary item = createItem(attributes);
                elementsInDictionary.add(item);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return elementsInDictionary;
    }

    public String getFileName() {
        File file = new File("src/main/java/org/example/data/dictionaries.csv");
        return file.getName().replaceFirst("[.][^.]+$", "");
    }

    private static Dictionary createItem(String[] element) {
        return new Dictionary(Integer.parseInt(element[0]), Integer.parseInt(element[1]), element[2], element[3], element[4]);
    }
}