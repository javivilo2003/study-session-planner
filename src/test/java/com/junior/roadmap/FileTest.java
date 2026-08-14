package com.junior.roadmap;


import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Test;

import com.junior.roadmap.domain.Session;
import com.junior.roadmap.domain.Status;
import com.junior.roadmap.exceptions.InvalidSessionException;
import com.junior.roadmap.repository.persistence.SessionFileWriter;

public class FileTest {
    
    @Test
    public void writesASessionInTheCorrectFormat() throws InvalidSessionException{
        UUID id = UUID.randomUUID();
        Session sesh = new Session(id, "Math", "Practice algebra", 45, Status.PLANNED);
        SessionFileWriter scw = new SessionFileWriter("sessions.txt");
        

        assertEquals(id + " | Math | Practice algebra | 45 | PLANNED", scw.format(sesh));
    }
}
