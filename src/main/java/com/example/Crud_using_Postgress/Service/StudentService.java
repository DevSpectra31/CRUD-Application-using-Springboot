
package com.example.Crud_using_Postgress.Service;

import com.example.Crud_using_Postgress.DTO.CreateStudentRequestDTO;
import com.example.Crud_using_Postgress.DTO.CreateStudentResponseDTo;
import com.example.Crud_using_Postgress.DTO.UpdateRequestDto;
import com.example.Crud_using_Postgress.DTO.UpdateResponseDto;
import com.example.Crud_using_Postgress.Entity.Student;
import com.example.Crud_using_Postgress.Exception.DuplicateException;
import com.example.Crud_using_Postgress.Exception.ResourceNotFoundException;
import com.example.Crud_using_Postgress.Respository.StudentRespository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentService {

    private final StudentRespository studentRespository;

    public StudentService(StudentRespository studentRespository) {
        this.studentRespository = studentRespository;
    }
    public CreateStudentResponseDTo createStudent(CreateStudentRequestDTO createStudentRequestDTO) {

        Student student = mapToEntity(createStudentRequestDTO);

        if(emailExists(student)){
            throw  new DuplicateException("Student with email " + student.getEmail() + " alreay exists");
        }
        Student savedStudent = studentRespository.save(student);

        return mapToResEntity(savedStudent);
    }

    // Get Student by ID
    public CreateStudentResponseDTo getStudent(Long id) {

        Student student1 = studentRespository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with " + id + " " + "not found"));

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
        Student  existingstudent = studentRespository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));


        existingstudent.setName(updateRequestDto.getName());
        existingstudent.setAge(updateRequestDto.getAge());
        existingstudent.setRoll_no(updateRequestDto.getRoll_no());
        existingstudent.setSubject(updateRequestDto.getSubject());
        existingstudent.setUpdatedAt(LocalDateTime.now());

        Student student = studentRespository.save(existingstudent);
        return mapToEntityUpdate(student);
    }

    // Hard Delete Student
    public void deletestudent(Long id) {
        Student student = studentRespository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("user with id " + id  + "  not found"));


        studentRespository.deleteById(id);
    }

    // Soft Delete Student
    public void  DeleteStudentSoftly(Long id) {

        Student student = studentRespository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User with id "+id+" not found"));

        student.setIsdeleted(true);


    }

    //all-delete-record
//    public Student deleteriously(){
//         studentRespository.deleteAll();
//
//        return null;
//    }

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

    private boolean emailExists(Student student) {
        return student.getEmail() == null;
    }
}
