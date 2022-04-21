package com.ntg.library.controllers;

import com.ntg.library.entities.Book;
import com.ntg.library.repositories.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class CategoryController {

    @Autowired
    BookRepo bookRepo ;

    @GetMapping(value = "/{id}")
    public Book findBookById(@PathVariable(value = "id") Long id){
        return bookRepo.findById(id).get();
    }

    @GetMapping
    public List<Book> findAllBooks(){
        return bookRepo.findAll();
    }
}
