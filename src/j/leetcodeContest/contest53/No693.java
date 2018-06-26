package j.leetcodeContest.contest53;

/**
 * Created by j on 2017/10/8.
 */
public class No693 {
    public boolean hasAlternatingBits(int n) {
        int i = 30;
        while (i >= 0 && ((1 << i) & n) == 0) i--;

        boolean tag = true;
        while (i >= 0 && (((1 << i) & n) > 0) == tag) {
            i--;
            tag = !tag;
        }

        return i < 0;
    }
}
