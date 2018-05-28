package clrs.ch32.e2.q3;

import clrs.ch32.e2.Hasher;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.floorMod;


public class RabinKarpMatrixMatcher extends Hasher {

    char [][] T;
    char [][] P;
    int n;  // size of T, the matrix string.
    int m;  // size of P, the matrix pattern.
    int p;  // hash of the pattern.
    int t0; // hash of the first block at the beginning of rows.
    int t; // hash of current block checking;

    int base_m = 1;
    int base_m_1 = 1;

    List<List<Integer>> results;

    public boolean match(char [][] T, char [][] P) throws IllegalArgumentException {
        checkInputMatrices(T, P);

        // preprocessing:
        p = 0;
        t0 = 0;

        for (int s = 0; s < m * m; s++) {
            int u = s / m;
            int v = s % m;
            p = (base * p + hash(P[u][v])) % modulus;
            t0 = (base * t0 + hash(T[u][v])) % modulus;
        }

        for (int i = 0; i < m - 1; i++) {
            base_m_1 *= base;
        }
        base_m = base_m_1 * base;

        results = new ArrayList<>();

        // matching and rolling
        for (int i = 0; i <= n - m; i++) {
            t = t0;
            for (int j =0 ; j <= n - m; j++) {
                if (t == p) {
                    if (isMatched(i, j)) {
                        System.out.printf("Found match at (%d, %d).\n", i, j);
                        results.add(ImmutableList.of(i, j));
                    }
                }
                rollRight(i, j);
            }
            rollDown(i);
        }
        return true;
    }

    void checkInputMatrices(char [][] T, char [][] P) throws IllegalArgumentException {
        if (!isSquareMatrix(T) || !isSquareMatrix(P))
            throw new IllegalArgumentException("Input matrix must be square.");

        this.T = T;
        this.P = P;

        n = T.length;
        m = P.length;
    }

    static boolean isSquareMatrix(char [][] matrix) {
        int n = matrix.length;

        for (char [] row : matrix) {
            if (row.length != n)
                return false;
        }
        return true;
    }

    boolean isMatched(int i, int j) {
        for (int u = 0; u < m; u++) {
            for (int v = 0; v < m; v++) {
                if (T[i+u][j+v] != P[u][v])
                    return false;
            }
        }
        return true;
    }

    void rollRight(int i, int j) {
        if (j + m >= n) // At the end of the rows. Cannot roll right.
            return;

        int dt = 0;
        for (int u = 0; u < m; u++) {
            dt = (dt * base_m + hash(T[i+u][j])) % modulus;
        }

        dt =  (base_m_1 * dt) % modulus;

        t = floorMod(t - dt, modulus); // remove

        t = (t * base) % modulus; // roll

        dt = 0;
        for (int u = 0; u < m ; u++) {
            dt = (dt * base_m + hash(T[i+u][j+m])) % modulus;
        }

        t = (t + dt) % modulus;
    }

    void rollDown(int i) {
        if ( i + m >= n) {
            return;
        }

        int dt = 0;
        for (int v = 0; v < m; v++) {
            dt = (dt * base + hash(T[i][v])) % modulus;
        }

        dt = (dt * base_m) % modulus;

        t0 = floorMod(t0 - dt, modulus); // remove
        t0 = (t0 * base_m) % modulus; // roll

        dt = 0;
        for (int v = 0; v < m; v++) {
            dt = (dt * base + hash(T[i+m][v])) % modulus;
        }

        t0 = (t0 + dt) % modulus;
    }
}
