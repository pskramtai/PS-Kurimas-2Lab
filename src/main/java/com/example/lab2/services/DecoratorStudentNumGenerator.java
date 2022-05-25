package com.example.lab2.services;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public class DecoratorStudentNumGenerator implements StudentNumGenerator{
    @Inject @Delegate @Any
    StudentNumGenerator studentNumGenerator;

    @Override
    public String generateStudentNum() {
        String studentNum = studentNumGenerator.generateStudentNum();
        studentNum = studentNum + "D";
        return studentNum;
    }
}
