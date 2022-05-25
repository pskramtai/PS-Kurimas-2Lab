package com.example.lab2.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;

@Specializes
@ApplicationScoped
public class SpecialStudentNumGenerator extends DefaultStudentNumGenerator{
    @Override
    public String generateStudentNum() {
        String studentNum = super.generateStudentNum();
        studentNum = "888" + studentNum.substring(3);
        return studentNum;
    }
}
