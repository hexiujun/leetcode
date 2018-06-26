package j.hihoCoder.offer28;

import java.util.*;

/**
 * Created by j on 2017/9/24.
 * @linked https://hihocoder.com/contest/offers28/problem/3
 */
public class C_limitedConference {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        int M = in.nextInt();
        Interval[] se = new Interval[N];
        for (int i = 0; i < N; i++) {
            se[i] = new Interval(in.nextInt(), in.nextInt());
        }
        int Q = in.nextInt();
        Interval[] xy = new Interval[Q];
        for (int i = 0; i < Q; i++) {
            xy[i] = new Interval(in.nextInt(), in.nextInt());
        }

        List<Interval> fullTime = new ArrayList<>();
        PriorityQueue<Interval> queue = new PriorityQueue<>((a, b) -> (a.r - b.r));
        Arrays.sort(se);
        for (int i = 0; i < se.length; i++) {
            while (!queue.isEmpty() && queue.peek().r <= se[i].l)
                queue.poll();
            if (queue.isEmpty()) {
                if (queue.size() + 1 == M)
                    fullTime.add(se[i]);
                queue.add(se[i]);
            } else {
                Interval top = queue.peek();
                if (queue.size() + 1 == M)
                    fullTime.add(new Interval(se[i].l, Math.min(top.r, se[i].r)));
                queue.add(se[i]);
            }
        }

        Arrays.sort(xy);
        for (int i = 0; i < Q; i++) {
            Interval interval = xy[i];
            boolean tag = true;
            for (int j = 0; j < fullTime.size(); j++) {
                if (fullTime.get(j).r <= interval.l || fullTime.get(j).l >= interval.r)
                    continue;
                tag = false;
                break;
            }
            System.out.println(tag ? "YES" : "NO");
        }

        in.close();
    }

    static class Interval implements Comparable<Interval>{
        int l, r;

        public Interval(int l, int r) {
            this.l = l;
            this.r = r;
        }

        @Override
        public int compareTo(Interval o) {
            return (this.l == o.l) ? this.r - o.r : this.l - o.l;
        }
    }
}
