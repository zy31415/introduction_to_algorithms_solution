package clrs.ch32.e2;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.floorMod;

public class RabinKarpMatcher extends Hasher {

    public static final char [] DecimalDigits = "0123456789".toCharArray();
    public static final char [] Letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    public static final char [] LowerCaseLetters = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    private int base;
    private Map<Character, Integer> map = null;

    private int modulus;

    private List<Integer> matchedPos = null;

    private int numSpuriousHits;

    // constructors:
    public RabinKarpMatcher() {
        // use all ascii code as the alphabet set.
        map = null;
        base = 128;

        setModulus(15485863);
    }

    public RabinKarpMatcher(int modulus) {
        // use all ascii code as the alphabet set.
        map = null;
        base = 128;

        setModulus(modulus);
    }

    public RabinKarpMatcher(char [] alphabets, int modulus) {
        map = new HashMap<>();
        for (int i = 0; i < alphabets.length; i++) {
            map.put(alphabets[i], i);
        }

        base = map.size();
        setModulus(modulus);
    }

    public RabinKarpMatcher(Map<Character, Integer> map, int modulus) {
        if (map == null) {
            throw  new IllegalStateException("Map cannot be null.");
        }

        this.map = map;
        base = map.size();

        setModulus(modulus);
    }

    private void setModulus(int modulus) {
        if (modulus <= 0) throw new IllegalStateException("Modulus are not set (correctly).");
        this.modulus = modulus;
    }

    int hash(char c) throws InvalidParameterException {

        // if map is null, use ascii code of the character.
        if (map == null) {
            return (int) c;
        }

        if (!map.containsKey(c)) {
            throw new InvalidParameterException(String.format("Don't find key %s.", c));
        }
        return map.get(c);
    }

    public boolean match(String T, String P) {
        matchedPos = new ArrayList<>();
        numSpuriousHits = 0;

        int n = T.length();
        int m = P.length();

        if (m > n) return false;

        int h = 1;

        for (int i = 1; i < m; i++)
            h = (h * base) % modulus;

        int p = 0;
        int t = 0;

        for (int i = 0; i < m; i++) {
            p = (base * p + hash(P.charAt(i))) % modulus;
            t = (base * t + hash(T.charAt(i))) % modulus;
        }

        boolean matched = false;

        for (int s = 0; s <= n - m; s++) {
            if (p == t) {
                if (verifyMatched(T, s, P)) {
                    matched = true;
                    System.out.printf("Found match at %d\n", s);
                    matchedPos.add(s);
                } else {
                    numSpuriousHits++;
                }
            }

            // Rolling hash
            if (s < n-m) {
                // Use Math.floorMod to make sure that module is positive
                // User multiple steps to compute to prevent overflow:
                //  't - hash(T.charAt(s)) * h' could be very large thus module it before next step.
                int r = (t - hash(T.charAt(s)) * h) % modulus;
                t = floorMod((base * r + hash(T.charAt(s+m))), modulus);
            }
        }

        return matched;

    }

    private boolean verifyMatched(String T, int s, String P) {
        for (int i = 0; i < P.length(); i++) {
            if (T.charAt(s + i) != P.charAt(i)) return false;
        }
        return true;
    }

    public List<Integer> getMatchedPos() {
        return matchedPos;
    }

    public int numMatchedPos() {
        return matchedPos.size();
    }

    public int getNumSpuriousHits() {
        return numSpuriousHits;
    }
}
