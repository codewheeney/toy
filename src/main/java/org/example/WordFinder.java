package org.example;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;

public class WordFinder {

    final static Set<String> fullWordList = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

    static {
        fullWordList.addAll(Arrays.asList(loadResource("dictionary", "english_words_full.txt").split("\n")));
    }

    public WordFinder() {
    }

    public List<String> findCandidates(List<Character> candidateCharacters, String template) {
        Queue<Character> templateLetters = new ArrayDeque<>(
            template.toLowerCase(Locale.ROOT).chars().mapToObj(e -> (char) e).collect(Collectors.toList()));
        Queue<Character> candidateQueue = new ArrayDeque<>(candidateCharacters);

        StringBuilder candidate = new StringBuilder();
        List<String> results = new ArrayList<>();

        fillAndCheck(candidateQueue, templateLetters, candidate, results);

        return results;
    }

    void fillAndCheck(final Queue<Character> candidateQueue, final Queue<Character> templateLetters,
        final StringBuilder candidate, List<String> results) {

        if (templateLetters.isEmpty()) {
            if (fullWordList.contains(candidate.toString())) {
                results.add(candidate.toString());
            }
            return;
        }

        Queue<Character> nextTemplate = new ArrayDeque<>(templateLetters);

        Character templateChar = nextTemplate.remove();
        if (templateChar == '_') {
            for (Character c : candidateQueue) {
                StringBuilder newCandidate = new StringBuilder(candidate.toString());
                newCandidate.append(c);

                // Check prefix


                fillAndCheck(candidateQueue, nextTemplate, newCandidate, results);
            }
        } else {
            candidate.append(templateChar);

            // Check prefix

            fillAndCheck(candidateQueue, nextTemplate, new StringBuilder(candidate.toString()), results);
        }
    }

    private static String loadResource(String directoryPrefix, String fileName) {
        try (InputStream input = Thread.currentThread().getContextClassLoader()
            .getResourceAsStream(directoryPrefix + File.separatorChar + fileName)) {
            assert input != null;
            return IOUtils.toString(input, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            return null;
        }
    }
}
