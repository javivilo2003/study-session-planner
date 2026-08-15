package com.junior.roadmap.repository.persistence;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.junior.roadmap.domain.Session;

public class SessionFileWriter{

    String path;

    public SessionFileWriter(String path) {
        this.path = path;
    }

    public String format(Session session){
        String sessionFormated = session.getId() + " | " + session.getSubject() + " | " + session.getGoal() + " | " + session.getSessionMin() + " | " + session.getStatus();

        return sessionFormated;    
    }
    
    public void write(Session sesh){
        String line = format(sesh);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeAll(List<Session> sessions){

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for(Session sesh : sessions){
                String line = format(sesh);
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
    }
}
