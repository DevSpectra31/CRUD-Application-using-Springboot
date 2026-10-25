package com.example.Crud_using_Postgress.Service;

import com.example.Crud_using_Postgress.Entity.Student;
import com.example.Crud_using_Postgress.Respository.StudentRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRespository studentRespository;

    public StudentService(StudentRespository studentRespository){
        this.studentRespository = studentRespository;
    }

    public Student createStudent(Student studentreq){

        System.out.println("student service called");

        Student studenres = studentRespository.save(studentreq);

        return studenres;
    }

    public Student getStudent(Long id){

        Optional<Student> studentres = Optional.of(studentRespository.getById(id));

        return studentres.orElse(null);
    }

    public List<Student> getallstudent() {

        List<Student> students = studentRespository.findAll();

        return students;
    }

    public Student updateStudent(Long id,Student studentreq){

        Optional<Student> existingstudent = studentRespository.findById(id);

        if(existingstudent.isEmpty()){
            return null;
        }
       Student StudentTosave = existingstudent.get();

        StudentTosave.setName(studentreq.getName());
        StudentTosave.setEmail(studentreq.getEmail());
        StudentTosave.setAge(studentreq.getAge());
        StudentTosave.setRoll_no(studentreq.getRoll_no());
        StudentTosave.setSubject(studentreq.getSubject());

       return  studentRespository.save(StudentTosave);
    }

    public Boolean deletestudent(Long id){

        Boolean isstudent = studentRespository.existsById(id);
        if(!isstudent) return false;

        studentRespository.deleteById(id);

        return true;
    }
}
