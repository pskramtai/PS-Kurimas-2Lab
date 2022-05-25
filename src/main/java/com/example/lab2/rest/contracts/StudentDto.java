package com.example.lab2.rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {
    private String name;

    private String surname;

    private Integer groupId;

    private String studentNum;
}
