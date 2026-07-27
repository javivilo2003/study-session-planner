package com.junior.roadmap;

import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void shouldCreateSessionFromScannerInput() {
        Scanner scanner = new Scanner("Math\nPractice algebra\n45\n");

        Session session = Main.createSesh(scanner);

        assertEquals("Math", session.getSubject());
        assertEquals("Practice algebra", session.getGoal());
        assertEquals(Integer.valueOf(45), session.getSessionMin());
    }
}
