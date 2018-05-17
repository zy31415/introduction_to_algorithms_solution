package clrs.ch32.e1.q4;

import org.junit.Test;
import static org.junit.Assert.*;


public class WildcardMatcherTest {
    @Test
    public void test_one_wildcard() {
        assertTrue(WildcardMatcher.isMatch("a", "*"));
    }

    @Test
    public void test_no_wildcard() {
        assertTrue(WildcardMatcher.isMatch("a", "a"));
    }

    @Test
    public void test_no_wildcard_false() {
        assertFalse(WildcardMatcher.isMatch("abc", "a"));
    }

    @Test
    public void test_star_end() {
        assertTrue(WildcardMatcher.isMatch("ab", "a*"));
    }

    @Test
    public void test_star_middle() {
        assertTrue(WildcardMatcher.isMatch("absst", "a*t"));
    }

    @Test
    public void test_star_front() {
        assertTrue(WildcardMatcher.isMatch("absst", "*st"));
    }

    @Test
    public void test_empty_pattern_false() {
        assertFalse(WildcardMatcher.isMatch("absst", ""));
    }

    @Test
    public void test_empty_pattern_true() {
        assertTrue(WildcardMatcher.isMatch("", ""));
    }

    @Test
    public void test_empty_string_true() {
        assertTrue(WildcardMatcher.isMatch("", "*"));
    }

    @Test
    public void test_empty_string_two_stars_true() {
        assertTrue(WildcardMatcher.isMatch("", "**"));
    }

    @Test
    public void test_empty_string_false() {
        assertFalse(WildcardMatcher.isMatch("", "*a"));
    }

    @Test
    public void test_two_stars() {
        assertTrue(WildcardMatcher.isMatch("asdefxyz", "a*e*z"));
    }

    @Test
    public void test_two_stars_front_end() {
        assertTrue(WildcardMatcher.isMatch("asdefxyz", "*fx*"));
    }
}
