package j.examination.toutiao;

import java.util.*;

/**
 * Created by j on 2017/9/10.
 */
public class UserLike {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] like = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            like[i] = in.nextInt();
        }
        int q = in.nextInt();
        int[][] search = new int[q][3];
        for (int i = 0; i < q; i++) {
            for (int j = 0; j < 3; j++) {   // l, r, k
                search[i][j] = in.nextInt();
            }
        }

        int[] res = new int[q];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < q; i++) {
            map.put(search[i][2], new ArrayList<>());
        }
        for (int i = 1; i <= n; i++) {
            if (map.containsKey(like[i]))
                map.get(like[i]).add(i);
        }
        for (int i = 0; i < q; i++) {
            List<Integer> list = map.get(search[i][2]);
            int l = Collections.binarySearch(list, search[i][0]);
            int r = Collections.binarySearch(list, search[i][1]);
            if (l == r) {
                res[i] = 0;continue;
            }
            if (r >= 0) res[i] = 1;
            if (l < 0) l = -l - 1;
            if (r < 0) r = -r - 1;
            res[i] += r - l;
        }

        for (int i = 0; i < q; i++) {
            System.out.println(res[i]);
        }
    }
}
