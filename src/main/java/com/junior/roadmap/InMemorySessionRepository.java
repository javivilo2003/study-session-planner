package com.junior.roadmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class InMemorySessionRepository implements SessionRepository{

    private final List<Session> sessions = new ArrayList<>();

    @Override
    public List<Session> findAll() {
        return new ArrayList<>(sessions);
    }

    @Override
    public void save(Session sesh) {
        sessions.add(sesh);
    }

    @Override
    public List<Session> findBySubject(String input) {
        List <Session> matches = new ArrayList<>();

        for (Session session : sessions) {
            if (session.getSubject().equalsIgnoreCase(input)) {
                matches.add(session);
            }
        }

        return matches;
    }

    @Override
    public Integer showTotalMin() {
        Integer total = 0;
        for (Session session : sessions) {
            if (session.getStatus() == Status.PLANNED) {
                total += session.getSessionMin();
            }
        }

        return total;
    }

    @Override
    public Session findById(UUID id){

        for (Session session : sessions) {
            if (session.getId().equals(id)) {
                return session;
            }
        }
        return null;
    }

    @Override
    public void deleteById(UUID id) {
        sessions.remove(findById(id));
    }

    @Override
    public List<Session> findByStatus(Status status) {
        List<Session> matches = new ArrayList<>();
        for (Session session : sessions) {
            if (session.getStatus() == (status)) {
                matches.add(session);  
            }
        }
        return matches;
    }

    @Override
    public Map<String, Integer> getPlannedMinutesBySubject() {
        Map<String, Integer> totales = new HashMap<>();
        for (Session session : sessions) {
            if (session.getStatus() == Status.PLANNED) {
                totales.put(session.getSubject(), totales.getOrDefault(session.getSubject(), 0) + session.getSessionMin());
            }
        }

        return totales;
    }
    
    

}
