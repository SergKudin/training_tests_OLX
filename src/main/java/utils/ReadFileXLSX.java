package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class ReadFileXLSX {

    private String FileName;

    public ReadFileXLSX(String FileName) throws IOException {
        this.FileName = FileName;
    }


    private ArrayList<ResultSearch> listDataFile = new ArrayList<>();

    public ReadFileXLSX setFileName(String fileName) {
        FileName = fileName;
        return this;
    }

    public ReadFileXLSX readToList() throws IOException {
        // Read XLSX file
        FileInputStream inputStream = new FileInputStream(new File(FileName + ".xlsx"));
        // Get the workbook instance for XLSX file
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        // Get first sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);
        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = sheet.iterator();
        String NameLot = null;
        Integer Price = null;
        String Link = null;
        String temp = null;

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // Get iterator to all cells of current row
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                // Change to getCellType() if using POI 4.x
                CellType cellType = cell.getCellType();

                switch (cellType) {
                    case _NONE:
                        System.out.print("");
                        System.out.print("\t");
                        break;
                    case BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        System.out.print("\t");
                        break;
                    case BLANK:
                        System.out.print("");
                        System.out.print("\t");
                        break;
                    case FORMULA:
                        // Formula
                        System.out.print(cell.getCellFormula());
                        System.out.print("\t");

                        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                        // Print out value evaluated by formula
                        System.out.print(evaluator.evaluate(cell).getNumberValue());
                        break;
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue());
                        System.out.print("\t");
                        Price = (int)cell.getNumericCellValue();
                        break;
                    case STRING:
                        System.out.print(cell.getStringCellValue());
                        System.out.print("\t");
                        temp = cell.getStringCellValue();
                        break;
                    case ERROR:
                        System.out.print("!ERROR");
                        System.out.print("\t");
                        break;
                }
                switch (cell.getColumnIndex()) {
                    case 1:
                        NameLot = temp;
                        break;
                    case 3:
                        Link = temp;
                        break;
                }
            }
            System.out.println("");
            listDataFile.add(new ResultSearch(NameLot, Price, Link));
        }
        System.out.println("Read xlsx file to listDataFile.size = "+listDataFile.size());
        return this;
    }
}
