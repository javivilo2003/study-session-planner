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
        UUID id;
        Integer minutes;
        Status status;

        String[] parts = fileSesh.split(" \\| ", -1);

        if (parts.length != 5) {
            throw new InvalidSessionException("Invalid session line: " + fileSesh);
        }

        try {
            id = UUID.fromString(parts[0]);
        } catch (IllegalArgumentException e) {
            throw new InvalidSessionException("Session ID invalid format or empty.");
        }

        try {
            minutes = Integer.parseInt(parts[3].trim());
        } catch (NumberFormatException e) {
            throw new InvalidSessionException("Invalid number format for minutes.");
        }

        try {
            status = Status.valueOf(parts[4].trim());
        } catch (IllegalArgumentException e) {
            throw new InvalidSessionException("Invalid status or empty status.");
        }

        return new Session(id, parts[1], parts[2], minutes, status);
    }

    public List<Session> readAll(){
        List<Session> sessions = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                if (!line.isBlank()) {
                    try{
                        sessions.add(parse(line));
                    } catch (InvalidSessionException e){
                        
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong reading the file.");
        }

        return sessions;
    }
}
