package clrs.ch32.e2;

import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.*;

public class RabinKarpMatcherTest {

    @Test
    public void test_front_match() {
        char [] alphabets = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        RabinKarpMatcher matcher = new RabinKarpMatcher.Builder().setAlphabets(alphabets).setModulus(101).build();
        assertTrue(matcher.match("1234", "12"));
        assertEquals(1, matcher.numMatchedPos());
    }

    @Test
    public void test_end_match() {
        char [] alphabets = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        RabinKarpMatcher matcher = new RabinKarpMatcher.Builder().setAlphabets(alphabets).setModulus(101).build();
        assertTrue(matcher.match("1234", "34"));
        assertEquals(1, matcher.numMatchedPos());
    }

    @Test
    public void test_front_match_long_P() {
        char [] alphabets = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        RabinKarpMatcher matcher = new RabinKarpMatcher.Builder().setAlphabets(alphabets).setModulus(101).build();
        assertTrue(matcher.match("1234567", "1234"));
        assertEquals(1, matcher.numMatchedPos());
    }

    @Test
    public void test_end_match_long_P() {
        char [] alphabets = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        RabinKarpMatcher matcher = new RabinKarpMatcher.Builder().setAlphabets(alphabets).setModulus(101).build();
        assertTrue(matcher.match("1234", "234"));
        assertEquals(1, matcher.numMatchedPos());
    }

    @Test
    public void test_spurious_hit() {
        RabinKarpMatcher matcher = new RabinKarpMatcher.Builder().setAlphabets(RabinKarpMatcher.Builder.DecimalDigits)
                .setModulus(11).build();
        assertTrue(matcher.match("3141592653589793", "26"));
        assertEquals(1, matcher.numMatchedPos());
        assertEquals(6, (int) matcher.getMatchedPos().get(0));
        assertEquals(3, matcher.getNumSpuriousHits());
    }

    @Test
    public void test_letters() {
        RabinKarpMatcher matcher = new RabinKarpMatcher.Builder().setAlphabets(RabinKarpMatcher.Builder.LowerCaseLetters)
                .setModulus(15485863).build();
        assertTrue(matcher.match("adlfkwoidjfkasdfkjalskdfiejfkdjfalskdjfaksdfadvdkdickslsidkf", "als"));
        assertEquals(2, matcher.numMatchedPos());
        assertEquals(18, (int) matcher.getMatchedPos().get(0));
        assertEquals(0, matcher.getNumSpuriousHits());
    }

    @Test(expected = InvalidParameterException.class)
    public void test_no_key_exception() {
        RabinKarpMatcher matcher = new RabinKarpMatcher.Builder().setAlphabets(RabinKarpMatcher.Builder.LowerCaseLetters)
                .setModulus(15485863).build();
        matcher.match("123", "1");
    }

    @Test
    public void test_default_ascii() {
        RabinKarpMatcher matcher = new RabinKarpMatcher.Builder().setModulus(15485863).build();
        assertTrue(matcher.match("~abAB12!@##()[].", "."));
    }
}
