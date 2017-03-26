package com.test.calculate;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author zhouj
 * @since 16/4/30
 */
public class TestPoi {

    public static void main(String[] args) throws Throwable {
//        SXSSFWorkbook wb = new SXSSFWorkbook(100); // keep 100 rows in memory, exceeding rows will be flushed to disk
//        Sheet sh = wb.createSheet();
//        for(int rownum = 0; rownum < 1000; rownum++){
//            Row row = sh.createRow(rownum);
//            for(int cellnum = 0; cellnum < 10; cellnum++){
//                Cell cell = row.createCell(cellnum);
//                String address = new CellReference(cell).formatAsString();
//                cell.setCellValue(address);
//            }
//
//        }
//
//        // Rows with rownum < 900 are flushed and not accessible
//        for(int rownum = 0; rownum < 900; rownum++){
////            Assert.assertNull(sh.getRow(rownum));
//        }
//
//        // ther last 100 rows are still in memory
//        for(int rownum = 900; rownum < 1000; rownum++){
////            Assert.assertNotNull(sh.getRow(rownum));
//        }
//        File file = new File("./test.xlsx");
//        FileOutputStream out = new FileOutputStream(file.getName());
//        wb.write(out);
//        out.close();
//
//        // dispose of temporary files backing this workbook on disk
//        wb.dispose();


        File file = new File("./test.xlsx");
        FileInputStream inputStream = new FileInputStream(file.getName());
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        POIFSFileSystem fileSystem = new POIFSFileSystem(inputStream);
        Sheet sheet = xssfWorkbook.getSheetAt(0);
        System.out.println(sheet.getLastRowNum());
        System.out.println(xssfWorkbook.getNumberOfSheets());
        inputStream.close();
    }
}
