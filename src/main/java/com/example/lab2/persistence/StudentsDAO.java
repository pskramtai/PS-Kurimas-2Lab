package com.example.lab2.persistence;

import com.example.lab2.entities.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class StudentsDAO {
    @Inject
    private EntityManager em;

    public void persist(Student student){
        this.em.persist(student);
    }

    public Student findOne(Integer id){
        return this.em.find(Student.class, id);
    }

    public Student update(Student student){
        return this.em.merge(student);
    }
}
