import org.junit.Test;
import static org.junit.Assert.*;


public class MatchWithGapTest_Subroutine {

    @Test
    public void test_no_star_1() {
        assertTrue(GapCharStringMatcher.isMatch("abcde", 0, "abc", 0));
    }

    @Test
    public void test_no_star_2() {
        assertTrue(GapCharStringMatcher.isMatch("abcde", 0, "de", 0));
    }

    @Test
    public void test_no_star_3() {
        assertFalse(GapCharStringMatcher.isMatch("abcde", 0, "dx", 0));
    }

    @Test
    public void test_no_star_4() {
        assertTrue(GapCharStringMatcher.isMatch("abcde", 0, "abcde", 0));
    }

    @Test
    public void test_no_star_5() {
        assertFalse(GapCharStringMatcher.isMatch("ab", 0, "abc", 0));
    }

    @Test
    public void test_non_zero_start_1() {
        assertTrue(GapCharStringMatcher.isMatch("abcd", 2, "ab*cd", 3));
    }

    @Test
    public void test_non_zero_start_2() {
        assertTrue(GapCharStringMatcher.isMatch("abxycd", 2, "ab*cd", 3));
    }

    @Test
    public void test_non_zero_start_3() {
        assertTrue(GapCharStringMatcher.isMatch("abcdfg", 2, "ab*cd", 3));
    }

    @Test
    public void test_non_zero_start_4() {
        assertFalse(GapCharStringMatcher.isMatch("abcdfg", 2, "ab*cdx", 3));
    }

    @Test
    public void test_non_zero_start_5() {
        assertFalse(GapCharStringMatcher.isMatch("abcd", 2, "ab*cdx", 3));
    }
}
