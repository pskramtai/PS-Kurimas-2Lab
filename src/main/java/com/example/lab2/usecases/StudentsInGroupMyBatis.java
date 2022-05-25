package com.example.lab2.usecases;

import com.example.lab2.mybatis.dao.GroupMapper;
import com.example.lab2.mybatis.dao.StudentMapper;
import com.example.lab2.mybatis.model.Group;
import com.example.lab2.mybatis.model.Student;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Model
public class StudentsInGroupMyBatis {

        @Inject
        private StudentMapper studentMapper;

        @Inject
        private GroupMapper groupMapper;

        @Getter @Setter
        private Group group;

        @Getter
        private List<Student> students;

        @Getter @Setter
        private Student studentToCreate = new Student();

        @PostConstruct
        public void init() {
            Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            Integer groupId = Integer.parseInt(requestParameters.get("groupId"));
            this.group = groupMapper.selectByPrimaryKey(groupId);
            setStudents(groupId);
        }

        private void setStudents(Integer groupId){
            List<Student> students = studentMapper.selectAll();
            this.students = students.stream().filter(s -> s.getGroupId() == groupId).collect(Collectors.toList());
        }

    @Transactional
    public void createStudent(){
        studentToCreate.setGroupId(group.getId());
        studentMapper.insert(studentToCreate);
    }
}
