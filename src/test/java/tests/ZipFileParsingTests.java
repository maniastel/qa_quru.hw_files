package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFileParsingTests {

    private ClassLoader cl = ZipFileParsingTests.class.getClassLoader();

    @Test
    @DisplayName("Проверка содержимого pdf файла")
    void pdfFileParsingTest() throws Exception {
        try (ZipInputStream zip = new ZipInputStream(
                cl.getResourceAsStream("sample.zip")
        )) {
            ZipEntry entry;

            while ((entry = zip.getNextEntry()) != null) {
                if (entry.getName().equals("PDF_Converter_Pro_Quick_Reference_Guide.RU.pdf")) {
                    PDF pdf = new PDF(zip);
                    Assertions.assertEquals("Краткое справочное руководство", pdf.title);
                }
            }
        }
    }

    @Test
    @DisplayName("Проверка содержимого xls файла")
    void xlsFileParsingTest() throws Exception {
        try (ZipInputStream zip = new ZipInputStream(
                cl.getResourceAsStream("sample.zip")
        )) {
            ZipEntry entry;

            while ((entry = zip.getNextEntry()) != null) {
                if (entry.getName().equals("file_example_XLS_50.xls")) {
                    XLS xls = new XLS(zip);
                    String actualResult = xls.excel.getSheetAt(0).getRow(1).getCell(4).getStringCellValue();
                    Assertions.assertTrue(actualResult.contains("United State"));
                }
            }
        }
    }

    @Test
    @DisplayName("Проверка содержимого csv файла")
    void csvFileParsingTest() throws Exception {
        try (ZipInputStream zip = new ZipInputStream(
                cl.getResourceAsStream("sample.zip")
        )) {
            ZipEntry entry;

            while ((entry = zip.getNextEntry()) != null) {
                if (entry.getName().equals("annual-enterprise-survey-2021-financial-year-provisional-csv.csv")) {
                    CSVReader csv = new CSVReader(new InputStreamReader(zip));
                    List<String[]> data = csv.readAll();
                    Assertions.assertEquals(41716, data.size());
                }
            }
        }
    }

}
