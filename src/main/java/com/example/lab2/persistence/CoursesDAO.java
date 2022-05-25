package com.example.lab2.persistence;


import com.example.lab2.entities.Course;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CoursesDAO {

    @Inject
    private EntityManager em;

    public List<Course> loadAll(){
        return em.createNamedQuery("Course.findAll", Course.class).getResultList();
    }

    public void setEm(EntityManager em){
        this.em =  em;
    }

    public void persist(Course course){
        em.persist(course);
    }

    public Course findOne(Long id){
        return em.find(Course.class, id);
    }
}
