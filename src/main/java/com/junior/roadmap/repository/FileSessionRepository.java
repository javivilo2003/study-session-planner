package com.junior.roadmap.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.junior.roadmap.domain.Session;
import com.junior.roadmap.domain.Status;

public class FileSessionRepository implements SessionRepository{

    private List<Session> sessionsInFile = new ArrayList<>();

    @Override
    public void deleteById(UUID id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Session> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Session findById(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Session> findByStatus(Status status) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Session> findBySubject(String input) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Integer> getPlannedMinutesBySubject() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(Session sesh) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Integer showTotalMin() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
