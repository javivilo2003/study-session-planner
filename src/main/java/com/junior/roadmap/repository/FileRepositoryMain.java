package com.junior.roadmap.repository;

import java.util.List;
import java.util.UUID;

import com.junior.roadmap.domain.Session;
import com.junior.roadmap.domain.Status;
import com.junior.roadmap.exceptions.InvalidSessionException;
import com.junior.roadmap.repository.persistence.SessionFileReader;
import com.junior.roadmap.repository.persistence.SessionFileWriter;

public class FileRepositoryMain {
    public static void main(String[] args) throws InvalidSessionException {
        String path = "testing-rw.txt";
        SessionFileReader reader = new SessionFileReader(path);
        SessionFileWriter writer = new SessionFileWriter(path);
        FileSessionRepository repository = new FileSessionRepository(reader, writer);


        Session sesh = new Session(UUID.fromString("2a74ce1c-7535-41fa-bf45-9e4c2d36fca7"), "DSA", "Practice read", 50, Status.PLANNED);
        Session sesh2 = new Session(UUID.fromString("2a74ce1c-7535-41fa-bf45-9e4c2d36fca8"), "Java", "Practice write", 20, Status.COMPLETED);
        Session sesh3 = new Session(UUID.fromString("2a74ce1c-7535-41fa-bf45-9e4c2d36fca9"), "Math", "Practice algebra", 35, Status.PLANNED);

        repository.save(sesh);
        repository.save(sesh2);
        repository.save(sesh3);


        List<Session> sessions = repository.findAll();

        System.out.println(sessions.toString());
        
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println(repository.findByStatus(Status.COMPLETED).toString());

    }
}
