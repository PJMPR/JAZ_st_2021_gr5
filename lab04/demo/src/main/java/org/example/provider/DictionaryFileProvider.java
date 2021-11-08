package org.example.provider;

import org.example.model.Dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DictionaryFileProvider implements DictionaryProvider {
    @Override
    public List<Dictionary> provide() {
        String filepath = "src/main/resources/dictionaries.csv";
        List<Dictionary> dictionaries = new ArrayList<>();
        Path path = Paths.get(filepath);


        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line = br.readLine();

            while (line != null) {
                String[] attributes = line.split(",");

                int id = Integer.parseInt(attributes[0]);
                int intKey = Integer.parseInt(attributes[1]);
                String stringKey = attributes[2];
                String value = attributes[3];
                String dictionaryName = attributes[4];

                Dictionary item = new Dictionary(id, intKey, stringKey, value, dictionaryName);

                dictionaries.add(item);

                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return dictionaries;
    }

    public String fileName() {
        File f = new File("/dictionaries.csv");
        return f.getName().replaceFirst("[.][^.]+$", "");

    }
}

