package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pojo.Student;
@Repository
public interface studentRepository extends JpaRepository<Student, Long> {

}
