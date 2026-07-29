package com.junior.roadmap;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

public class SessionTest {

    @Test
    public void shouldCreateSessionWithSubjectGoalAndMinutes() {
        Session session = new Session("Math", "Practice algebra", 45);

        assertEquals("Math", session.getSubject());
        assertEquals("Practice algebra", session.getGoal());
        assertEquals(Integer.valueOf(45), session.getSessionMin());
    }

    @Test
    public void testGetGoal() {
        Session session = new Session("Science", "Read chapter 3", 30);

        assertEquals("Read chapter 3", session.getGoal());
    }

    @Test
    public void testGetSessionMin() {
        Session session = new Session("History", "Review notes", 25);

        assertEquals(Integer.valueOf(25), session.getSessionMin());
    }

    @Test
    public void testGetSubject() {
        Session session = new Session("English", "Write outline", 20);

        assertEquals("English", session.getSubject());
    }

    @Test
    public void testSetGoal() {
        Session session = new Session("Math", "Practice algebra", 45);

        session.setGoal("Finish worksheet");

        assertEquals("Finish worksheet", session.getGoal());
    }

    @Test
    public void testSetSessionMin() {
        Session session = new Session("Math", "Practice algebra", 45);

        session.setSessionMin(60);

        assertEquals(Integer.valueOf(60), session.getSessionMin());
    }

    @Test
    public void testSetSubject() {
        Session session = new Session("Math", "Practice algebra", 45);

        session.setSubject("Physics");

        assertEquals("Physics", session.getSubject());
    }

    @Test
    public void testToString() {
        UUID id = UUID.randomUUID();
        Session session = new Session(id, "Math", "Practice algebra", 45, Status.PLANNED);

        assertEquals("\nSession ID " + id + ": [\nSubject: Math\nGoal: Practice algebra\nPlanned minutes: 45\nStatus: PLANNED ]", session.toString());
    }
}
