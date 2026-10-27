
package com.example.Crud_using_Postgress.Service;

import com.example.Crud_using_Postgress.Entity.Student;
import com.example.Crud_using_Postgress.Respository.StudentRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRespository studentRespository;

    public StudentService(StudentRespository studentRespository) {
        this.studentRespository = studentRespository;
    }

    // Create Student
    public Student createStudent(Student studentreq) {
        studentreq.setIsdeleted(false);
        return studentRespository.save(studentreq);
    }

    // Get Student by ID
    public Student getStudent(Long id) {
        return studentRespository
                .findByIdAndIsdeletedFalse(id)
                .orElse(null);
    }

    // Get All Active Students
    public List<Student> getallstudent() {
        return studentRespository.findByIsdeletedFalse();
    }

    // Update Student
    public Student updateStudent(Long id, Student studentreq) {
        Optional<Student> existingstudent =
                studentRespository.findByIdAndIsdeletedFalse(id);

        if (existingstudent.isEmpty()) {
            return null;
        }

        Student studentToSave = existingstudent.get();

        studentToSave.setName(studentreq.getName());
        studentToSave.setEmail(studentreq.getEmail());
        studentToSave.setAge(studentreq.getAge());
        studentToSave.setRoll_no(studentreq.getRoll_no());
        studentToSave.setSubject(studentreq.getSubject());

        return studentRespository.save(studentToSave);
    }

    // Hard Delete Student
    public Boolean deletestudent(Long id) {
        Optional<Student> student = studentRespository.findById(id);

        if (student.isEmpty()) {
            return false;
        }

        studentRespository.delete(student.get());
        return true;
    }

    // Soft Delete Student
    public Boolean DeleteStudentSoftly(Long id) {
        Optional<Student> existingstudent =
                studentRespository.findByIdAndIsdeletedFalse(id);

        if (existingstudent.isEmpty()) {
            return false;
        }

        Student studentToSave = existingstudent.get();
        studentToSave.setIsdeleted(true);

        studentRespository.save(studentToSave);

        return true;
    }

    //all-delete-record
    public Student deleteallrecord(){
         studentRespository.deleteAll();

        return null;
    }
}
