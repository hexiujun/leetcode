package j.examination.toutiao;

import java.util.*;

/**
 * Created by j on 2017/9/10.
 */
public class NumsOfLongestConsecutiveCharacter {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String s = in.next();
        int m = in.nextInt();

        Map<Character, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c))
                map.put(c, new ArrayList<Integer>());
            map.get(c).add(i);
        }

        int ans = 0;
        for (ArrayList<Integer> list : map.values()) {
            for (int i = 0; i < list.size(); i++) {
                int tmp = 0, calc = 1;
                int j = i - 1, k = i + 1;
                while (j >= 0 && k < list.size()) {
                    int addj = list.get(i) - list.get(j) - (i - j);
                    int addk = list.get(k) - list.get(i) - (k - i);
                    if (addj <= addk) {
                        tmp += addj;
                        j--;
                    } else {
                        tmp += addk;
                        k++;
                    }
                    if (tmp <= m) {
                        calc++;
                        ans = Math.max(ans, calc);
                    } else break;
                }
                while (j >= 0) {
                    tmp += list.get(i) - list.get(j) - (i - j);
                    j--;
                    if (tmp <= m) {
                        calc++;
                        ans = Math.max(ans, calc);
                    } else break;
                }
                while (k < list.size()) {
                    tmp += list.get(k) - list.get(i) - (k - i);
                    k++;
                    if (tmp <= m) {
                        calc++;
                        ans += Math.max(ans, calc);
                    } else break;
                }
            }
        }

        System.out.println(ans);
    }
}
