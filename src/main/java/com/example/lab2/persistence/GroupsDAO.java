package com.example.lab2.persistence;

import com.example.lab2.entities.Group;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class GroupsDAO {

    @Inject
    private EntityManager em;

    public List<Group> loadAll(){
        return em.createNamedQuery("Group.findAll", Group.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Group group){
        this.em.persist(group);
    }

    public Group findOne(Integer id){
        return em.find(Group.class, id);
    }
}
