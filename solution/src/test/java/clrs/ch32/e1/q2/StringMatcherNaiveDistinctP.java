package clrs.ch32.e1.q2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringMatcherNaiveDistinctP {
    @Test
    public void test__naive_distinct_char() {
        int [] out = StringMatcher._naiveMatchDistinctP("abc", "a");
        assertEquals(1, out.length);
    }

    @Test
    public void test__naive_distinct_char_2() {
        int [] out = StringMatcher._naiveMatchDistinctP("abc", "ab");
        assertEquals(1, out.length);
    }

    @Test
    public void test__naive_distinct_char_3() {
        int [] out = StringMatcher._naiveMatchDistinctP("abcxy", "xy");
        assertEquals(1, out.length);
    }

    @Test
    public void test__naive_distinct_char_6() {
        int [] out = StringMatcher._naiveMatchDistinctP("abcxyz", "abc");
        assertEquals(1, out.length);
    }

    @Test
    public void test__naive_distinct_char_4() {
        int [] out = StringMatcher._naiveMatchDistinctP("xyabcxy", "xy");
        assertEquals(2, out.length);
    }

    @Test
    public void test__naive_distinct_char_5() {
        int [] out = StringMatcher._naiveMatchDistinctP("abcxa", "xy");
        assertEquals(0, out.length);
    }


}

