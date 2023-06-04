package com.example.springbatchxml.service;

import com.alibaba.excel.EasyExcel;
import com.example.springbatchxml.model.Student;
import com.example.springbatchxml.repository.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import jxl.write.WriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    private final EntityManager entityManager;

    @Autowired
    public StudentService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }



    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }

    public List<Student> allStudent() {
       return studentRepository.findAll();
    }

// Jexcel
    public void convertEntitiesToExcel(HttpServletResponse response) throws IOException, WriteException {
        List<Student> entities=allStudent();
        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());

        WritableSheet sheet = workbook.createSheet("Entity Sheet", 0);

        sheet.addCell(new Label(0, 0, "Id"));
        sheet.addCell(new Label(1, 0, "FirstName"));
        sheet.addCell(new Label(2, 0, "SecondName"));
        sheet.addCell(new Label(3, 0, "telephone"));
        sheet.addCell(new Label(4, 0, "adresss"));


        int row = 1;
        for (Student entity : entities) {
            sheet.addCell(new Label(0, row, entity.getId().toString()));
            sheet.addCell(new Label(1, row, entity.getFirstName()));
            sheet.addCell(new Label(2, row, entity.getSecondName()));
            sheet.addCell(new Label(3, row, entity.getTelephoneNumber()));
            sheet.addCell(new Label(4, row, entity.getAddress().toString()));
            row++;
        }
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=DatabaseJxl" + currentDateTime + ".xls";
        response.setHeader(headerKey, headerValue);
        workbook.write();
        workbook.close();
    }

//error
    public void convertToExcel() {
        List<Student> entities = allStudent();

        String fileName = "entities.xlsx";
        String sheetName = "Entity Sheet";

        EasyExcel.write(fileName)
                .sheet(sheetName)
                .head(Student.class)

                .doWrite(entities);
    }



//JexcelApi
public void convertEntitiesToExcel1(HttpServletResponse response) throws Exception {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
    Root<Student> root = criteriaQuery.from(Student.class);
    criteriaQuery.select(root);

    List<Student> entities = entityManager.createQuery(criteriaQuery).getResultList();

    WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
    WritableSheet sheet = workbook.createSheet("Entity Sheet", 0);

    int row = 0;
    for (Student entity : entities) {
        sheet.addCell(new Label(0, row, entity.getId().toString()));
        sheet.addCell(new Label(1, row, entity.getSecondName()));
        sheet.addCell(new Label(2, row, entity.getTelephoneNumber()));
        sheet.addCell(new Label(3, row, entity.getAddress().toString()));
        sheet.addCell(new Label(4, row, entity.getFirstName()));
        row++;
    }

    response.setContentType("application/vnd.ms-excel");
    response.setHeader("Content-Disposition", "attachment; filename=student.xls");

    workbook.write();
    workbook.close();
}}
