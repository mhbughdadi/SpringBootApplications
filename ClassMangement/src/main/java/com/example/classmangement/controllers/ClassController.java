package com.example.classmangement.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.classmangement.dtos.common.GenericResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/classes")
public class ClassController {


    @GetMapping
    public ResponseEntity<GenericResponse> getMethodName() {
       
        return new ResponseEntity<>( new GenericResponse(),HttpStatus.OK);
    }
    
    
}
