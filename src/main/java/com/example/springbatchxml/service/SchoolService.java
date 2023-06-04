package com.example.springbatchxml.service;

import com.example.springbatchxml.model.Address;
import com.example.springbatchxml.model.School;
import com.example.springbatchxml.repository.SchoolRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;


    public  School save(School school){
        return  schoolRepository.save(school);
    }

    public   List<School>  remove(String name){
        log.info(name+"--------------------------"+schoolRepository.deleteAllByName(name));
        return schoolRepository.removeAllByName(name);
    }

    public List<School> findAll() {
       return schoolRepository.findAll();
    }
    @Transactional
    public List<School> findAll1() {
        return schoolRepository.readAlLBySchoolTypeNotNull().collect(Collectors.toList());
    }

    public  List<School> findSchoolByStreetName(String streetName){
      return   schoolRepository.findByAddressStreetName(streetName).get();

    }

    public  List<School> findSchoolByStreetCity(String city){
        return   schoolRepository.findSchoolByStreetCity(city);

    }

    public  List<School> ffindByAgeBetween(int age1, int age2){
        return  schoolRepository.findByAgeBetween(age1,age2).get();
    }


    public Address findAddressBySchoolId(long id){
        return  schoolRepository.findAddressBySchoolId(id);
    }
}
