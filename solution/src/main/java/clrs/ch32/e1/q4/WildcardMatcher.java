package clrs.ch32.e1.q4;

public class WildcardMatcher {

    // Dynamic Programming - used for memorization
    static int [][] memo;

    public static boolean isMatch(String T, String P) {
        int n = T.length();
        int m = P.length();

        initMemo(n, m);

        return isMatch(T, n, P, m);
    }

    static boolean isMatch(String T, int s, String P, int t) {

        // memo:
        if (memo[s][t] != -1)
            return memo[s][t] == 1;

        boolean res;

        // base case 1: when t == 0;
        if (t == 0) {
            res = (s == 0);
        }
        else if (P.charAt(t-1) == '*') {
            res = false;
            for (int i = s; i >= 0; i--) {
                res = res || isMatch(T, i, P, t-1);
            }
        }
        else {
            res = s != 0 && P.charAt(t-1) == T.charAt(s-1) && isMatch(T, s-1, P, t-1);
        }

        memo[s][t] = res ? 1 : 0;
        return res;
    }

    static void initMemo(int n, int m) {
        memo = new int[n+1][m+1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                memo[i][j] = -1;
            }
        }
    }
}
