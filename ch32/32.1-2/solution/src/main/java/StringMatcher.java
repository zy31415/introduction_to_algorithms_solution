import com.google.common.collect.ImmutableList;
import com.google.common.primitives.ImmutableIntArray;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class StringMatcher {

	static public int [] naiveMatch(String T, String P) {
		int n = T.length();
		int m = P.length();

		List<Integer> res = new ArrayList<>();

		if (n >= m) {
			for (int s = 0; s < n - m + 1; s++) {
				if (isMatch(T, s, P)) {
					System.out.printf("Found match at index %d.\n", s);
					res.add(s);
				}
			}
		}

		return toPrimitiveArray(res);
	}

	/**
	 * If string P match T starting at index s of P.
	 */
	static private boolean isMatch(String T, int s, String P) {
		int n = T.length();
		int m = P.length();

		if (s<0 || s > n - m || s >= n) return false;

		for (int i = 0; i < m; i++) {
			if (T.charAt(s + i) != P.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	static private int [] toPrimitiveArray(List<Integer> in) {
		int [] out = new int[in.size()];

		for (int i = 0; i < in.size(); i++) {
			out[i] = in.get(i);
		}

		return out;
	}

	public static int [] naiveMatchDistinctP(String T, String P) {
		if (ifStringHasDistinctChar(P)) {
			return _naiveMatchDistinctP(T, P);
		}
		return naiveMatch(T, P);

	}

	static int [] _naiveMatchDistinctP(String T, String P) {
		int n = T.length();
		int m = P.length();

		List<Integer> res = new ArrayList<>();

		int s = 0;
		while (s < n - m + 1) {
			boolean isMatch = true;
			int i;
			for (i = 0; i < m; i++) {
				if (T.charAt(s + i) != P.charAt(i)) {
					isMatch = false;
					i++;
					break;
				}
			}

			if (isMatch) {
				System.out.printf("Found match at index %d.\n", s);
				res.add(s);
			}
			s += i;
		}

		return toPrimitiveArray(res);
	}

	private static boolean ifStringHasDistinctChar(String str) {
		Set<Character> counter = new HashSet<>();
		for (int i = 0; i < str.length(); i++) {
			Character c = str.charAt(i);
			if (counter.contains(c)) {
				return false;
			} else {
				counter.add(c);
			}
		}

		return true;
	}

	public static void matchWithGap(String T, String P) {

	}

	static List<List<Integer>> decompose(String P) {
		List<List<Integer>> couples = new ArrayList<>();

		int length = P.length();

		for (int from = 0; from < length; from++) {

			if (P.charAt(from) == '*') continue;

			for (int to = from + 1; to <= length; to++) {
				if (to == length || P.charAt(to) == '*') {
					couples.add(ImmutableList.of(from, to));
					from = to;
					break;
				}
			}

		}
		return couples;
	}

	static boolean isMatch(String T, int s, String P, int p) {
		int m = T.length();
		int n = P.length();

		for (int i = p; i < n; i++) {
			char c = P.charAt(i);
			if (c == '*') {
				return isMatch(T, s + i, P, i+1);
			}

			if (s+i >= m) {
				return true;
			}

			if (T.charAt(s + i - p) != c) {
				return false;
			}
		}
		return true;
	}
}