package com.junior.roadmap.service;

import java.util.UUID;

import com.junior.roadmap.domain.Session;
import com.junior.roadmap.domain.Status;
import com.junior.roadmap.exceptions.InvalidSessionException;
import com.junior.roadmap.exceptions.SessionNotFoundException;
import com.junior.roadmap.repository.SessionRepository;

public class SessionService {

    private final SessionRepository repository;

    public SessionService(SessionRepository repository) {
        this.repository = repository;
    }

    public void updateSubject(UUID id, String subject) throws SessionNotFoundException, InvalidSessionException{
        Session sesh = repository.findById(id);

        if (sesh == null) {
            throw new SessionNotFoundException("Session not found.");
        } else {
            sesh.setSubject(subject);
        }
    }
    
    public void updateGoal(UUID id, String goal) throws InvalidSessionException, SessionNotFoundException{
        Session sesh = repository.findById(id);

        if (sesh == null) {
            throw new SessionNotFoundException("Session not found.");
        } else {
            sesh.setGoal(goal);
        }
    }

    public void updateSessionMin(UUID id, Integer minutes) throws InvalidSessionException, SessionNotFoundException{
        Session sesh = repository.findById(id);

        if (sesh == null) {
            throw new SessionNotFoundException("Session not found.");
        } else {
            sesh.setSessionMin(minutes);
        }
    }

    public void updateStatus(UUID id, Status status) throws InvalidSessionException, SessionNotFoundException{
        Session sesh = repository.findById(id);

        if (sesh == null) {
            throw new SessionNotFoundException("Session not found.");
        } else {
            sesh.setStatus(status);
        }
    }

    public void deleteSession(UUID id) throws SessionNotFoundException{
        Session delete;
        delete = repository.findById(id);
        if (delete == null) {
            throw new SessionNotFoundException("Session not found.");
        } else {
            repository.deleteById(id);
        }
    }

    public void completeSession(UUID id) throws SessionNotFoundException{
        Session completed;
        completed = repository.findById(id);
        if (completed == null) {
            throw new SessionNotFoundException("Session not found.");
        } else {
            completed.setStatus(Status.COMPLETED);
        }
    }

    public void showMinSummaryBySubject() throws SessionNotFoundException{
        if (repository.getPlannedMinutesBySubject() == null || repository.getPlannedMinutesBySubject().isEmpty()) {
            throw new SessionNotFoundException("Couldn't find any sessions with that subject.");
        } else {
            repository.getPlannedMinutesBySubject().forEach((key, value) -> {
                System.out.println(key + ": " + value + " minutes");
            });
        }
    }
}
