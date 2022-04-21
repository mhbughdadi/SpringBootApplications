package com.ntg.library.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "STUDENTS")
@Data
@NoArgsConstructor
public class Student {

    @Id
    @Column(name = "STUDENT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;
    @Column(name = "MAJOR")
    private String major;
    @Column(name = "MINOR")
    private String minor;
    @Column(name = "GRADUATE")
    private Boolean graduate;

    @Column( name = "GENDER")
    private Boolean gender;

//

    @OneToMany(mappedBy = "student")
    private List<StudentBook> borrowedBooks = new ArrayList<>();


}
