package com.example.Crud_using_Postgress.Controller;

import com.example.Crud_using_Postgress.Entity.Student;
import com.example.Crud_using_Postgress.Service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }
    @PostMapping("/create")
    public ResponseEntity<Student> CreateStudent(@RequestBody Student student){

        Student createdstudent = studentService.createStudent(student);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdstudent);

    }

    @GetMapping("/get")
    public ResponseEntity<Student> getStudent(@RequestParam Long id){
        Student students = studentService.getStudent(id);

        if(students == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .ok(students);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Student>> getAllStudent(){
        List<Student> students = studentService.getallstudent();
        if(students == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(students);
    }

    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestParam Long id, @RequestBody Student studentreq){

        Student updatedstudent = studentService.updateStudent(id,studentreq);
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
}
