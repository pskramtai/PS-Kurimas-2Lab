package com.example.lab2.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.Random;

@Alternative
@ApplicationScoped
public class AlternativeStudentNumGenerator implements StudentNumGenerator{
    @Override
    public String generateStudentNum() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e)
        {

        }

        String studentNum = "";
        for (int i = 0; i < 10; i++) {
            studentNum += Integer.toString(new Random().nextInt(10));
        }

        studentNum = "999" + studentNum.substring(3);

        return studentNum;
    }
}
