package com.junior.roadmap;

public class Session {
    
    String subject;
    String goal;
    Integer sessionMin;
    Status status;

    public Session (){}

    public Session(String subject, String goal, Integer sessionMin){
        this.subject = subject;
        this.goal = goal;
        this.sessionMin = sessionMin;
        status = Status.PLANNED;
    }

    public Session(String subject, String goal, Integer sessionMin, Status status){
        this.subject = subject;
        this.goal = goal;
        this.sessionMin = sessionMin;
        this.status = status;
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
        return "[\nSubject: " + getSubject() + 
                "\nGoal: " + getGoal() + 
                "\nPlanned minutes: " + getSessionMin() + 
                "\nStatus: " +  status + " ]";
    }
}
