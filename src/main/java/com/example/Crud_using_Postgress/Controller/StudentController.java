package com.example.Crud_using_Postgress.Controller;

import com.example.Crud_using_Postgress.DTO.*;
import com.example.Crud_using_Postgress.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }
    @PostMapping
    public ResponseEntity<CreateStudentResponseDTo> CreateStudent(@Valid @RequestBody CreateStudentRequestDTO createStudentRequestDTO){

        CreateStudentResponseDTo createdstudent = studentService.createStudent(createStudentRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdstudent);

    }

    @GetMapping("{id}")
    public ResponseEntity<CreateStudentResponseDTo> getStudent(@PathVariable Long id){
        CreateStudentResponseDTo students = studentService.getStudent(id);

        if(students == null){
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity
                .ok(students);
    }

    @GetMapping
    public  ResponseEntity<List<CreateStudentResponseDTo>> getAllStudent(){
        List<CreateStudentResponseDTo> students = studentService.getallstudent();
        if(students.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(students);
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateResponseDto> updateStudent(@RequestParam Long id, @RequestBody UpdateRequestDto studentreq){

        UpdateResponseDto updatedstudent = studentService.updateStudent(id,studentreq);
        if(updatedstudent == null){
            return ResponseEntity.notFound().build();
        }

        return  ResponseEntity.ok(updatedstudent);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CreateStudentResponseDTo> deleteStudent(@RequestParam Long id){

        studentService.deletestudent(id);
        return  ResponseEntity.noContent().build();
    }

//    @DeleteMapping("/delete-all")
//    public ResponseEntity<String> deleteallrecord(){
//        Student alldeleted = studentService.deleteriously();
//
//        return ResponseEntity.ok("All record deleted successfully");
//    }

    @PatchMapping("soft-delete")
    public ResponseEntity<String> DeleteStudentSoftly(@RequestParam Long id){
        studentService.DeleteStudentSoftly(id);
        return  ResponseEntity.ok("user marked for deleted");
    }

}
