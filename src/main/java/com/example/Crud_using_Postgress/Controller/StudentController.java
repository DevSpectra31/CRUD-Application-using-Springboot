package com.example.Crud_using_Postgress.Controller;

import com.example.Crud_using_Postgress.DTO.CreateStudentRequestDTO;
import com.example.Crud_using_Postgress.DTO.CreateStudentResponseDTo;
import com.example.Crud_using_Postgress.DTO.UpdateRequestDto;
import com.example.Crud_using_Postgress.DTO.UpdateResponseDto;
import com.example.Crud_using_Postgress.Entity.Student;
import com.example.Crud_using_Postgress.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private  StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }
    @PostMapping("/create")
    public ResponseEntity<CreateStudentResponseDTo> CreateStudent( @Valid @RequestBody CreateStudentRequestDTO createStudentRequestDTO){

        CreateStudentResponseDTo createdstudent = studentService.createStudent(createStudentRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdstudent);

    }

    @GetMapping("/get")
    public ResponseEntity<Student> getStudent(@RequestParam Long id){
        Student students = studentService.getStudent(id);

        if(students == null){
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity
                .ok(students);
    }

    @GetMapping("/getall")
    public  ResponseEntity<List<Student>> getAllStudent(){
        List<Student> students = studentService.getallstudent();
        if(students == null){
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
    public ResponseEntity<String> deleteStudent(@RequestParam Long id){
        Boolean isdeleted = studentService.deletestudent(id);

        if(!isdeleted){
            return ResponseEntity.notFound().build();
        }

        return  ResponseEntity.ok("student deleted successfully");
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteallrecord(){
        Student alldeleted = studentService.deleteriously();

        return ResponseEntity.ok("All record deleted successfully");
    }

    @PatchMapping("soft-delete")
    public ResponseEntity<String> DeleteStudentSoftly(@RequestParam Long id){
        Boolean isdeleted = studentService.DeleteStudentSoftly(id);

        if(!isdeleted){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Student marked for deletion");
    }

}
