package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader {

    public static HashMap<String, String> storeValues = new HashMap();
    public static XSSFWorkbook workbook;
    public static XSSFSheet sheet;
    public static FileInputStream fis;
    public static FileOutputStream fos;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static int rowcount=0;

    public static List<HashMap<String, String>> data(String filepath, String sheetName) {

        List<HashMap<String, String>> mydata = new ArrayList<>();

        try {
            FileInputStream fs = new FileInputStream(filepath);
            workbook = new XSSFWorkbook(fs);
            sheet = workbook.getSheet(sheetName);
            Row HeaderRow = sheet.getRow(0);
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row currentRow = sheet.getRow(i);
                HashMap<String, String> currentHash = new HashMap<String, String>();
                for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) {
                    Cell currentCell = currentRow.getCell(j);
                    switch (currentCell.getCellType()) {
                        case STRING:
                            currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
                            break;
                    }
                }
                mydata.add(currentHash);
            }
            fs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mydata;
    }


    public  static int getrowcount(String filepath, String filename) throws IOException {
        workbook=new XSSFWorkbook(filepath);
        sheet=workbook.getSheet(filename);
         rowcount =sheet.getLastRowNum();
        workbook.close();
        return rowcount;

    }

    public static int getcellcount(String filepath,String filename,int rowcount) throws IOException {
        workbook=new XSSFWorkbook(filepath);
        sheet= workbook.getSheet(filename);
        row= sheet.getRow(rowcount);
        int cellcount =row.getLastCellNum();
        return cellcount;

    }
    public static String getData(String filepath, String filename, int rownumber, int cellnumber) throws IOException {
        workbook=new XSSFWorkbook(filepath);
        sheet=workbook.getSheet(filename);
        row= sheet.getRow(rownumber);
        cell=row.getCell(cellnumber);
        String str = cell.toString();
        workbook.close();
        return str;
    }

    public static void SetTestdatatoExcel(String filepath, String sheetname, int rownum, int cellnum, String str1) throws IOException {
        fis=new FileInputStream(filepath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetname);
        row = sheet.getRow(rownum);
        cell = row.createCell(cellnum);
        cell.setCellValue(str1);
        fos = new FileOutputStream(filepath);
        workbook.write(fos);
        workbook.close();
        fis.close();
        fos.close();


    }
//
//    public static void validatecell(String filepath, String sheetname, int rownum, int cellnum, String str) throws IOException {
//        workbook = new XSSFWorkbook(filepath);
//        sheet = workbook.getSheet(sheetname);
//        row = sheet.getRow(rownum);
//        cell = row.getCell(cellnum);
//        fos = new FileOutputStream(filepath);
//
//        String temp = cell.getStringCellValue();
//        if (temp.equals(str)) {
//            cell.setCellValue("Passed");
//        }

//    }

}
