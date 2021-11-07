package org.example.caching.loaders;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DictionaryCacheProvider {
    File file = new File("../dictionaries.csv");

    public List<List<String>> DBProvider(){
        List<List<String>> records = new ArrayList<>();
        {
            try {
                Scanner scan = new Scanner(file);
                while (scan.hasNextLine()) {
                    records.add(getDataFromLine(scan.nextLine()));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return records;
    }

    public List<String> getDataFromLine(String line) {
        List<String> data = new ArrayList<>();
        try (Scanner lineScanner = new Scanner(line)) {
            lineScanner.useDelimiter(",");
            while(lineScanner.hasNextLine()) {
                data.add(lineScanner.next());
            }
        }
        return data;
    }

    public String getFileName() {
        return file.getName().replaceFirst("[.][^.]+$", "");
    }
}
