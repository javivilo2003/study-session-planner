package com.junior.roadmap;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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
import com.junior.roadmap.service.SessionService;

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

    @Test
    public void updatedSubjectIsWrittenToFile() throws Exception{
        Path tempFile = Files.createTempFile("sessions-test", ".txt");
        Session session = new Session(UUID.randomUUID(), "Math", "Practice algebra", 45, Status.PLANNED);

        FileSessionRepository repository = new FileSessionRepository(
            new SessionFileReader(tempFile.toString()),
            new SessionFileWriter(tempFile.toString())
        );
        SessionService service = new SessionService(repository);
        repository.save(session);

        service.updateSubject(session.getId(), "Java");

        FileSessionRepository recreatedRepository = new FileSessionRepository(
            new SessionFileReader(tempFile.toString()),
            new SessionFileWriter(tempFile.toString())
        );

        assertEquals("Java", recreatedRepository.findById(session.getId()).getSubject());
    }

    @Test
    public void completedSessionIsWrittenToFile() throws Exception{
        Path tempFile = Files.createTempFile("sessions-test", ".txt");
        Session session = new Session(UUID.randomUUID(), "Math", "Practice algebra", 45, Status.PLANNED);

        FileSessionRepository repository = new FileSessionRepository(
            new SessionFileReader(tempFile.toString()),
            new SessionFileWriter(tempFile.toString())
        );
        SessionService service = new SessionService(repository);
        repository.save(session);

        service.completeSession(session.getId());

        FileSessionRepository recreatedRepository = new FileSessionRepository(
            new SessionFileReader(tempFile.toString()),
            new SessionFileWriter(tempFile.toString())
        );

        assertEquals(Status.COMPLETED, recreatedRepository.findById(session.getId()).getStatus());
    }

    @Test
    public void skipsCorruptSessionLineAndLoadsValidLines() throws Exception{
        Path tempFile = Files.createTempFile("sessions-test", ".txt");
        UUID id = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();
        Files.writeString(tempFile,
            id + " | Math | Practice algebra | 45 | PLANNED\n" +
            id2 + " | DSA | Practice algorithms | pp | Hello\n" +
            id3 + " | DSA | Practice algorithms | 20 | COMPLETED\n"
        );

        FileSessionRepository repository = new FileSessionRepository(
            new SessionFileReader(tempFile.toString()),
            new SessionFileWriter(tempFile.toString())
        );

        List<Session> sessionsRead = repository.findAll();

        assertEquals(2, sessionsRead.size());
        assertEquals(id, sessionsRead.get(0).getId());
        assertEquals("Math", sessionsRead.get(0).getSubject());
        assertEquals("Practice algebra", sessionsRead.get(0).getGoal());
        assertEquals(Integer.valueOf(45), sessionsRead.get(0).getSessionMin());
        assertEquals(Status.PLANNED, sessionsRead.get(0).getStatus());

        assertEquals(id3, sessionsRead.get(1).getId());
        assertEquals("DSA", sessionsRead.get(1).getSubject());
        assertEquals("Practice algorithms", sessionsRead.get(1).getGoal());
        assertEquals(Integer.valueOf(20), sessionsRead.get(1).getSessionMin());
        assertEquals(Status.COMPLETED, sessionsRead.get(1).getStatus());

    }

    @Test
    public void rejectsLineWithMissingFields() throws Exception{
        SessionFileReader reader = new SessionFileReader();

        assertThrows(InvalidSessionException.class, () -> {
            reader.parse("Math | Practice algebra | abc | PLANNED");
        }); 
    }

    @Test
    public void rejectsInvalidMinutes() throws Exception{
        SessionFileReader reader = new SessionFileReader();

        assertThrows(InvalidSessionException.class, () -> {
            reader.parse("550e8400-e29b-41d4-a716-446655440000 | Math | Practice algebra | abc | PLANNED");
        });
    }

    @Test
    public void rejectsInvalidStatus() throws Exception{
        SessionFileReader reader = new SessionFileReader();

        assertThrows(InvalidSessionException.class, () -> {
            reader.parse("550e8400-e29b-41d4-a716-446655440000 | Math | Practice algebra | 20 | HELLO");
        });
    }

    @Test
    public void rejectsInvalidUuid() throws Exception{
        SessionFileReader reader = new SessionFileReader();

        assertThrows(InvalidSessionException.class, () -> {
            reader.parse("007 | Math | Practice algebra | 20 | PLANNED");
        });
    }
}
