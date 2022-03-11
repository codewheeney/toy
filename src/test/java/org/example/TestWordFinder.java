package org.example;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.google.common.base.Stopwatch;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.Test;

@Test
public class TestWordFinder {

    @Test
    public void testLoadDictionary() {
        WordFinder wordFinder = new WordFinder(List.of('a', 'b'), "__d");
        assertEquals(WordFinder.fullWordList.size(), 372968);
        assertTrue(WordFinder.fullWordList.contains("bad"));

        StringBuilder candidate = new StringBuilder();
        candidate.append("bad");
        assertTrue(WordFinder.fullWordList.contains(candidate.toString()));
    }

    @Test
    public void testSimpleCase() {
        WordFinder wordFinder = new WordFinder(List.of('a', 'b'), "__d");

        List<String> results = wordFinder.findCandidates();
        assertEquals(results.size(), 2);
        assertTrue(results.contains("bad"));
        assertTrue(results.contains("abd"));
    }

    @Test
    public void testWordle() {
        String candidates = "qweyuofghjkzxvb";

        WordFinder wordFinder = new WordFinder(
            candidates.chars().mapToObj(e -> (char) e).collect(Collectors.toList()),
            "_ou_e");

        List<String> results = wordFinder.findCandidates();

        assertFalse(results.isEmpty());

        for (String result : results) {
            System.out.println(result);
        }
    }

    @Test
    public void testWordle137() {
        String candidates = "qwrtypasghjklzxcvbnm";

        WordFinder wordFinder = new WordFinder(
            candidates.chars().mapToObj(e -> (char) e).collect(Collectors.toList()),
            "a_t_r");

        List<String> results = wordFinder.findCandidates();

        assertFalse(results.isEmpty());

        for (String result : results) {
            System.out.println(result);
        }
    }

    @Test
    public void testWordle138() {
        String candidates = "qwryupfghjklzxcvbm";

        WordFinder wordFinder = new WordFinder(
            candidates.chars().mapToObj(e -> (char) e).collect(Collectors.toList()),
            "_ul_y");

        List<String> results = wordFinder.findCandidates();

        assertFalse(results.isEmpty());

        System.out.printf("There are %d results\n", results.size());
        for (String result : results) {
            System.out.println(result);
        }
    }

    @Test
    public void testWordle140() {
        String candidates = "qwetyopasfgjkzxcvnm";

        WordFinder wordFinder = new WordFinder(
            candidates.chars().mapToObj(e -> (char) e).collect(Collectors.toList()),
            "_oast");

        List<String> results = wordFinder.findCandidates();

        assertFalse(results.isEmpty());

        System.out.printf("There are %d results\n", results.size());
        for (String result : results) {
            System.out.println(result);
        }
    }

    @Test
    public void testWorldAtch() {
        String candidates = "qwrtypafhjkzxcvm";

        WordFinder wordFinder = new WordFinder(
            candidates.chars().mapToObj(e -> (char) e).collect(Collectors.toList()),
            "_atch");

        List<String> results = wordFinder.findCandidates();

        assertFalse(results.isEmpty());

        System.out.printf("There are %d results\n", results.size());
        for (String result : results) {
            System.out.println(result);
        }
    }


    @Test
    public void testWorldHomeworkCandidate() {
        String candidates = "qwertypafghjklzxcvbn";

        WordFinder wordFinder = new WordFinder(
            candidates.chars().mapToObj(e -> (char) e).collect(Collectors.toList()),
            "_rea_");

        List<String> results = wordFinder.findCandidates();

        assertFalse(results.isEmpty());

        System.out.printf("There are %d results\n", results.size());
        for (String result : results) {
            System.out.println(result);
        }
    }

    @Test
    public void testWordleSolved() {
        String candidates = "qwryupfghjklzxcvbm";
        String answer = "puppy";

        WordFinder wordFinder = new WordFinder(
            candidates.chars().mapToObj(e -> (char) e).collect(Collectors.toList()),
            answer);

        List<String> results = wordFinder.findCandidates();

        assertEquals(results, List.of(answer));
    }

    @Test
    public void testBigWerds() {
        final String answer = "decussate";
        String candidates = "qwyuofghjdkzxvb";
        String template = "_ec_ssate";

        WordFinder wordFinder = new WordFinder(
            candidates.chars().mapToObj(e -> (char) e).collect(Collectors.toList()),
            template);

        List<String> results = wordFinder.findCandidates();

        assertTrue(results.contains(answer));
    }

    @Test
    public void testAllThreeLetterWords() {
        String candidates = "abcdefghijklmnopqrstuvwxyz";
        String template = "___";

        WordFinder wordFinder = new WordFinder(
            candidates.chars().mapToObj(e -> (char) e).collect(Collectors.toList()),
            template);

        List<String> results = wordFinder.findCandidates();

        assertFalse(results.isEmpty());
    }

    static final int COUNT = 1000;

    @Test
    public void timeIt() {
        Stopwatch timeIt = Stopwatch.createStarted();
        String candidates = "qweyuofghjkzxvb";

        WordFinder wordFinder = new WordFinder(
            candidates.chars().mapToObj(e -> (char) e).collect(Collectors.toList()),
            "_ou_e");

        for (int i = 0; i < COUNT; i++) {
            wordFinder.findCandidates();
        }
        System.out.printf("That took %d ms\n", timeIt.elapsed().toMillis());
    }
}
