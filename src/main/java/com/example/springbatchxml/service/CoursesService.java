package com.example.springbatchxml.service;

import com.example.springbatchxml.model.Course;
import com.example.springbatchxml.model.Student;
import com.example.springbatchxml.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesService {
    @Autowired
    private CourseRepository courseRepository;

    public Course save(Course course){
        return courseRepository.save(course);
    }


    public  void saveStudentAndCourse(){

    }

    public List<Course> getAll(){
        return  courseRepository.findAll();
    }


    public List<Student> getAllSchoolByCourseId(long id){
        return  courseRepository.findStudentCourse(id);
    }

    public List<Student> getAllSchoolByCourseId1(String id){
        return  courseRepository. findByStudentsName(id);
    }
}
