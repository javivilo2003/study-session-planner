package com.junior.roadmap;

import java.util.ArrayList;
import java.util.List;

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
    public List<Session> findBySubject(List<Session> sessions, String input) {
        List <Session> matches = new ArrayList<>();

        for (Session session : sessions) {
            if (session.getSubject().equalsIgnoreCase(input)) {
                matches.add(session);
            }
        }

        return matches;
    }

    @Override
    public Integer showTotalMin(List<Session> sessions) {
        Integer total = 0;
        for (Session session : sessions) {
            if (session.getStatus() == Status.PLANNED) {
                total += session.getSessionMin();
            }
        }

        return total;
    }

}
