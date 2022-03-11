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
