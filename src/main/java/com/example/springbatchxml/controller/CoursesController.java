package com.example.springbatchxml.controller;

import com.example.springbatchxml.model.Course;
import com.example.springbatchxml.model.Student;
import com.example.springbatchxml.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses/")
public class CoursesController {
    @Autowired
    private CoursesService service;


    @PostMapping("/")
    public Course save(@RequestBody Course course){
        return  service.save(course);
    }


    @GetMapping("/")
    public List<Course> all(){
        return service.getAll();
    }

    @GetMapping("/{id}/student")
    public List<Student> studentAll(@PathVariable String id){
        return   service.getAllSchoolByCourseId1(id);
    }



}
