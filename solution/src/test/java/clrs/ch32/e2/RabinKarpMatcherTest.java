package clrs.ch32.e2;

import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.*;

public class RabinKarpMatcherTest {

    @Test
    public void test_front_match() {
        RabinKarpMatcher matcher = new RabinKarpMatcher(RabinKarpMatcher.DecimalDigits, 101);
        assertTrue(matcher.match("1234", "12"));
        assertEquals(1, matcher.numMatchedPos());
    }


    @Test
    public void test_end_match() {
        RabinKarpMatcher matcher = new RabinKarpMatcher(RabinKarpMatcher.DecimalDigits, 101);
        assertTrue(matcher.match("1234", "34"));
        assertEquals(1, matcher.numMatchedPos());
    }

    @Test
    public void test_front_match_long_P() {
        RabinKarpMatcher matcher = new RabinKarpMatcher(RabinKarpMatcher.DecimalDigits, 101);
        assertTrue(matcher.match("1234567", "1234"));
        assertEquals(1, matcher.numMatchedPos());
    }

    @Test
    public void test_end_match_long_P() {
        RabinKarpMatcher matcher = new RabinKarpMatcher(RabinKarpMatcher.DecimalDigits, 101);
        assertTrue(matcher.match("1234", "234"));
        assertEquals(1, matcher.numMatchedPos());
    }

    @Test
    public void test_spurious_hit() {
        RabinKarpMatcher matcher = new RabinKarpMatcher(RabinKarpMatcher.DecimalDigits,11);
        assertTrue(matcher.match("3141592653589793", "26"));
        assertEquals(1, matcher.numMatchedPos());
        assertEquals(6, (int) matcher.getMatchedPos().get(0));
        assertEquals(3, matcher.getNumSpuriousHits());
    }

    @Test
    public void test_letters() {
        RabinKarpMatcher matcher = new RabinKarpMatcher(RabinKarpMatcher.LowerCaseLetters, 15485863);
        assertTrue(matcher.match("adlfkwoidjfkasdfkjalskdfiejfkdjfalskdjfaksdfadvdkdickslsidkf", "als"));
        assertEquals(2, matcher.numMatchedPos());
        assertEquals(18, (int) matcher.getMatchedPos().get(0));
        assertEquals(0, matcher.getNumSpuriousHits());
    }

    @Test(expected = InvalidParameterException.class)
    public void test_no_key_exception() {
        RabinKarpMatcher matcher = new RabinKarpMatcher(RabinKarpMatcher.LowerCaseLetters, 15485863);
        matcher.match("123", "1");
    }

    @Test
    public void test_default_ascii() {
        RabinKarpMatcher matcher = new RabinKarpMatcher(15485863);
        assertTrue(matcher.match("~abAB12!@##()[].", "."));
    }

    @Test
    public void test_ascii_prod_overflow() {
        RabinKarpMatcher matcher = new RabinKarpMatcher(15485863);
        assertTrue(matcher.match("aworld", "world"));
    }

}
