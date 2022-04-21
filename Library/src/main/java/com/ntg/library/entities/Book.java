package com.ntg.library.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BOOKS")
@Data
@NoArgsConstructor
public class Book {

    @Id
    @Column(name = "BOOK_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PARTS_NUM")
    private Integer partsNum;
    @Column( name = "DELETED")
    private Boolean deleted;

    @ManyToOne
    @JoinColumn( name = "AUTHOR_ID" )
    private Author author;

    @ManyToOne
    @JoinColumn( name = "CATEGORY_ID")
    private Category category;

    @OneToMany(mappedBy = "book")
    List<StudentBook> borrowedBooks = new ArrayList<>();


}
