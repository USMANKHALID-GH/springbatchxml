package com.example.springbatchxml.controller;

import com.example.springbatchxml.model.Address;
import com.example.springbatchxml.model.School;

import com.example.springbatchxml.service.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/school")
public class SchoolController {
    @Autowired
    private SchoolService service;


    @PostMapping("/")
    public School save(@RequestBody School school){
        return  service.save(school);
    }


    @DeleteMapping("/{name}")
    public   List<School> removeByName(@PathVariable("name")  String name){
        log.info(name+"--------------------------");
        return  service.remove(name);

    }


    @GetMapping("/")
    public List<School> all(){
       return service.findAll();
    }

    @GetMapping("/1")
    public List<School> all1(){
        return service.findAll1();
    }

    @GetMapping("/{street-name}/streetName")
    public List<School> findBystreetName(@PathVariable("street-name") String streetName){
        return service.findSchoolByStreetName(streetName);
    }

    @GetMapping("/{city}/city")
    public List<School> findSchoolByStreetCity(@PathVariable("city") String streetName){
        return service.findSchoolByStreetCity(streetName);
    }


    @GetMapping("/{age1}/age1/{age2}/age2")
    public List<School> ffindByAgeBetween(@PathVariable("age1") int age1, @PathVariable("age2") int age2){
        return service.ffindByAgeBetween(age1,age2);
    }
    @GetMapping("/{id}/address")
    public Address findAddressBySchoolId(@PathVariable("id") int id){
        return service.findAddressBySchoolId(id);
    }
}

