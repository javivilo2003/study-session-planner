package com.junior.roadmap;

import java.util.UUID;

public class Session {
    
    UUID id;
    String subject;
    String goal;
    Integer sessionMin;
    Status status;

    public Session (){}

    public Session(String subject, String goal, Integer sessionMin) throws InvalidSessionException {
        setId(id);
        setSubject(subject);
        setGoal(goal);
        setSessionMin(sessionMin);
        status = Status.PLANNED;
    }

    public Session(String subject, String goal, Integer sessionMin, Status status) throws InvalidSessionException {
        setId(id);
        setSubject(subject);
        setGoal(goal);
        setSessionMin(sessionMin);
        this.status = status;
    }

    // For testing toString() method
    public Session(UUID id, String subject, String goal, Integer sessionMin, Status status) throws InvalidSessionException {
        setId(id);
        setSubject(subject);
        setGoal(goal);
        setSessionMin(sessionMin);
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {

        if (id == null) {
           id = UUID.randomUUID(); 
        }
        
        this.id = id;
    }
    
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) throws InvalidSessionException {
        
        if (subject == null || subject.isBlank()) {
            throw new InvalidSessionException("Subject cannot be blank.");
        } 
        
        this.subject = subject;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) throws InvalidSessionException{

        if (goal == null || goal.isBlank()) {
            throw new InvalidSessionException("The goal cannot be blank.");
        } 

        this.goal = goal;
    }

    public Integer getSessionMin() {
        return sessionMin;
    }

    public void setSessionMin(Integer sessionMin) throws InvalidSessionException {

        if (sessionMin == null) {
            throw new InvalidSessionException("The session's minutes cannot be blank.");
        } 

        if (sessionMin <= 0) {
            throw new InvalidSessionException("The session's minutes must be greater than 0.");
        }

        if (sessionMin > 480) {
            throw new InvalidSessionException("The session cannot exceed 480 minutes. Session is too long");
        }

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
