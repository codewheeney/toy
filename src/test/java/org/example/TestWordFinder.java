package org.example;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.google.common.base.Stopwatch;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.testng.annotations.Test;

@Test
public class TestWordFinder {

    private final WordFinder wordFinder = new WordFinder();

    @Test
    public void testLoadDictionary() {
        assertEquals(WordFinder.fullWordList.size(), 372968);
        assertTrue(WordFinder.fullWordList.contains("bad"));

        StringBuilder candidate = new StringBuilder();
        candidate.append("bad");
        assertTrue(WordFinder.fullWordList.contains(candidate.toString()));
    }

    @Test
    public void testSimpleCase() {
        WordFinder wordFinder = new WordFinder();

        List<String> results = wordFinder.findCandidates(List.of('a', 'b'), "__d");
        assertEquals(results.size(), 2);
        assertTrue(results.contains("bad"));
        assertTrue(results.contains("abd"));
    }

    @Test
    public void testWordle() {
        WordFinder wordFinder = new WordFinder();

        List<String> results = wordFinder.findCandidates(
                "qweyuofghjkzxvb".chars().mapToObj(e -> (char) e).collect(Collectors.toList()),
                "_ou_e");

        assertFalse(results.isEmpty());

        for (String result : results) {
            System.out.println(result);
        }
    }

    @Test
    public void testWordle137() {

        List<String> results = wordFinder.findCandidates(
                "qwrtypasghjklzxcvbnm".chars().mapToObj(e -> (char) e).collect(Collectors.toList()),
                "a_t_r");

        assertFalse(results.isEmpty());

        for (String result : results) {
            System.out.println(result);
        }
    }

    @Test
    public void testWordle138() {
        List<String> results = wordFinder.findCandidates(
                "qwryupfghjklzxcvbm".chars().mapToObj(e -> (char) e).collect(Collectors.toList()),
                "_ul_y");

        assertFalse(results.isEmpty());

        System.out.printf("There are %d results\n", results.size());
        for (String result : results) {
            System.out.println(result);
        }
    }

    @Test
    public void testWordle140() {
        List<String> results = wordFinder.findCandidates(
                "qwetyopasfgjkzxcvnm".chars().mapToObj(e -> (char) e).collect(Collectors.toList()),
                "_oast");

        assertFalse(results.isEmpty());

        System.out.printf("There are %d results\n", results.size());
        for (String result : results) {
            System.out.println(result);
        }
    }

    @Test
    public void testWordle142() {
        List<String> results = wordFinder.findCandidates(
                "qwertyiopfghjkzxcvnm".chars().mapToObj(e -> (char) e).collect(Collectors.toList()),
                "_roi_");

        assertFalse(results.isEmpty());

        System.out.printf("There are %d results\n", results.size());
        for (String result : results) {
            System.out.println(result);
        }
    }

    @Test
    public void testWordle143() {
        List<String> results = wordFinder.findCandidates(
                "qweryifghjklzxcvbnm".chars().mapToObj(e -> (char) e).collect(Collectors.toList()),
                "_i_e_");

        assertFalse(results.isEmpty());

        System.out.printf("There are %d results\n", results.size());
        for (String result : results) {
            System.out.println(result);
        }
    }

    @Test
    public void testWorldAtch() {
        List<String> results = wordFinder.findCandidates(
                "qwrtypafhjkzxcvm".chars().mapToObj(e -> (char) e).collect(Collectors.toList()),
                "_atch");

        assertFalse(results.isEmpty());

        System.out.printf("There are %d results\n", results.size());
        for (String result : results) {
            System.out.println(result);
        }
    }

    @Test
    public void testWorldScratch() {
        List<String> results = wordFinder.findCandidates(
                "qweryuopfgjklzxcvm".chars().mapToObj(e -> (char) e).collect(Collectors.toList()),
                "_o_u_");

        assertFalse(results.isEmpty());

        System.out.printf("There are %d results\n", results.size());
        for (String result : results) {
            System.out.println(result);
        }
    }

    @Test
    public void testWorldHomeworkCandidate() {
        List<String> results = wordFinder.findCandidates(
                "qwertypafghjklzxcvbn".chars().mapToObj(e -> (char) e).collect(Collectors.toList()),
                "_rea_");

        assertFalse(results.isEmpty());

        System.out.printf("There are %d results\n", results.size());
        for (String result : results) {
            System.out.println(result);
        }
    }

    @Test
    public void testWordleSolved() {
        String answer = "puppy";

        List<String> results = wordFinder.findCandidates(
                "qwryupfghjklzxcvbm".chars().mapToObj(e -> (char) e).collect(Collectors.toList()),
                answer);

        assertEquals(results, List.of(answer));
    }

    @Test
    public void testBigWerds() {
        final String answer = "decussate";

        List<String> results = wordFinder.findCandidates(
                "qwyuofghjdkzxvb".chars().mapToObj(e -> (char) e).collect(Collectors.toList()),
                "_ec_ssate");

        assertTrue(results.contains(answer));
    }

    @Test
    public void testAllWords() {
        String candidates = "abcdefghijklmnopqrstuvwxyz";
        String template = "_";
        int maxLen = 7;

        for(int i = 0; i < maxLen; i++) {
            Stopwatch stopwatch = Stopwatch.createStarted();
            List<String> results = wordFinder.findCandidates(
                    candidates.chars().mapToObj(e -> (char) e).collect(Collectors.toList()),
                    template);
            stopwatch.stop();

            System.out.printf("Length = %d, %d ms\n", template.length(), stopwatch.elapsed(TimeUnit.MILLISECONDS));

            assertFalse(results.isEmpty());

            template = template + "_";
        }
    }

    static final int COUNT = 1000;

    @Test
    public void timeIt() {
        Stopwatch timeIt = Stopwatch.createStarted();
        String candidates = "qweyuofghjkzxvb";


        for (int i = 0; i < COUNT; i++) {
            wordFinder.findCandidates(
                    candidates.chars().mapToObj(e -> (char) e).collect(Collectors.toList()),
                    "_ou_e");
        }

        System.out.printf("That took %d ms\n", timeIt.elapsed().toMillis());
    }
}
