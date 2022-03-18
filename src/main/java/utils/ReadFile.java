package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ReadFile {
    private ArrayList<String> listDataFile = new ArrayList<>();


    public ReadFile ReadFileToList(String Name) {
        String fileName = Name + ".txt";
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
//            String str;
            listDataFile = (ArrayList<String>) br.lines().collect(Collectors.toList());
//            Logger.logInfo(listDataFile.get(0));
//            Logger.logInfo(listDataFile.get(1));
//            Logger.logInfo(listDataFile.get(2));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return listDataFile;
        return this;
    }

    public String getStringDataFile(Integer n) {
        return listDataFile.get(n);
    }

}