package clrs.ch32.e1.q4;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class MatchWithGapTest_Main {

    @Test
    public void test_no_star() {
        assertTrue(GapCharStringMatcher.isMatch("abcdfg", "abcd"));
    }

    @Test
    public void test_no_star_empty_P() {
        assertTrue(GapCharStringMatcher.isMatch("abcdfg", ""));
    }

    @Test
    public void test_star_1() {
        assertTrue(GapCharStringMatcher.isMatch("abcdfg", "ab*cd"));
    }

    @Test
    public void test_star_2() {
        assertFalse(GapCharStringMatcher.isMatch("abcdfg","ab*cx"));
    }

    @Test
    public void test_star_3() {
        assertTrue(GapCharStringMatcher.isMatch("abcdfg", "ab*fg"));
    }

    @Test
    public void test_two_stars_1() {
        assertTrue(GapCharStringMatcher.isMatch("abcdfghi", "ab*d*i"));
    }

    @Test
    public void test_two_stars_2() {
        assertFalse(GapCharStringMatcher.isMatch("abcdfghi", "ab*d*x"));
    }

    @Test
    public void test_two_stars_3() {
        assertTrue(GapCharStringMatcher.isMatch("cabccbacbacab", "ab*ba*c"));
    }

    @Test
    public void test_only_one_star() {
        assertTrue(GapCharStringMatcher.isMatch("abc", "*"));
    }
}
