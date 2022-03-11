package org.example;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;

public class WordFinder {

    final List<Character> candidateCharacters;
    final String template;
    final static Set<String> fullWordList = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

    static {
        fullWordList.addAll(Arrays.asList(loadResource("dictionary", "english_words_full.txt").split("\n")));
    }


    public WordFinder(List<Character> candidatesCharacters, String template) {
        this.candidateCharacters = candidatesCharacters;
        this.template = template;
    }

    public List<String> findCandidates() {
        Queue<Character> templateLetters = new ArrayDeque<>(
            template.chars().mapToObj(e -> (char) e).collect(Collectors.toList()));
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

                fillAndCheck(candidateQueue, nextTemplate, newCandidate, results);
            }
        } else {
            candidate.append(templateChar);

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
