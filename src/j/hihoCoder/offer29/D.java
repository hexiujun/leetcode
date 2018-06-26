package j.hihoCoder.offer29;

import java.util.*;

/**
 * Created by j on 2017/10/1.
 */
public class D {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String s = in.next();
        if (s.length() == 0) {
            System.out.println(0);
            in.close();
            return;
        }

        int ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        char[] str = s.toCharArray();
        int index = 0;
        while (index < str.length) {
            int j = index + 1;
            char c = str[index];
            while (j < str.length && str[j] == str[index]) j++;

            if (map.containsKey(c)) {
                int num = map.get(c);
                if (num < j - index) {
                    ans += j - index - num;
                    map.put(c, j - index);
                }
            } else {
                ans += j - index;
                map.put(c, j - index);
            }

            int p = index - 1, q = j;
            while (p >= 0 && q < str.length) {
                if (str[p] == str[q]) {
                    set.add(String.valueOf(str, p, q - p + 1));
                    p--;
                    q++;
                } else {
                    break;
                }
            }

            index = j;
        }

        System.out.println(set.size() + ans);

        in.close();
    }
}
