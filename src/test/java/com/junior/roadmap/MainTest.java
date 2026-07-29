package com.junior.roadmap;

import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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
    public void cratesValidSession(){
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

}

