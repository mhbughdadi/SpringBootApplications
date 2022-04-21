package com.ntg.library.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "AUTHORS")
@Data
@NoArgsConstructor
public class Author {

    @Id
    @Column(name = "AUTHOR_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;
    @Column(name = "JOB")
    private String job;
    @Column(name = "DEGREE")
    private String degree;

    @OneToMany(mappedBy = "author")
    private List<Book> books = new ArrayList<>();


}
