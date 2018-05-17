package clrs.ch32.e1.q4;

public class GapCharStringMatcher {

    public static boolean isMatch(String T, String P) {
        return isMatch(T, 0, P, 0);
    }

    static boolean isMatch(String T, int s0, String P, int t) {
        int n = T.length();
        int m = P.length();

        if(s0 == n) {
            if (t >= m) {
                return true;
            }

            if (P.charAt(t) == '*') {
                return isMatch(T, s0, P, t+1);
            }

            return false;
        }

        for (int s = s0; s < n; s++) {
            boolean matched = true;

            for (int i = 0; t + i < m; i++) {
                if (P.charAt(t+i) == '*') {
                    return isMatch(T, s0 + i, P, t + i + 1);
                }

                if (s + i >= n || (T.charAt(s+i) != P.charAt(t+i))) {
                    matched = false;
                    break;
                }
            }

            if (matched) {
                return true;
            }

        }

        return false;
    }


}

