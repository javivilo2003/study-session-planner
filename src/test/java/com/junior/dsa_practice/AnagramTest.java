package com.junior.dsa_practice;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AnagramTest {
    @Test
    public void testIsAnagram() {
        assertTrue(Anagram.isAnagram("listen", "silent"));
    }

    @Test
    public void testIsNotAnagram() {
        assertFalse(Anagram.isAnagram("hello", "world"));
    }
}
