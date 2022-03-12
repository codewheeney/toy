package org.example;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Usage App template candidtes");
            return;
        }

        WordFinder wordFinder = new WordFinder();

        List<String> results = wordFinder.findCandidates(
                args[1].chars().mapToObj(e -> (char) e).collect(Collectors.toList()),
                args[0]);

        System.out.println("Template: " + args[0]);
        System.out.println("Canidates: " + args[1]);

        for (String r : results) {
            System.out.println(r);
        }
    }
}
