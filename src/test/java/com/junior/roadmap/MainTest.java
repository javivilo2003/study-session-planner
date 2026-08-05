package com.junior.roadmap;

import org.junit.Test;

import java.util.Scanner;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class MainTest {

    @Test
    public void shouldCreateSessionFromScannerInput() {
        Scanner scanner = new Scanner("Math\nPractice algebra\n45\n");

        Session session = Main.createSesh(scanner);

        assertEquals("Math", session.getSubject());
        assertEquals("Practice algebra", session.getGoal());
        assertEquals(Integer.valueOf(45), session.getSessionMin());
        assertEquals(Status.PLANNED, session.getStatus());
    }

    @Test
    public void cratesValidSession() throws InvalidSessionException{
        Session session = new Session("Java", "DSA", Integer.valueOf(60));

        assertEquals("Java", session.getSubject());
        assertEquals("DSA", session.getGoal());
        assertEquals(Integer.valueOf(60), session.getSessionMin());
        assertEquals(Status.PLANNED, session.getStatus());
    }

    @Test
    public void rejectsEmptySubjectThenAcceptsValidSubject() {
        Scanner sc = new Scanner("\nJava\nPractice Big O\n30\n");

        Session sesh = Main.createSesh(sc);

        assertEquals("Java", sesh.getSubject());
        assertEquals("Practice Big O", sesh.getGoal());
        assertEquals(Integer.valueOf(30), sesh.getSessionMin());
        assertEquals(Status.PLANNED, sesh.getStatus());
    }

    @Test
    public void rejectsEmptyGoalThenAcceptsValidGoal() {
        Scanner sc = new Scanner("Java\n\nPractice Big O\n30\n");

        Session sesh = Main.createSesh(sc);

        assertEquals("Java", sesh.getSubject());
        assertEquals("Practice Big O", sesh.getGoal());
        assertEquals(Integer.valueOf(30), sesh.getSessionMin());
    }

    @Test
    public void rejectsInvalidMinutesThenAcceptsValidMinutes() {
        Scanner sc = new Scanner("Java\nPractice Big O\nabc\n30\n");

        Session sesh = Main.createSesh(sc);

        assertEquals("Java", sesh.getSubject());
        assertEquals("Practice Big O", sesh.getGoal());
        assertEquals(Integer.valueOf(30), sesh.getSessionMin());
    }

    @Test
    public void rejectsInvalidMinutesZeroThenAcceptsValidMinutes() {
        Scanner sc = new Scanner("Java\nPractice Big O\n0\n30\n");

        Session sesh = Main.createSesh(sc);

        assertEquals("Java", sesh.getSubject());
        assertEquals("Practice Big O", sesh.getGoal());
        assertEquals(Integer.valueOf(30), sesh.getSessionMin());
    }

    @Test
    public void rejectsInvalidMinutesNegativeThenAcceptsValidMinutes() {
        Scanner sc = new Scanner("Java\nPractice Big O\n-30\n30\n");

        Session sesh = Main.createSesh(sc);

        assertEquals("Java", sesh.getSubject());
        assertEquals("Practice Big O", sesh.getGoal());
        assertEquals(Integer.valueOf(30), sesh.getSessionMin());
    }

    @Test
    public void rejectsInvalidMinutesExceededValueThenAcceptsValidMinutes() {
        Scanner sc = new Scanner("Java\nPractice Big O\n481\n30\n");

        Session sesh = Main.createSesh(sc);

        assertEquals("Java", sesh.getSubject());
        assertEquals("Practice Big O", sesh.getGoal());
        assertEquals(Integer.valueOf(30), sesh.getSessionMin());
    }

    @Test
    public void completingExistingSessionChangesStatusToCompleted() throws InvalidSessionException {
        SessionRepository repository = new InMemorySessionRepository();
        SessionService service = new SessionService(repository);
        UUID id = UUID.randomUUID();
        repository.save(new Session(id, "Math", "Practice algebra", 45, Status.PLANNED));
        Scanner sc = new Scanner(id + "\n");

        Main.completeSession(sc, repository, service);

        assertEquals(Status.COMPLETED, repository.findById(id).getStatus());
    }

    @Test
    public void completingMissingSessionFailsClearly() throws InvalidSessionException, SessionNotFoundException {
        SessionRepository repository = new InMemorySessionRepository();
        SessionService service = new SessionService(repository);

        UUID existingId = UUID.randomUUID();
        UUID missingId = UUID.randomUUID();
        repository.save(new Session(existingId, "Math", "Practice algebra", 45, Status.PLANNED));

        SessionNotFoundException exception = assertThrows(
            SessionNotFoundException.class,
            () -> service.completeSession(missingId)
        );

        assertEquals("Session not found.", exception.getMessage());
        assertEquals(Status.PLANNED, repository.findById(existingId).getStatus());
    }

    @Test
    public void deletingExistingSessionRemovesItFromFindAll() throws InvalidSessionException {
        SessionRepository repository = new InMemorySessionRepository();
        SessionService service = new SessionService(repository);
        UUID id = UUID.randomUUID();
        repository.save(new Session(id, "Math", "Practice algebra", 45, Status.PLANNED));
        Scanner sc = new Scanner(id + "\n");

        Main.deleteSession(sc, repository, service);
        assertTrue(repository.findAll().isEmpty());
        assertNull(repository.findById(id));
    }

    @Test
    public void deletingMissingSessionFailsClearly() throws InvalidSessionException, SessionNotFoundException {
        SessionRepository repository = new InMemorySessionRepository();
        SessionService service = new SessionService(repository);
        UUID existingId = UUID.randomUUID();
        UUID missingId = UUID.randomUUID();
        repository.save(new Session(existingId, "Math", "Practice algebra", 45, Status.PLANNED));

        SessionNotFoundException exception = assertThrows(
            SessionNotFoundException.class,
            () -> service.deleteSession(missingId)
        );

        assertEquals("Session not found.", exception.getMessage());
        assertEquals(1, repository.findAll().size());
        assertEquals(existingId, repository.findAll().get(0).getId());
    }
}
