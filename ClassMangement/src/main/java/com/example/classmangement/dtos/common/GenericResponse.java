package com.example.classmangement.dtos.common;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericResponse  implements Serializable{

    private static final long serialVersionUID = 1234567L;
    
    int code;
    ErrorMessage errorMessage;
}
