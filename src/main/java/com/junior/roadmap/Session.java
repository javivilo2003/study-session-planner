package com.junior.roadmap;

import java.util.UUID;

public class Session {
    
    UUID id;
    String subject;
    String goal;
    Integer sessionMin;
    Status status;

    public Session (){}

    public Session(String subject, String goal, Integer sessionMin){
        id = UUID.randomUUID();
        this.subject = subject;
        this.goal = goal;
        this.sessionMin = sessionMin;
        status = Status.PLANNED;
    }

    public Session(String subject, String goal, Integer sessionMin, Status status){
        id = UUID.randomUUID();
        this.subject = subject;
        this.goal = goal;
        this.sessionMin = sessionMin;
        this.status = status;
    }

    // For testing toString() method
    public Session(UUID id, String subject, String goal, Integer sessionMin, Status status) {
        this.id = id;
        this.subject = subject;
        this.goal = goal;
        this.sessionMin = sessionMin;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public Integer getSessionMin() {
        return sessionMin;
    }

    public void setSessionMin(Integer sessionMin) {
        this.sessionMin = sessionMin;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return  "\nSession ID " + getId() + ": [" +
                "\nSubject: " + getSubject() + 
                "\nGoal: " + getGoal() + 
                "\nPlanned minutes: " + getSessionMin() + 
                "\nStatus: " +  status + " ]";
    }  
}
