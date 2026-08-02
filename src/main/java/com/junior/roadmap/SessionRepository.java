package com.junior.roadmap;

import java.util.List;

public interface SessionRepository {
    void save(Session sesh);
    List<Session> findAll();
}
