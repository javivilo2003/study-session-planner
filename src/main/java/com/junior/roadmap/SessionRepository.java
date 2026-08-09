package com.junior.roadmap;

import java.util.List;
import java.util.Map;
import java.util.UUID;


public interface SessionRepository {
    void save(Session sesh);
    List<Session> findAll();
    List<Session> findBySubject(String input);
    Session findById(UUID id);
    Integer showTotalMin();
    void deleteById(UUID id);
    List<Session> findByStatus(Status status);
    Map<String, Integer> getPlannedMinutesBySubject();
}
