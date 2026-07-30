package com.junior.dsa_practice;

import static org.junit.Assert.assertSame;

import org.junit.Test;

public class ContainsDuplicateTest {
    @Test
    public void testContainsDuplicate() {
        Integer[] numbers = {1, 2, 3, 1};

        assertSame(ContainsDuplicate.containsDuplicate(numbers), true);
    }

    @Test
    public void testDoesNotContainsDuplicate() {
        Integer[] numbers = {1, 2, 3, 4};

        assertSame(ContainsDuplicate.containsDuplicate(numbers), false);
    }
}
