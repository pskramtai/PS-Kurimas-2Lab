package com.example.lab2.usecases;

import com.example.lab2.interceptors.LoggedInvocation;
import com.example.lab2.services.StudentNumGenerator;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GenerateStudentNumber implements Serializable {
    @Inject
    StudentNumGenerator studentNumGenerator;

    private CompletableFuture<String> studentNumGenerationTask = null;

    @LoggedInvocation
    public String generateStudentNum(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        studentNumGenerationTask = CompletableFuture.supplyAsync(() -> studentNumGenerator.generateStudentNum());

        return "StudentsDetails?faces-redirect=true&amp;studentId="+requestParameters.get("studentId");
    }

    public boolean isStudentNumGenerationRunning() {
        return studentNumGenerationTask != null && !studentNumGenerationTask.isDone();
    }

    public String getStudentNumGenerationStatus() throws ExecutionException, InterruptedException {
        if (studentNumGenerationTask == null) {
            return null;
        }
        else if (isStudentNumGenerationRunning()) {
            return "Student number generation in progress";
        }
        return "Suggested number: " + studentNumGenerationTask.get();
    }
}
