package chidokun;

import java.util.Arrays;

/**
 * @author tuannlh
 */
public class Strings {

    public static String repeat(char c, int count) {
        char[] arr = new char[count];
        Arrays.fill(arr, c);
        return new String(arr);
    }
}