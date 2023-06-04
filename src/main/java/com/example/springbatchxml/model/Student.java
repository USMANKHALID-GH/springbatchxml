package com.example.springbatchxml.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data

@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    private Long id;
    private String firstName;
    private String secondName;
    private String telephoneNumber;
    @Embedded
    private Address address;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Course> course;
}
