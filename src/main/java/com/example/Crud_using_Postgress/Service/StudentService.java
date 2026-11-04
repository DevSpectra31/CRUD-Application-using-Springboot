
package com.example.Crud_using_Postgress.Service;

import com.example.Crud_using_Postgress.DTO.CreateStudentRequestDTO;
import com.example.Crud_using_Postgress.DTO.CreateStudentResponseDTo;
import com.example.Crud_using_Postgress.DTO.UpdateRequestDto;
import com.example.Crud_using_Postgress.DTO.UpdateResponseDto;
import com.example.Crud_using_Postgress.Entity.Student;
import com.example.Crud_using_Postgress.Respository.StudentRespository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRespository studentRespository;

    public StudentService(StudentRespository studentRespository) {
        this.studentRespository = studentRespository;
    }
    public CreateStudentResponseDTo createStudent(CreateStudentRequestDTO createStudentRequestDTO) {

        Student student = mapToEntity(createStudentRequestDTO);

        Student savedStudent = studentRespository.save(student);

        return mapToResEntity(savedStudent);
    }

    // Get Student by ID
    public CreateStudentResponseDTo getStudent(Long id) {

        Student student1 = studentRespository.getReferenceById(id);

        return mapToResEntity(student1);
    }

    // Get All Active Students
    public List<CreateStudentResponseDTo> getallstudent() {

        List<Student> students = studentRespository.findAll();

        return students.stream()
                .map(this::mapToResEntity)
                .toList();
    }

    // Update Student
    public UpdateResponseDto updateStudent(Long id, UpdateRequestDto updateRequestDto) {
        Optional<Student> existingstudent =
                studentRespository.findByIdAndIsdeletedFalse(id);

        if (existingstudent.isEmpty()) {
            return null;
        }

        Student studentToSave = existingstudent.get();

        studentToSave.setName(updateRequestDto.getName());
        studentToSave.setAge(updateRequestDto.getAge());
        studentToSave.setRoll_no(updateRequestDto.getRoll_no());
        studentToSave.setSubject(updateRequestDto.getSubject());
        studentToSave.setUpdatedAt(LocalDateTime.now());

        Student student = studentRespository.save(studentToSave);
        return mapToEntityUpdate(student);
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
    public Student deleteriously(){
         studentRespository.deleteAll();

        return null;
    }

    private Student mapToEntity(CreateStudentRequestDTO createStudentRequestDTO) {
        Student student = new Student();
        student.setName(createStudentRequestDTO.getName());
        student.setEmail(createStudentRequestDTO.getEmail());
        student.setAge(createStudentRequestDTO.getAge());
        student.setRoll_no(createStudentRequestDTO.getRoll_no());
        student.setSubject(createStudentRequestDTO.getSubject());

        student.setIsdeleted(false);
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

        return  student;
    }

    private CreateStudentResponseDTo mapToResEntity(Student student) {
        CreateStudentResponseDTo createStudentResponseDTo = new CreateStudentResponseDTo();
        createStudentResponseDTo.setName(student.getName());
        createStudentResponseDTo.setEmail(student.getEmail());
        createStudentResponseDTo.setAge(student.getAge());
        createStudentResponseDTo.setRoll_no(student.getRoll_no());
        createStudentResponseDTo.setSubject(student.getSubject());
        createStudentResponseDTo.setMessage("student created successfully");
        createStudentResponseDTo.setUpdatedAt(student.getUpdatedAt());
        createStudentResponseDTo.setCreatedAt(student.getCreatedAt());

        return createStudentResponseDTo;
    }


    private UpdateResponseDto mapToEntityUpdate(Student student){
        UpdateResponseDto updateResponseDto = new UpdateResponseDto();

        updateResponseDto.setName(student.getName());
        updateResponseDto.setAge(student.getAge());
        updateResponseDto.setRoll_no(student.getRoll_no());
        updateResponseDto.setSubject((student.getSubject()));
        updateResponseDto.setUpdatedAt(student.getUpdatedAt());
        updateResponseDto.setMessage("user updated sucessfully");

        return updateResponseDto;
    }
}
