package clrs.ch32.e2;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class Hasher {
    public static final char [] DecimalDigits = "0123456789".toCharArray();
    public static final char [] Letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    public static final char [] LowerCaseLetters = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    private int base = 128;
    private Map<Character, Integer> map = null;

    private int modulus = 15485863;

    void setAlphabets(char [] alphabets) {
        map = new HashMap<>();
        for (int i = 0; i < alphabets.length; i++) {
            map.put(alphabets[i], i);
        }

        base = map.size();
    }

    void setModulus(int modulus) {
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

}
