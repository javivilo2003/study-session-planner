package com.junior.dsa_practice;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ContainsDuplicateTest {
    @Test
    public void testContainsDuplicate() {
        Integer[] numbers = {1, 2, 3, 1};

        assertTrue(ContainsDuplicate.containsDuplicate(numbers));
    }

    @Test
    public void testDoesNotContainDuplicate() {
        Integer[] numbers = {1, 2, 3, 4};

        assertFalse(ContainsDuplicate.containsDuplicate(numbers));
    }
}
