package org.example;

import java.util.HashSet;
import java.util.Set;

public class FinallyTest {

    //private final Set<String> iMissConstCorrectness = Set.of("foo");
    private final Set<String> iMissConstCorrectness = new HashSet<>();

    private int i = 0;

    public void surpriseMe() {
        iMissConstCorrectness.add("I got your finally right here " + i);
        i++;

        System.out.println(iMissConstCorrectness);
    }
}
