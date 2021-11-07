package org.example.caching.providers;
import org.example.model.Dictionary;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DictionaryFileProvider implements DictionaryProvider {

    private String line = "";
    private String splitBy = ",";
    private List<Dictionary> dictionaryList = new ArrayList<>();

    @Override
    public List<Dictionary> provide(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);
                int id = Integer.parseInt(data[0]);
                int intKey = Integer.parseInt(data[1]);
                Dictionary dict = new Dictionary(id, intKey, data[2], data[3], data[4]);
                dictionaryList.add(dict);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dictionaryList;
    }
}
