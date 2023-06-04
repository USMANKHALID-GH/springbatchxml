package com.example.springbatchxml.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    private Long id;
    private String name;
    @ManyToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_course",
           joinColumns=@JoinColumn( name = "course_Id"),
            inverseJoinColumns=@JoinColumn(name = "student_id")
    )
    private List<Student> students;
}
