package clrs.ch32.e2;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.floorMod;

public class RabinKarpMatcher extends Hasher {

    private List<Integer> matchedPos = null;

    private int numSpuriousHits;

    public RabinKarpMatcher(int modulus) {
        setModulus(modulus);
    }

    public RabinKarpMatcher(char [] alphabets, int modulus) {
        setAlphabets(alphabets);
        setModulus(modulus);
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
