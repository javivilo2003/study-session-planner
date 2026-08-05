package com.junior.roadmap;

import java.util.UUID;

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
}
