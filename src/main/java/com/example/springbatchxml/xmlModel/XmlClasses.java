package com.example.springbatchxml.xmlModel;

import com.example.springbatchxml.model.Student;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;


import java.util.List;


@XmlRootElement(name = "Students")
@Data
public class XmlClasses {

    private List<Student> student;
    private String Gname;


}
