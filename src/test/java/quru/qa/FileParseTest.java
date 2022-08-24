package quru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Selenide;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;
import quru.qa.domain.Film;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class FileParseTest {
    ClassLoader classLoader = FileParseTest.class.getClassLoader();
    String zipName = "folder.zip";
    String csvName = "file2.csv";
    String pdfName = "file1.pdf";
    String xlsName = "Table.xls";

    private InputStream getFromArchive(String fileName) throws Exception {
        File zipFile = new File("src/test/resources/" + zipName);
        ZipFile zip = new ZipFile(zipFile);
        return zip.getInputStream(zip.getEntry(fileName));
    }

    @Test
    void csvTest() throws Exception {
        try (InputStream csvFile = getFromArchive(csvName)){
            CSVReader csvReader = new CSVReader(new InputStreamReader(csvFile, UTF_8));
            List<String[]> csv = csvReader.readAll();
            assertThat(csv).contains(
                    new String[]{"name", "surname", "age", "placeOfBirth"},
                    new String[]{"Anna", "Annina", "20", "Napoli"}
            );
        }
    }

    @Test
    void pdfTest() throws Exception {
        try (InputStream pdfFile = getFromArchive(pdfName)){
            PDF pdf = new PDF(pdfFile);
            assertThat(pdf.text).contains("Simple pdf file");
        }
    }

    @Test
    void xlsTest() throws Exception {
        try (InputStream xlsFile = getFromArchive(xlsName)) {
            XLS xls = new XLS(xlsFile);
            assertThat(
                    xls.excel.getSheetAt(0)
                            .getRow(3)
                            .getCell(4)
                            .getStringCellValue()
            ).contains("Станислав Васенков");
        }
    }

    @Test
    void jsonTest() throws Exception {
        try (InputStream jsonFile = classLoader.getResourceAsStream("theLordOfTheRings.json")) {
            Gson gson = new Gson();
            Film jsonObject = gson.fromJson(new InputStreamReader(jsonFile), Film.class);
            assertThat(jsonObject.getName()).isEqualTo("The Lord of the Rings: The Fellowship of the Ring");
            assertThat(jsonObject.getYearOfCreation()).isEqualTo(2001);
            assertThat(jsonObject.getDirectedBy()).isEqualTo("Peter Jackson");
            assertThat(jsonObject.getStarring().getMainRole()).isEqualTo("Elijah Wood");
        }
    }
}