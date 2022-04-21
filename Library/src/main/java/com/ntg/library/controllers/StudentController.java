package com.ntg.library.controllers;

import com.ntg.library.entities.Student;
import com.ntg.library.repositories.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController()
@RequestMapping(value = "/students",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

    @Autowired
    StudentRepo studentRepo ;

    @GetMapping(value = "/{id}")
    public Student findStudent(@PathVariable(value = "id") Long id){
        return studentRepo.findById(id).get();
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAllStudents(){
        return ResponseEntity.status(HttpStatus.OK).body( studentRepo.findAll());
    }

    @PostMapping
    public ResponseEntity<Student> addNewStudent(@RequestBody Student student){
        Student stud =  studentRepo.save(student);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(stud);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") Long id){
        Optional<Student>  opStudent = studentRepo.findById(id);
        if(opStudent.isPresent()){

            Student updated = studentRepo.save(student);

            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .body(updated);
        }else{

            return ResponseEntity
                    .badRequest().build();
        }
    }


}
