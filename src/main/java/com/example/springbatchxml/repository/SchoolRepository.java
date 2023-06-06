package com.example.springbatchxml.repository;

import com.example.springbatchxml.model.Address;
import com.example.springbatchxml.model.School;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

    @Transactional
    List<School> removeAllByName(String name);
    @Transactional
    long deleteAllByName(String name);


    @Transactional
    boolean existsByName(String name);
    Stream<School> readAlLBySchoolTypeNotNull();

    Optional<List<School>> findByAddressStreetName(String streeName);

    @Query(" from School  as sc where  sc.address.city like  %:city%")
    List<School> findSchoolByStreetCity(@Param("city") String city);

    @Query("from School as sc where sc.age between  :age1 and  :age2 ORDER BY  sc.age desc ")
    Optional<List<School>> findByAgeBetween(@Param("age1") int firstAge , @Param("age2") int secondAge);

    @Query("select sc.address from  School  as sc where sc.id=?1")
    Address findAddressBySchoolId(long id);
}
