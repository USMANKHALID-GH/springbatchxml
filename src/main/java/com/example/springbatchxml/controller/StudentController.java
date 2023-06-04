package com.example.springbatchxml.controller;

import com.example.springbatchxml.model.Student;
import com.example.springbatchxml.service.StudentService;
import com.example.springbatchxml.xmlModel.StudentExcel;
import com.example.springbatchxml.xmlModel.XmlClasses;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.thoughtworks.xstream.XStream;
import jakarta.servlet.http.HttpServletResponse;
import jxl.write.WriteException;
import org.apache.commons.io.IOUtils;
import org.simpleframework.xml.core.Persister;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.simpleframework.xml.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class StudentController {
    @Autowired
    private StudentService service;

    @PostMapping("/")
    public Student save(@RequestBody Student student){
        return  service.saveStudent(student);
    }

    @GetMapping("/")
    public List<Student> studentAll(){
      return   service.allStudent();
    }

//    JAXB
    @GetMapping("/database-to-xml")
    public ResponseEntity<String> convertDatabaseToXml() {
        List<Student> student = service.allStudent();
        XmlClasses wrapper = new XmlClasses();
        wrapper.setStudent(student);
        wrapper.setGname("this is me");

        try {
            JAXBContext context = JAXBContext.newInstance(XmlClasses.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


            StringWriter writer = new StringWriter();
            marshaller.marshal(wrapper, writer);
            String xmlString = writer.toString();

            return ResponseEntity.ok(xmlString);
        } catch (JAXBException e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error converting to XML");
        }
    }

//XStream
    @GetMapping("/database-to-xml-sxtream")
    public ResponseEntity<String> convertDatabaseToXml1() {
        List<Student> student = service.allStudent();

        XStream xstream = new XStream();
        xstream.alias("student", List.class);
        xstream.alias("student", Student.class);
        String xmlString = xstream.toXML(student);

        return ResponseEntity.ok(xmlString);
    }

//    Jackson
    @GetMapping("/database-to-xml-jackson")
    public ResponseEntity<String> convertDatabaseToXml2() {
        List<Student> student = service.allStudent();

        XmlMapper xmlMapper = new XmlMapper();
        try {
            String xmlString = xmlMapper.writeValueAsString(student);
            return ResponseEntity.ok(xmlString);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error converting to XML");
        }
    }
// simpleXml
    @GetMapping("/database-to-xml-simplexml")
    public ResponseEntity<String> convertDatabaseToXml3() {
        List<Student> books = service.allStudent();

        XmlClasses wrapper = new XmlClasses();
        wrapper.setStudent(books);

        Serializer serializer = new Persister();
        try {
            StringWriter writer = new StringWriter();
            serializer.write(wrapper, writer);
            String xmlString = writer.toString();
            return ResponseEntity.ok(xmlString);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error converting to XML");
        }
    }

    @GetMapping("/export-to-excel")
    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {


        List <Student> listOfStudents = service.allStudent();
        StudentExcel generator = new StudentExcel(listOfStudents);
        generator.generateExcelFile(response);
    }

    @GetMapping("/convert-to-excel")
    public void convertToExcel(HttpServletResponse response) throws WriteException, IOException {
        service.convertEntitiesToExcel(response);

    }



    @GetMapping("/convert-to-excel1")
    public String convertToExcelEasyExcel() {
        service.convertToExcel();
        return "Conversion successful!";
    }


    @GetMapping("/convert-to-excel2")
    public void convertToExcel2(HttpServletResponse response) throws Exception {
        service.convertEntitiesToExcel1(response);

    }





}
