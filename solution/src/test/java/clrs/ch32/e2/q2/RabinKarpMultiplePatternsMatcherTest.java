package clrs.ch32.e2.q2;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class RabinKarpMultiplePatternsMatcherTest {

    @Test
    public void testInitializeRollingHash() {
        RabinKarpMultiplePatternsMatcher matcher = new RabinKarpMultiplePatternsMatcher();

        String T = "abcdef";
        List<String> L = ImmutableList.of("a", "abc");
        
        matcher.initializeRollingHash(T, L);

    }

    @Test
    public void testInitializeTargetHash() {
        RabinKarpMultiplePatternsMatcher matcher = new RabinKarpMultiplePatternsMatcher();

        List<String> L = ImmutableList.of("a", "abc");

        matcher.initializeTargetHash(L);
    }

    @Test
    public void name() throws Exception {
    }

    @Test
    public void test_one_string_front() {
        RabinKarpMultiplePatternsMatcher matcher = new RabinKarpMultiplePatternsMatcher();

        String T = "abcdef";
        List<String> L = ImmutableList.of("a");

        matcher.match(T, L);

        List<Integer> [] results = matcher.getResults();

        assertEquals(1, results.length);
        assertEquals(1, (int) results[0].size());
        assertEquals(0, (int) results[0].get(0));
    }

    @Test
    public void test_one_string_end() {
        RabinKarpMultiplePatternsMatcher matcher = new RabinKarpMultiplePatternsMatcher();

        String T = "abcdef";
        List<String> L = ImmutableList.of("f");

        matcher.match(T, L);

        List<Integer> [] results = matcher.getResults();

        assertEquals(1, results.length);
        assertEquals(1, (int) results[0].size());
        assertEquals(5, (int) results[0].get(0));
    }

    @Test
    public void test_one_string_middle() {
        RabinKarpMultiplePatternsMatcher matcher = new RabinKarpMultiplePatternsMatcher();

        String T = "abcdef";
        List<String> L = ImmutableList.of("d");

        matcher.match(T, L);

        List<Integer> [] results = matcher.getResults();

        assertEquals(1, results.length);
        assertEquals(1, (int) results[0].size());
        assertEquals(3, (int) results[0].get(0));
    }

    @Test
    public void test_one_string_multiple_times() {
        RabinKarpMultiplePatternsMatcher matcher = new RabinKarpMultiplePatternsMatcher();

        String T = "abcdefd";
        List<String> L = ImmutableList.of("d");

        matcher.match(T, L);

        List<Integer> [] results = matcher.getResults();

        assertEquals(1, results.length);
        assertEquals(2, results[0].size());
        assertEquals(3, (int) results[0].get(0));
        assertEquals(6, (int) results[0].get(1));
    }

    @Test
    public void test_two_strings() {
        RabinKarpMultiplePatternsMatcher matcher = new RabinKarpMultiplePatternsMatcher();

        String T = "abcdefd";
        List<String> L = ImmutableList.of("d", "de");

        matcher.match(T, L);

        List<Integer> [] results = matcher.getResults();

        assertEquals(2, results.length);
        assertEquals(2, results[0].size());
        assertEquals(3, (int) results[0].get(0));
        assertEquals(6, (int) results[0].get(1));

        assertEquals(1, results[1].size());
        assertEquals(3, (int) results[1].get(0));
    }

    @Test
    public void test_overflow() {
        RabinKarpMultiplePatternsMatcher matcher = new RabinKarpMultiplePatternsMatcher();

        String T = "aworl";
        List<String> L = ImmutableList.of("worl");

        matcher.match(T, L);

        List<Integer> [] results = matcher.getResults();

        assertEquals(1, results.length);
        assertEquals(1, results[0].size());

    }

    @Test
    public void test_three_paterns() {
        RabinKarpMultiplePatternsMatcher matcher = new RabinKarpMultiplePatternsMatcher();

        String T = "this is a wonderful world.";
        List<String> L = ImmutableList.of("is", "a", "world");

        matcher.match(T, L);

        List<Integer> [] results = matcher.getResults();

        assertEquals(3, results.length);
        assertEquals(2, results[0].size());

        assertEquals(1, results[1].size());

        assertEquals(1, results[2].size());

    }

}
