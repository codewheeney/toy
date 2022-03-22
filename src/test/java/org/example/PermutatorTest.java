package org.example;

import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.testng.Assert.assertEquals;

@Test
public class PermutatorTest {
    @Test
    public void testBasicPermutation() {
        Set<String> permutations = Permutator.permutateString("abc");

        Set<String> correctPermutations = new TreeSet<>();
        correctPermutations.addAll(List.of("abc", "acb", "bac", "bca", "cab", "cba"));

        assertEquals(permutations, correctPermutations);
    }
}
