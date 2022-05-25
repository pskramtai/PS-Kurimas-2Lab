package com.example.lab2.rest;


import com.example.lab2.entities.Group;
import com.example.lab2.entities.Student;
import com.example.lab2.persistence.GroupsDAO;
import com.example.lab2.persistence.StudentsDAO;
import com.example.lab2.rest.contracts.StudentDto;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/students")
public class StudentController {
    @Inject
    @Getter @Setter
    private StudentsDAO studentsDAO;

    @Inject
    @Getter @Setter
    private GroupsDAO groupsDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id){

        Student student = studentsDAO.findOne(id);

        if(student == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        StudentDto studentDto = new StudentDto();
        studentDto.setName(student.getName());
        studentDto.setSurname(student.getSurname());
        studentDto.setStudentNum(student.getStudentNum());
        studentDto.setGroupId(student.getGroup().getId());

        return Response.ok(studentDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(StudentDto studentDto){
        try{
            Student student = new Student();
            Group group = groupsDAO.findOne(studentDto.getGroupId());

            if(group == null){
                return Response.status(Response.Status.CONFLICT).build();
            }

            student.setName(studentDto.getName());
            student.setSurname(studentDto.getSurname());
            student.setStudentNum(studentDto.getStudentNum());
            student.setGroup(group);

            studentsDAO.persist(student);

            return Response.ok().build();


        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") Integer studentId, StudentDto studentDto){
        try{
           Student student = studentsDAO.findOne(studentId);

           if(student == null){
               return Response.status(Response.Status.NOT_FOUND).build();
           }

           student.setName(studentDto.getName());
           student.setSurname(studentDto.getSurname());
           student.setStudentNum(studentDto.getStudentNum());

           studentsDAO.update(student);

           return Response.ok().build();

        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();

        }
    }
}
