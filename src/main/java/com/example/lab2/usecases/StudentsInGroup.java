package com.example.lab2.usecases;

import com.example.lab2.entities.Group;
import com.example.lab2.entities.Student;
import com.example.lab2.interceptors.LoggedInvocation;
import com.example.lab2.persistence.GroupsDAO;
import com.example.lab2.persistence.StudentsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class StudentsInGroup {

    @Inject
    private GroupsDAO groupsDAO;

    @Inject
    private StudentsDAO studentsDAO;

    @Getter @Setter
    private Group group;

    @Getter @Setter
    private Student studentToCreate = new Student();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer groupId = Integer.parseInt(requestParameters.get("groupId"));
        this.group = groupsDAO.findOne(groupId);
    }

    @LoggedInvocation
    @Transactional
    public void createStudent(){
        studentToCreate.setGroup(group);
        studentsDAO.persist(studentToCreate);
    }
}
