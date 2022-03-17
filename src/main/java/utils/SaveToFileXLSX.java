package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class SaveToFileXLSX {

    private Object Exception;

    public SaveToFileXLSX() throws IOException {
    }

    private static XSSFCellStyle createStyleForTitle(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    public void saveDateToFileXLSX(ArrayList<ResultSearch> ListResultSearch, String nameFile) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("OLX");

        int rownum = 0;
        Cell cell;
        Row row;
        //
        XSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);

        // №пп
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("№пп");
        cell.setCellStyle(style);
        // Название
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Название");
        cell.setCellStyle(style);
        // Стоимость
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Стоимость");
        cell.setCellStyle(style);
        // Ссылка
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Ссылка");
        cell.setCellStyle(style);

        // Data
        for (ResultSearch news : ListResultSearch) {
            rownum++;
            row = sheet.createRow(rownum);

            // №пп (A)
            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue(rownum);
            // Название (B)
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(news.getNameLot());
            // Стоимость (C)
            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(news.getPrice());
            // Ссылка (D)
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(news.getLink());
        }
        File file = new File(nameFile+".xlsx");
//        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        Logger.logInfo("Created file: " + file.getAbsolutePath());
//        System.out.println("Created file: " + file.getAbsolutePath());

    }

}
