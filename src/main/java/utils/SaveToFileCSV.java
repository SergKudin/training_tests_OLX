package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class SaveToFileCSV {

    private Object Exception;


    public SaveToFileCSV() throws IOException {
    }

    public Object saveDateToFile(ArrayList<ResultSearch> ListResultSearch, String nameFile) {
        try {
            FileOutputStream writer = new FileOutputStream(nameFile+".csv");
            int i = 1;
            String h = "№пп" + ";"
                    + "Название" + ";"
                    + "Стоимость"+";"
                    + "Ссылка" + System.getProperty("line.separator");
            writer.write(h.getBytes("Cp1251"));
            for (ResultSearch news : ListResultSearch) {
                String s = i++ + ";"
                        + news.getNameLot() + ";"
                        + news.getPrice() + ";"
                        + news.getLink() + System.getProperty("line.separator");
                writer.write(s.getBytes("Cp1251"));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            Exception=e;
        }
        return Exception;
    }

}



