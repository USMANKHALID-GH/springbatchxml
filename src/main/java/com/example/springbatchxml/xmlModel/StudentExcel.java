package com.example.springbatchxml.xmlModel;

import com.example.springbatchxml.model.Student;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.apache.poi.ss.util.CellUtil.createCell;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentExcel {
    private List<Student> studentList;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public StudentExcel(List<Student> listOfStudents) {
        this.studentList = listOfStudents;
        workbook = new XSSFWorkbook();
    }


    private void writeHeader() {
        sheet = workbook.createSheet("Student");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "ID", style);
        createCell(row, 1, "first Name", style);
        createCell(row, 2, "second", style);
        createCell(row, 3, "telephone No.", style);
        createCell(row, 4, "Address.", style);
    }
    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (Student record: studentList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, record.getId().toString(), style);
            createCell(row, columnCount++, record.getFirstName(), style);
            createCell(row, columnCount++, record.getSecondName(), style);
            createCell(row, columnCount++, record.getTelephoneNumber(), style);
            createCell(row, columnCount++, record.getAddress().toString(), style);
        }
    }
    public void generateExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Database" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
