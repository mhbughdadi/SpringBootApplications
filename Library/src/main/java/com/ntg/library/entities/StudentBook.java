package com.ntg.library.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "STUDENT_BORROW_BOOK")
@Data
@NoArgsConstructor
public class StudentBook {


    @EmbeddedId
    private StudentBookId id;

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

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn( name = "STUDENT_ID" )
    private Student student;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn( name = "BOOK_ID" )
    private Book book;


}
