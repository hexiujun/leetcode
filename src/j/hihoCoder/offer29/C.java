package j.hihoCoder.offer29;

import java.util.*;

/**
 * Created by j on 2017/10/1.
 */
public class C {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = in.nextInt();
        }

        solve(data, m);

        in.close();
    }

    private static void solve(int[] data, int m) {
        Map<Integer, Integer> map = calcFreq(data);
        int[][] key = sortByValueWithFreq(map);
        int maxScore = dpSolution(key, m);

        System.out.println(maxScore);
    }

    private static int dpSolution(int[][] key, int m) {
        int[][] dp = new int[key.length][m + 1];
        dp[0][1] = key[0][0] * key[0][1];

        for (int i = 1; i < key.length; i++) {
            for (int j = 1; j < m + 1; j++) {
                int preIndex = i - 1;
                if (key[preIndex][0] + 1 == key[i][0]) preIndex--;

                int val = key[i][1] * key[i][0];
                if (preIndex >= 0) {
                    if (dp[preIndex][j - 1] != 0)
                        val += dp[preIndex][j - 1];
                    else
                        val = 0;
                }
                dp[i][j] = Math.max(dp[i - 1][j], val);
            }
        }

        return dp[dp.length - 1][m];
    }

    private static Map<Integer, Integer> calcFreq(int[] data) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < data.length; i++) {
            if (map.containsKey(data[i])) {
                map.put(data[i], map.get(data[i]) + 1);
            } else {
                map.put(data[i], 1);
            }
        }

        return map;
    }

    private static int[][] sortByValueWithFreq(Map<Integer, Integer> map) {
        int[][] key = new int[map.size()][2];
        //int[] freq = new int[map.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            key[index][0] = entry.getKey();
            key[index++][1] = entry.getValue();
        }
        Arrays.sort(key, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        return key;
    }
}
