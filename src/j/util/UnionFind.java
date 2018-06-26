package j.util;

/**
 * Created by j on 2017/9/15.
 */
public class UnionFind {
    int[] parent;
    int size;

    public UnionFind(int n) {
        if (n > 0) {
            size = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
    }

    public void union(int a, int b) {
        if (!check(a) || !check(b)) return;
        int pa = find(a);
        int pb = find(b);
        if (pa != pb) parent[pb] = pa;
        return;
    }

    public int find(int x) {
        if (!check(x)) return -1;
        int p = x;
        while (parent[p] != p) p = parent[p];
        return p;
    }

    private boolean check(int x) {
        return x >= 0 && x < size;
    }
}
