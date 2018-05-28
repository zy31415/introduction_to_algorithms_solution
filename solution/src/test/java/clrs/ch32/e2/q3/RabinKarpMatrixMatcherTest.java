package clrs.ch32.e2.q3;

import clrs.ch32.e2.Hasher;
import org.junit.Test;
import static org.junit.Assert.*;


public class RabinKarpMatrixMatcherTest {
    @Test
    public void testIsMatrix() {
        char [][] m1 = {{'a', 'b'}, {'c', 'd'}};
        assertTrue(RabinKarpMatrixMatcher.isSquareMatrix(m1));

        char [][] m2 = {{'a', 'b'}, {'c', 'd'}, {'e'}};
        assertFalse(RabinKarpMatrixMatcher.isSquareMatrix(m2));
    }

    @Test
    public void testMatch_begin() {
        char [][] P = {{'1', '2'}, {'3', '4'}};
        char [][] T = {{'1', '2', '5'}, {'3', '4', '6'}, {'7', '8', '9'}};

        RabinKarpMatrixMatcher matcher = new RabinKarpMatrixMatcher();
        matcher.setAlphabets(Hasher.DecimalDigits);

        matcher.match(T, P);
        assertEquals(1, matcher.results.size());
    }

    @Test
    public void testMatch_roll_right() {
        char [][] P = {{'2', '5'}, {'4', '6'}};
        char [][] T = {{'1', '2', '5'}, {'3', '4', '6'}, {'7', '8', '9'}};

        RabinKarpMatrixMatcher matcher = new RabinKarpMatrixMatcher();
        matcher.setAlphabets(Hasher.DecimalDigits);

        matcher.match(T, P);

        assertEquals(1, matcher.results.size());
    }

    @Test
    public void testMatch_roll_right_2() {
        char [][] P = {{'3', '4'}, {'7', '8'}};
        char [][] T = {{'1', '2', '3', '4'}, {'5', '6', '7', '8'}, {'9', '1', '2', '3'}, {'5', '7', '8', '9'}};

        RabinKarpMatrixMatcher matcher = new RabinKarpMatrixMatcher();
        matcher.setAlphabets(Hasher.DecimalDigits);

        matcher.match(T, P);

        assertEquals(1, matcher.results.size());
    }

    @Test
    public void testMatch_roll_down() {
        char [][] P = {{'4', '5'}, {'7', '8'}};
        char [][] T = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};

        RabinKarpMatrixMatcher matcher = new RabinKarpMatrixMatcher();
        matcher.setAlphabets(Hasher.DecimalDigits);

        matcher.match(T, P);

        assertEquals(1, matcher.results.size());
    }

    @Test
    public void testMatch() {
        char [][] P = {{'5', '6'}, {'8', '9'}};
        char [][] T = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};

        RabinKarpMatrixMatcher matcher = new RabinKarpMatrixMatcher();
        matcher.setAlphabets(Hasher.DecimalDigits);

        matcher.match(T, P);

        assertEquals(1, matcher.results.size());
    }

    @Test
    public void testMatch_ascii() {
        char [][] P = {
                {'k', 'i'},
                {'c', 'i'}
        };
        char [][] T = {
                "asdfg".toCharArray(),
                "qwert".toCharArray(),
                "zxcvb".toCharArray(),
                "akicj".toCharArray(),
                "kcijd".toCharArray()
        };

        RabinKarpMatrixMatcher matcher = new RabinKarpMatrixMatcher();

        matcher.match(T, P);

        assertEquals(1, matcher.results.size());
    }

    @Test
    public void testMatch_ascii_multiple_match() {
        char [][] P = {
                {'w', 'e'},
                {'x', 'c'}
        };
        char [][] T = {
                "asdfg".toCharArray(),
                "qwert".toCharArray(),
                "zxcvb".toCharArray(),
                "akiwe".toCharArray(),
                "kcixc".toCharArray()
        };

        RabinKarpMatrixMatcher matcher = new RabinKarpMatrixMatcher();
        matcher.match(T, P);
        assertEquals(2, matcher.results.size());
    }
}
