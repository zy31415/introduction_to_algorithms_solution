package clrs.ch32.e2.q2;

import clrs.ch32.e2.Hasher;
import com.google.common.collect.ImmutableList;

import java.util.*;

import static java.lang.Math.floorMod;

public class RabinKarpMultiplePatternsMatcher extends Hasher {

//    // Use ascii code.
//    int base = 128;
//    int modulus = 15485863;

    private Integer [] rollingHash;
    private Map<Integer, Integer> targetHash;
    private int [] M;  // Lengths of all patterns.
    private int [] D; // power of the base to the length of the pattern

    private List<Integer> [] results;

    public RabinKarpMultiplePatternsMatcher() {}

    public RabinKarpMultiplePatternsMatcher(int modulus) {
        setModulus(modulus);
    }

    private void setModulus(int modulus) {
        if (modulus <= 0) throw new IllegalStateException("Modulus are not set (correctly).");
        this.modulus = modulus;
    }

    public boolean match(String T, List<String> L) {
        // preprocessing
        initializeRollingHash(T, L);
        initializeTargetHash(L);

        results = (ArrayList<Integer>[]) new ArrayList[L.size()];

        int n = T.length();

        // Matching
        for(int s = 0; s < n; s++) {
            for (Integer h : rollingHash) {

                if ( h == null)  // Rolling hash is not available the pattern
                    continue;

                if (targetHash.containsKey(h)) {
                    int i = targetHash.get(h);
                    String targetString = L.get(i);
                    if(verifyMatched(T, s, targetString)) {
                        System.out.printf("Find match for \'%s\' at %d.\n", targetString, s);
                        if (results[i] == null) {
                            results[i] = new ArrayList<Integer>();
                        }
                        results[i].add(s);
                    }
                }
            }

            // Roll the hash
            rollHash(T, s, L);
        }

        return true;
    }

    void initializeRollingHash(String T, List<String> L) {
        int r = L.size();
        rollingHash = new Integer [r];
        M = new int [r];
        D = new int [r];

        // Use a priority queue to remember the length of each pattern.
        PriorityQueue<List<Integer>> heap = new PriorityQueue<>(
                (List<Integer> l1, List<Integer> l2) -> l1.get(0).compareTo(l2.get(0))
        );

        for (int i = 0; i < r; i++) {
            int m = L.get(i).length();
            M[i] = m;
            heap.add(ImmutableList.of(m - 1, i));
        }

        int h = 0;
        int d = 1;

        // Calculate the initial hash value in one pass
        for (int index = 0; index < T.length(); index ++) {

            if (heap.isEmpty()) // All calculation is done.
                break;

            // Calculate the hash
            h = (h * base + hash(T.charAt(index))) % modulus;
            d = index == 0 ? 1 : (d * base) % modulus;

            if (index == heap.peek().get(0)) {
                int i = heap.poll().get(1);
                rollingHash[i] = h;
                D[i] = d;
            }
        }
    }

    void initializeTargetHash(List<String> L) {
        int r = L.size();
        targetHash = new HashMap<>();

        for (int index = 0; index < r; index++) {
            int h = 0;
            String s = L.get(index);
            int m = s.length();
            for (int j = 0; j < m; j++) {
                h = (h * base + hash(s.charAt(j))) % modulus;
            }
            targetHash.put(h, index);
        }
    }

//    private int hash(char c) {
//        return (int) c;
//    }

    private boolean verifyMatched(String T, int s, String P) {
        for (int i = 0; i < P.length(); i++) {
            if (T.charAt(s + i) != P.charAt(i)) return false;
        }
        return true;
    }

    void rollHash(String T, int s, List<String> L) {
        for (int i = 0; i < rollingHash.length; i++) {
            int m = M[i];
            int end = s + m;

            if (rollingHash[i] == null || end >= T.length()) {
                rollingHash[i] = null;
            } else {
                int r = (rollingHash[i] - hash(T.charAt(s)) * D[i]) % modulus;
                rollingHash[i] = floorMod(
                        base * r + hash(T.charAt(end)),
                        modulus);
            }
        }
    }

    public List<Integer>[] getResults() {
        return results;
    }
}
