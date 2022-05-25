package com.example.lab2.usecases;

import com.example.lab2.entities.Course;
import com.example.lab2.persistence.CoursesDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Map;

@Model
public class CoursesStudents {

    @Inject
    private CoursesDAO coursesDAO;

    @Getter @Setter
    private Course course;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long courseId = Long.parseLong(requestParameters.get("courseId"));
        this.course = coursesDAO.findOne(courseId);
    }
}
