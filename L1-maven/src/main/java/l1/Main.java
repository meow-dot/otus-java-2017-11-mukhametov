package l1;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("Single sheet");

        sheet.createRow(0).createCell(0).setCellValue("Hello,");
        sheet.createRow(1).createCell(0).setCellValue("Otus!");

        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream("Message.xls");
        } catch (FileNotFoundException e) {
            System.out.println("Что-то пошло не так");
        }

        try {
            wb.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            System.out.println("Что-то пошло не так");
        }
    }
}