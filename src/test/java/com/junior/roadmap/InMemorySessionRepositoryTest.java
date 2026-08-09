package com.junior.roadmap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;


import java.util.List;
import java.util.UUID;

import org.junit.Test;

public class InMemorySessionRepositoryTest {

    @Test
    public void afterSavingSessionAppearsInFindAll() throws InvalidSessionException{
        SessionRepository repository = new InMemorySessionRepository();
        UUID id = UUID.randomUUID(); 
        repository.save(new Session(id, "Math", "Practice algebra", 45, Status.PLANNED));

        assertEquals("[\nSession ID " + id + "\nSubject: Math\nGoal: Practice algebra\nPlanned minutes: 45\nStatus: PLANNED\n-----------------------------------------------\n]", repository.findAll().toString());

    }
    
    @Test
    public void savingTwoSessionsAppearInFindAll() throws InvalidSessionException{
        SessionRepository repository = new InMemorySessionRepository();
        UUID id1 = UUID.randomUUID(); 
        UUID id2 = UUID.randomUUID(); 

        repository.save(new Session(id1, "Math", "Practice algebra", 45, Status.PLANNED));
        repository.save(new Session(id2, "Java", "Practice java", 60, Status.PLANNED));

        assertEquals("[\nSession ID " + id1 + "\nSubject: Math\nGoal: Practice algebra\nPlanned minutes: 45\nStatus: PLANNED\n-----------------------------------------------\n, " +
        "\nSession ID " + id2 + "\nSubject: Java\nGoal: Practice java\nPlanned minutes: 60\nStatus: PLANNED\n-----------------------------------------------\n]", repository.findAll().toString());
    }

    @Test
    public void findAllDoesNotExposeInternalListDirectly() throws InvalidSessionException {
        SessionRepository repository = new InMemorySessionRepository();
        Session session = new Session(UUID.randomUUID(), "Math", "Practice algebra", 45, Status.PLANNED);
        repository.save(session);

        List<Session> returnedSessions = repository.findAll();
        assertNotSame(returnedSessions, repository.findAll());

        returnedSessions.clear();

        assertEquals(1, repository.findAll().size());
        assertEquals(session, repository.findAll().get(0));
    }

    @Test
    public void searchShowsMultipleSubjects() throws InvalidSessionException {
        SessionRepository repository = new InMemorySessionRepository();
        UUID id1 = UUID.randomUUID(); 
        UUID id2 = UUID.randomUUID(); 
        UUID id3 = UUID.randomUUID(); 

        repository.save(new Session(id1, "Math", "Practice algebra", 45, Status.PLANNED));
        repository.save(new Session(id2, "Java", "Practice java", 60, Status.PLANNED));
        repository.save(new Session(id3, "Java", "Practice java", 60, Status.PLANNED));

        assertEquals("[\nSession ID " + id2 + "\nSubject: Java\nGoal: Practice java\nPlanned minutes: 60\nStatus: PLANNED\n-----------------------------------------------\n, " +
        "\nSession ID " + id3 + "\nSubject: Java\nGoal: Practice java\nPlanned minutes: 60\nStatus: PLANNED\n-----------------------------------------------\n]", repository.findBySubject( "java").toString());
    }

    @Test
    public void searchShowsNoSessions() throws InvalidSessionException {
        SessionRepository repository = new InMemorySessionRepository();
        UUID id1 = UUID.randomUUID(); 
        UUID id2 = UUID.randomUUID(); 
        UUID id3 = UUID.randomUUID(); 

        repository.save(new Session(id1, "Math", "Practice algebra", 45, Status.PLANNED));
        repository.save(new Session(id2, "Java", "Practice java", 60, Status.PLANNED));
        repository.save(new Session(id3, "Java", "Practice java", 60, Status.PLANNED));

        assertEquals("[]", repository.findBySubject( "dsa").toString());
    }

    @Test
    public void searchShowsMultipleSessionsWithSameStatus() throws InvalidSessionException {
        SessionRepository repository = new InMemorySessionRepository();
        UUID id1 = UUID.randomUUID(); 
        UUID id2 = UUID.randomUUID(); 
        UUID id3 = UUID.randomUUID(); 

        repository.save(new Session(id1, "Math", "Practice algebra", 45, Status.COMPLETED));
        repository.save(new Session(id2, "Java", "Practice java", 60, Status.PLANNED));
        repository.save(new Session(id3, "DSA", "Practice arrays", 30, Status.COMPLETED));

        assertEquals("[\nSession ID " + id1 + "\nSubject: Math\nGoal: Practice algebra\nPlanned minutes: 45\nStatus: COMPLETED\n-----------------------------------------------\n, " +
        "\nSession ID " + id3 + "\nSubject: DSA\nGoal: Practice arrays\nPlanned minutes: 30\nStatus: COMPLETED\n-----------------------------------------------\n]", repository.findByStatus(Status.COMPLETED).toString());
    }

    @Test
    public void searchShowsNoSessionsWithStatus() throws InvalidSessionException {
        SessionRepository repository = new InMemorySessionRepository();
        UUID id1 = UUID.randomUUID(); 
        UUID id2 = UUID.randomUUID(); 
        UUID id3 = UUID.randomUUID(); 

        repository.save(new Session(id1, "Math", "Practice algebra", 45, Status.PLANNED));
        repository.save(new Session(id2, "Java", "Practice java", 60, Status.PLANNED));
        repository.save(new Session(id3, "DSA", "Practice arrays", 30, Status.COMPLETED));

        assertEquals("[]", repository.findByStatus(Status.CANCELLED).toString());
    }


    @Test
    public void findsSessionWithUUID() throws InvalidSessionException,  SessionNotFoundException {
        SessionRepository repository = new InMemorySessionRepository();
        UUID id1 = UUID.randomUUID(); 
        repository.save(new Session(id1, "Math", "Practice algebra", 45, Status.PLANNED));
        
        Session found = repository.findById(id1);
        assertEquals(repository.findAll().getFirst(), found);
    }

    @Test
    public void sessionWithUUIDNotFound() throws InvalidSessionException, SessionNotFoundException {
        SessionRepository repository = new InMemorySessionRepository();
        UUID id1 = UUID.randomUUID(); 
        UUID id2 = UUID.randomUUID(); 

        repository.save(new Session(id1, "Math", "Practice algebra", 45, Status.PLANNED));
        
        Session found = repository.findById(id2);
        assertNull(found);
    }

    @Test
    public void mapReturnsEmptyMap() throws SessionNotFoundException {
        SessionRepository repository = new InMemorySessionRepository();

        assertEquals(repository.getPlannedMinutesBySubject(), repository.getPlannedMinutesBySubject());
    }

    @Test
    public void mapReturnsOneSession() throws SessionNotFoundException, InvalidSessionException {
        SessionRepository repository = new InMemorySessionRepository();
        UUID id1 = UUID.randomUUID(); 

        repository.save(new Session(id1, "Java", "Practice java", 60, Status.PLANNED));


        assertEquals("{Java=60}", repository.getPlannedMinutesBySubject().toString());
    }

    @Test
    public void mapSameSubjectTwice() throws SessionNotFoundException, InvalidSessionException {
        SessionRepository repository = new InMemorySessionRepository();
        UUID id1 = UUID.randomUUID(); 
        UUID id2 = UUID.randomUUID(); 

        repository.save(new Session(id1, "Java", "Practice java", 60, Status.PLANNED));
        repository.save(new Session(id2, "Java", "Practice java", 45, Status.PLANNED));


        assertEquals("{Java=105}", repository.getPlannedMinutesBySubject().toString());
    }

    @Test
    public void mapDifferentSubject() throws SessionNotFoundException, InvalidSessionException {
        SessionRepository repository = new InMemorySessionRepository();
        UUID id1 = UUID.randomUUID(); 
        UUID id2 = UUID.randomUUID(); 

        repository.save(new Session(id1, "Java", "Practice java", 60, Status.PLANNED));
        repository.save(new Session(id2, "DSA", "Practice java", 45, Status.PLANNED));


        assertEquals("{Java=60, DSA=45}", repository.getPlannedMinutesBySubject().toString());
    }
}
