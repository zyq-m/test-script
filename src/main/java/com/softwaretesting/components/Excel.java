package com.softwaretesting.components;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;

public class Excel {
    static Driver d = new Driver();

    public Excel() {
        d.launch("http://localhost:3001/");

        d.findByXpath("//*[@id=\"__next\"]/div/div[2]/div/form/div[1]/input[1]").sendKeys("admin@gmail.com");
        d.findByXpath("//*[@id=\"__next\"]/div/div[2]/div/form/div[1]/input[2]").sendKeys("adminekupon");
        d.clickButtonByXpath("//*[@id=\"__next\"]/div/div[2]/div/form/div[2]/button");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void run() throws IOException {
        File f = new File("C:\\Users\\HAZIQ\\Documents\\Book1.xlsx");
        FileInputStream fis = new FileInputStream(f);
        try (XSSFWorkbook excelWorkbook = new XSSFWorkbook(fis)) {
            XSSFSheet excelSheet = excelWorkbook.getSheetAt(0);
            int rows = excelSheet.getPhysicalNumberOfRows();// 3
            int cols = excelSheet.getRow(0).getPhysicalNumberOfCells();// 2
            String data[][] = new String[rows][cols];
            XSSFCell cell;
            d.navigate("http://localhost:3001/addStudent");
            for (int i = 0; i < rows; i++) {
                ArrayList<String> arr = new ArrayList<String>();
                try {
                    for (int j = 0; j < cols; j++) {
                        cell = excelSheet.getRow(i).getCell(j);
                        String cellContents = cell.getStringCellValue();
                        data[i][j] = cellContents;
                        arr.add(cellContents);
                    }

                    d.findByXpath("//*[@id=\"__next\"]/div/div/form/div/input[1]").sendKeys(arr.get(0));
                    d.findByXpath("//*[@id=\"__next\"]/div/div/form/div/input[2]").sendKeys(arr.get(1));
                    d.findByXpath("//*[@id=\"__next\"]/div/div/form/div/input[3]").sendKeys(arr.get(2));

                    d.clickButtonByXpath("//*[@id=\"__next\"]/div/div/form/button");

                    System.out.println(i + 1 + " done");

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } catch (UnhandledAlertException e) {
                    try {
                        d.acceptAlert();
                    } catch (NoAlertPresentException g) {
                        i = i - 1;
                        g.printStackTrace();
                    }
                }
                arr.clear();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        fis.close();
        // d.quit();
    }
}
