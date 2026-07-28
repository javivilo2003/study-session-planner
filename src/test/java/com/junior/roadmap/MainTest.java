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
    public void rejectsEmptySubject(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Session("", "Practice Big O", 30);
        });
    }

}

