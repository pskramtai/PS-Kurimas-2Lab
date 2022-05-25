package com.example.lab2.usecases;

import com.example.lab2.mybatis.dao.GroupMapper;
import com.example.lab2.mybatis.model.Group;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class GroupsMyBatis {

    @Inject
    private GroupMapper groupMapper;

    @Getter
    private List<Group> allGroups;

    @Getter @Setter
    private Group groupToCreate = new Group();

    @PostConstruct
    public void init() {
        this.loadAllTeams();
    }

    private void loadAllTeams() {
        this.allGroups = groupMapper.selectAll();
    }

    @Transactional
    public String createGroup() {
        groupMapper.insert(groupToCreate);
        return "/myBatis/groups?faces-redirect=true";
    }
}
