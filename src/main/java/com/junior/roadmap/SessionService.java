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
}
