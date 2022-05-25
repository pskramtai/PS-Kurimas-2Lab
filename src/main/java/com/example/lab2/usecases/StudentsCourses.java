package com.example.lab2.usecases;

import com.example.lab2.entities.Course;
import com.example.lab2.entities.Group;
import com.example.lab2.entities.Student;
import com.example.lab2.interceptors.LoggedInvocation;
import com.example.lab2.persistence.CoursesDAO;
import com.example.lab2.persistence.GroupsDAO;
import com.example.lab2.persistence.StudentsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Model
public class StudentsCourses{

    @Inject
    private StudentsDAO studentsDAO;

    @Inject
    private CoursesDAO coursesDAO;

    @Getter @Setter
    private Student student;

    @Getter @Setter
    private Course courseToAdd;

    @Getter @Setter
    private Long courseToAddId;

    @Getter @Setter
    private Long courseToRemoveId;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer studentId = Integer.parseInt(requestParameters.get("studentId"));
        this.student = studentsDAO.findOne(studentId);
    }

    @LoggedInvocation
    @Transactional
    public String addCourse(){
        this.courseToAdd = coursesDAO.findOne(courseToAddId);
        if(!student.getCourses().contains(courseToAdd)){
            student.getCourses().add(courseToAdd);
        }
        studentsDAO.update(student);

        return "studentsCourses?faces-redirect=true&amp;studentId="+student.getId();
    }

    @LoggedInvocation
    @Transactional
    public void removeCourse(Long courseToRemoveId){
        student.getCourses().remove(coursesDAO.findOne(courseToRemoveId));
        studentsDAO.update(student);
    }

    @Transactional
    public void removeCourseByForm(){
        student.getCourses().remove(coursesDAO.findOne(courseToRemoveId));
        studentsDAO.update(student);
    }
}
