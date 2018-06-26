package j.hihoCoder.problemset;

/**
 * Created by j on 2017/12/24.
 */
import java.util.Scanner;

public class RectangleJudge {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        while(T-- > 0) {
            Segment[] segments = new Segment[4];
            for(int i = 0; i < 4; i++) {
                segments[i] = new Segment(new Point(in.nextInt(), in.nextInt()),
                        new Point(in.nextInt(), in.nextInt()));
            }

            boolean res = true;
            for (Segment segment : segments) {
                if (segment.isDot()) {
                    res = false;
                    break;
                }
            }
            if (res) {
                int p1 = 0, p2 = 1, p3 = 2, p4 = 3;
                if (!segments[0].isParallel(segments[1])) {
                    if (segments[0].isParallel(segments[2])) {
                        p2 = 2; p3 = 1;
                    } else if (segments[1].isParallel(segments[2])) {
                        p1 = 1; p2 = 2; p3 = 0;
                    } else {
                        res = false;
                    }
                }
                if (!res) {System.out.println("NO"); continue;}

                if (segments[p1].sizeSquare() == segments[p2].sizeSquare()) {
                    res = segments[p1].isVertical(segments[p3]) && segments[p1].isVertical(segments[p4]) &&
                            (segments[p1].contains(segments[p3].start) || segments[p2].contains(segments[p3].start)) &&
                            (segments[p1].contains(segments[p3].end) || segments[p2].contains(segments[p3].end)) &&
                            (segments[p1].contains(segments[p4].start) || segments[p2].contains(segments[p4].start)) &&
                            (segments[p1].contains(segments[p4].end) || segments[p2].contains(segments[p4].end));
                } else res = false;
            }

            System.out.println(res ? "YES" : "NO");
        }

        in.close();
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isEqual(Point another) {
            return x == another.x && y == another.y;
        }
    }

    static class Segment {
        Point start;
        Point end;

        public Segment(Point start, Point end) {
            this.start = start;
            this.end = end;
        }

        public boolean isDot() {
            return start.isEqual(end);
        }

        public long sizeSquare() {
            return ((long)start.x - end.x) * (start.x - end.x) + ((long)start.y - end.y) * (start.y - end.y);
        }

        public boolean isVertical(Segment another) {
            return 0 == ((long)start.x - end.x) * (another.start.x - another.end.x) + ((long)start.y - end.y) * (another.start.y - another.end.y);
        }

        public boolean isParallel(Segment another) {
            return ((long)start.x - end.x) * (another.start.y - another.end.y) == ((long)start.y - end.y) * (another.start.x - another.end.x);
        }

        public boolean contains(Point point) {
            return point.isEqual(start) || point.isEqual(end);
        }
    }
}
