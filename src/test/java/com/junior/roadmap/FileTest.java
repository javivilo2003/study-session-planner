package com.junior.roadmap;


import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.junior.roadmap.domain.Session;
import com.junior.roadmap.domain.Status;
import com.junior.roadmap.exceptions.InvalidSessionException;
import com.junior.roadmap.repository.FileSessionRepository;
import com.junior.roadmap.repository.persistence.SessionFileReader;
import com.junior.roadmap.repository.persistence.SessionFileWriter;

public class FileTest {
    
    @Test
    public void writesASessionInTheCorrectFormat() throws InvalidSessionException, IOException{
        Path tempFile = Files.createTempFile("sessions-test", ".txt");

        UUID id = UUID.randomUUID();
        Session sesh = new Session(id, "Math", "Practice algebra", 45, Status.PLANNED);
        SessionFileWriter scw = new SessionFileWriter(tempFile.toString());
        

        assertEquals(id + " | Math | Practice algebra | 45 | PLANNED", scw.format(sesh));
    } 

    @Test
    public void writesASession() throws InvalidSessionException, IOException{
        Path tempFile = Files.createTempFile("sessions-test", ".txt");

        UUID id = UUID.randomUUID();
        Session sesh = new Session(id, "Math", "Practice algebra", 45, Status.PLANNED);

        SessionFileWriter scw = new SessionFileWriter(tempFile.toString());
        scw.write(sesh);
        String content = Files.readString(tempFile);

        assertEquals(id + " | Math | Practice algebra | 45 | PLANNED\n", content);
    } 

    @Test
    public void writesMultipleSessions() throws InvalidSessionException, IOException{
        Path tempFile = Files.createTempFile("sessions-test", ".txt");

        UUID id = UUID.randomUUID();
        Session sesh = new Session(id, "Math", "Practice algebra", 45, Status.PLANNED);

        UUID id2 = UUID.randomUUID();
        Session sesh2 = new Session(id2, "DSA", "Practice algorithms", 20, Status.PLANNED);
        List<Session> sessions = new ArrayList<>();
        sessions.add(sesh);
        sessions.add(sesh2);
        SessionFileWriter scw = new SessionFileWriter(tempFile.toString());
        scw.writeAll(sessions);
        String content = Files.readString(tempFile);

        assertEquals(id + " | Math | Practice algebra | 45 | PLANNED\n" + id2 + " | DSA | Practice algorithms | 20 | PLANNED\n", content);
    } 

    @Test
    public void writeAllOverwritesExistingSessions() throws InvalidSessionException, IOException{
        Path tempFile = Files.createTempFile("sessions-test", ".txt");

        Session firstSession = new Session(UUID.randomUUID(), "Math", "Practice algebra", 45, Status.PLANNED);
        Session secondSession = new Session(UUID.randomUUID(), "DSA", "Practice algorithms", 20, Status.PLANNED);
        List<Session> firstWrite = new ArrayList<>();
        firstWrite.add(firstSession);
        firstWrite.add(secondSession);

        Session replacementSession = new Session(UUID.randomUUID(), "Java", "Practice interfaces", 30, Status.PLANNED);
        List<Session> secondWrite = new ArrayList<>();
        secondWrite.add(replacementSession);

        SessionFileWriter scw = new SessionFileWriter(tempFile.toString());
        scw.writeAll(firstWrite);
        scw.writeAll(secondWrite);

        String content = Files.readString(tempFile);

        assertEquals(replacementSession.getId() + " | Java | Practice interfaces | 30 | PLANNED\n", content);
    }

    @Test
    public void loadsOneSavedSession() throws InvalidSessionException, IOException{
        Path tempFile = Files.createTempFile("sessions-test", ".txt");
        UUID id = UUID.randomUUID();
        Files.writeString(tempFile, id + " | Math | Practice algebra | 45 | PLANNED\n");

        SessionFileReader reader = new SessionFileReader(tempFile.toString());
        List<Session> sessions = reader.readAll();

        assertEquals(1, sessions.size());
        assertEquals(id, sessions.get(0).getId());
        assertEquals("Math", sessions.get(0).getSubject());
        assertEquals("Practice algebra", sessions.get(0).getGoal());
        assertEquals(Integer.valueOf(45), sessions.get(0).getSessionMin());
        assertEquals(Status.PLANNED, sessions.get(0).getStatus());
    }

    @Test
    public void loadsMultipleSavedSessions() throws InvalidSessionException, IOException{
        Path tempFile = Files.createTempFile("sessions-test", ".txt");
        UUID id = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        Files.writeString(tempFile,
            id + " | Math | Practice algebra | 45 | PLANNED\n" +
            id2 + " | DSA | Practice algorithms | 20 | COMPLETED\n"
        );

        SessionFileReader reader = new SessionFileReader(tempFile.toString());
        List<Session> sessions = reader.readAll();

        assertEquals(2, sessions.size());
        assertEquals(id, sessions.get(0).getId());
        assertEquals("Math", sessions.get(0).getSubject());
        assertEquals(Status.PLANNED, sessions.get(0).getStatus());
        assertEquals(id2, sessions.get(1).getId());
        assertEquals("DSA", sessions.get(1).getSubject());
        assertEquals(Status.COMPLETED, sessions.get(1).getStatus());
    }

    @Test
    public void savedSessionLoadsAfterRepositoryRecreation() throws InvalidSessionException, IOException{
        Path tempFile = Files.createTempFile("sessions-test", ".txt");
        Session session = new Session(UUID.randomUUID(), "Math", "Practice algebra", 45, Status.PLANNED);

        FileSessionRepository firstRepository = new FileSessionRepository(
            new SessionFileReader(tempFile.toString()),
            new SessionFileWriter(tempFile.toString())
        );
        firstRepository.save(session);

        FileSessionRepository recreatedRepository = new FileSessionRepository(
            new SessionFileReader(tempFile.toString()),
            new SessionFileWriter(tempFile.toString())
        );

        List<Session> loadedSessions = recreatedRepository.findAll();

        assertEquals(1, loadedSessions.size());
        assertEquals(session.getId(), loadedSessions.get(0).getId());
        assertEquals("Math", loadedSessions.get(0).getSubject());
        assertEquals("Practice algebra", loadedSessions.get(0).getGoal());
        assertEquals(Integer.valueOf(45), loadedSessions.get(0).getSessionMin());
        assertEquals(Status.PLANNED, loadedSessions.get(0).getStatus());
    }
}
