package com.junior.roadmap;

public class Session {
    
    String subject;
    String goal;
    Integer sessionMin;

    public Session(String subject, String goal, Integer sessionMin){
        this.subject = subject;
        this.goal = goal;
        this.sessionMin = sessionMin;
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

    @Override
    public String toString() {
        return "[\nSubject: " + getSubject() + 
                "\nGoal: " + getGoal() + 
                "\nPlanned minutes: " + getSessionMin() + "\n]";
    }

    
}
