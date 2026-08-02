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

}
