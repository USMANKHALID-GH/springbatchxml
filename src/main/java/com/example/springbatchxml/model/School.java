package com.example.springbatchxml.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class School {
    @Id
    private Long id;
    private String name;
    private int age=9;
    private String schoolType;

    @Embedded
    private Address address;
}
