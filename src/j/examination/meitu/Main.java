package j.examination.meitu;

import java.util.Scanner;

/**
 * Created by j on 2017/10/12.
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String line = in.nextLine();
            String[] values = line.split(",");
            int n = Integer.parseInt(values[0]);
            int m = Integer.parseInt(values[1]);

            WeightedQuickUnionPC unionSet = new WeightedQuickUnionPC(n);
            boolean ownCircle = false;
            int[][] matrix = new int[n][n];
            for (int i = 0; i < m; i++) {
                line = in.nextLine();
                values = line.split(",");
                int s = Integer.parseInt(values[0]);
                int t = Integer.parseInt(values[1]);
                int d = Integer.parseInt(values[2]);
                matrix[s][t] = d;
                matrix[t][s] = d;

                if (!ownCircle) {
                    if (unionSet.connected(s, t)) {
                        ownCircle = true;
                        break;
                    } else {
                        unionSet.union(s, t);
                    }
                }
            }

            if (ownCircle) {
                System.out.println("YES");
            } else {
                System.out.println(0);
            }
        }
        in.close();
    }
}

class WeightedQuickUnionPC {
    private int[] id;
    private int[] size;

    public WeightedQuickUnionPC(int N) {
        id = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];  // path compression
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (size[i] > size[j]) {
            id[j] = i;
            size[i] += size[j];
        } else {
            id[i] = j;
            size[j] += size[i];
        }
    }
}
