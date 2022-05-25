package com.example.lab2.usecases;

import com.example.lab2.entities.Group;
import com.example.lab2.persistence.GroupsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Groups {
    @Inject
    private GroupsDAO groupsDAO;

    @Getter @Setter
    private Group groupToCreate = new Group();

    @Getter
    private List<Group> allGroups;

    @PostConstruct
    public void init(){
        loadAllGroups();
    }

    @Transactional
    public void createGroup(){
        this.groupsDAO.persist(groupToCreate);
    }

    private void loadAllGroups(){this.allGroups = groupsDAO.loadAll();}

}
