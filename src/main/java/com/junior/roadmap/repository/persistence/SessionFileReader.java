package com.junior.roadmap.repository.persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.junior.roadmap.domain.Session;
import com.junior.roadmap.domain.Status;
import com.junior.roadmap.exceptions.InvalidSessionException;

public class SessionFileReader {
    
    String path;

    public SessionFileReader(String path){
        this.path = path;
    }

    public SessionFileReader(){};

    public Session parse(String fileSesh) throws InvalidSessionException{
        String[] parts = fileSesh.split(" \\| ", -1);

        if (parts.length != 5) {
            throw new InvalidSessionException("Invalid session line: " + fileSesh);
        }

        UUID id = UUID.fromString(parts[0]);
        String subject = parts[1];
        String goal = parts[2];
        Integer minutes = Integer.parseInt(parts[3].trim());
        Status status = Status.valueOf(parts[4].trim());

        return new Session(id, subject, goal, minutes, status);
    }

    public List<Session> readAll(){
        List<Session> sessions = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                if (!line.isBlank()) {
                    sessions.add(parse(line));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sessions;
    }
}
