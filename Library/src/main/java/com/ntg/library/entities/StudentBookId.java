package com.ntg.library.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentBookId  implements Serializable {

    @Column( name = "STUDENT_ID")
    private Long studentId;
    @Column( name = "BOOK_ID")
    private Long bookId;
}
