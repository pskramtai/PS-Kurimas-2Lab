package com.example.lab2.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Course.findAll", query = "select c from Course as c")
})
@Getter @Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Basic
    @Column(name = "NAME")
    private String name;


    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

}
