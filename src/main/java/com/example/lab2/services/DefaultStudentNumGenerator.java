package com.example.lab2.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.util.Random;

@Default
@ApplicationScoped
public class DefaultStudentNumGenerator implements StudentNumGenerator{
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
        return studentNum;
    }
}
