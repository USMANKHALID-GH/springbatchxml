package com.example.springbatchxml.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String city;
    private String streetName;
    private String appartmentName;
    private int kapiNumara;
}
