package com.example.Crud_using_Postgress.Respository;

import com.example.Crud_using_Postgress.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


public interface StudentRespository extends JpaRepository<Student,Long> {

}
