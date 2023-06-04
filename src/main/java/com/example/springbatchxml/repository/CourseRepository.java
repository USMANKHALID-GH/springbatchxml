package com.example.springbatchxml.repository;

import com.example.springbatchxml.model.Course;
import com.example.springbatchxml.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course , Long> {

    @Query("select cs.students from Course  as cs where  cs.id=:id")
    List<Student> findStudentCourse(@Param("id") long id);

    @Query()
    List<Student>  findByStudentsName(String name);
}
