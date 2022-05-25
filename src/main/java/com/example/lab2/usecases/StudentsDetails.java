package com.example.lab2.usecases;

import com.example.lab2.entities.Student;
import com.example.lab2.interceptors.LoggedInvocation;
import com.example.lab2.persistence.StudentsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
public class StudentsDetails implements Serializable {

    @Inject
    private StudentsDAO studentsDAO;

    @Getter @Setter
    private Student student;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer studentId = Integer.parseInt(requestParameters.get("studentId"));
        this.student = studentsDAO.findOne(studentId);
    }

    @LoggedInvocation
    @Transactional
    public String updateStudent(){
        try{
            studentsDAO.update(student);
        }catch(OptimisticLockException ole){
            return "StudentsDetails?faces-redirect=true&amp;studentId="+student.getId()+"&error=optimistic-lock-exception";
        }
        return "StudentsDetails?faces-redirect=true&amp;studentId="+student.getId();
    }

}
