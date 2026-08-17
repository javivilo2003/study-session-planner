package com.junior.roadmap.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.junior.roadmap.domain.Session;
import com.junior.roadmap.domain.Status;
import com.junior.roadmap.repository.persistence.SessionFileReader;
import com.junior.roadmap.repository.persistence.SessionFileWriter;

public class FileSessionRepository implements SessionRepository{

    private List<Session> sessionsInFile;
    private SessionFileWriter writer;

    public FileSessionRepository(SessionFileReader reader, SessionFileWriter writer){
        this.sessionsInFile = reader.readAll();
        this.writer = writer;
    }

    @Override
    public void deleteById(UUID id) {
        sessionsInFile.remove(findById(id));  
        writer.writeAll(sessionsInFile);
    }

    @Override
    public List<Session> findAll() {
        return new ArrayList<>(sessionsInFile);
    }

    @Override
    public Session findById(UUID id) {
        for (Session session : sessionsInFile) {
            if (session.getId().equals(id)) {
                return session;
            }
        }
        return null;
    }

    @Override
    public List<Session> findByStatus(Status status) {
        List<Session> matches = new ArrayList<>();
        for (Session session : sessionsInFile) {
            if (session.getStatus() == (status)) {
                matches.add(session);  
            }
        }
        return matches;
    }

    @Override
    public List<Session> findBySubject(String input) {
        List <Session> matches = new ArrayList<>();

        for (Session session : sessionsInFile) {
            if (session.getSubject().equalsIgnoreCase(input)) {
                matches.add(session);
            }
        }

        return matches;
    }

    @Override
    public Map<String, Integer> getPlannedMinutesBySubject() {
        Map<String, Integer> totales = new HashMap<>();
        for (Session session : sessionsInFile) {
            if (session.getStatus() == Status.PLANNED) {
                totales.put(session.getSubject(), totales.getOrDefault(session.getSubject(), 0) + session.getSessionMin());
            }
        }

        return totales;
    }

    @Override
    public void save(Session sesh) {
        sessionsInFile.add(sesh);
        writer.writeAll(sessionsInFile);
    }

    @Override
    public void persistChanges() {
        writer.writeAll(sessionsInFile);
    }

    @Override
    public Integer showTotalMin() {
        Integer total = 0;
        for (Session session : sessionsInFile) {
            if (session.getStatus() == Status.PLANNED) {
                total += session.getSessionMin();
            }
        }

        return total;
    }
    
}
