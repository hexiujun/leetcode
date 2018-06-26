package j.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by j on 2017/8/14.
 * code : utf-8
 */
public class KMP {
    /**
     *
     * @param t 匹配的模式串
     * @return 记录最长前缀的数组
     */
    public static int[] next(char[] t) {
        int[] next = new int[t.length];
        next[0] = -1;
        int i = 0;
        int j = -1;
        while (i < t.length - 1) {
            if (j == -1 || t[i] == t[j]) {
                i++;
                j++;
                if (t[i] != t[j]) {
                    next[i] = j;
                } else {
                    next[i] = next[j];
                }
            } else {
                j = next[j];
            }
        }
        return next;
    }

    /**
     *
     * @param s 待匹配的主串
     * @param t 匹配的模式串
     * @param s_start 从 s 开始匹配的位置下标
     * @return 模式串在主串中的头下标
     */
    public static int KMP_Index(char[] s, char[] t, int s_start) {
        int[] next = next(t);
        int i = s_start;
        int j = 0;
        while (i < s.length && j < t.length) {
            if (j == -1 || s[i] == t[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j < t.length) {
            return -1;
        } else
            return i - t.length; // 返回模式串在主串中的头下标
    }

    /**
     *
     * @param s 待匹配的主串
     * @param t 匹配的模式串
     * @return 所有在主串中匹配的位置下标的集合
     */
    public static List<Integer> getAllMatchedIndex(char[] s, char[] t) {
        List<Integer> list = new ArrayList<>();
        int s_start = 0;
        while (s_start < s.length) {
            int index = KMP_Index(s, t, s_start);
            if (index == -1) break;
            list.add(index);
            s_start = index + t.length;
        }
        return list;
    }

    public static void main(String[] args) {
        String s = "onetwoonethreesixoneonbcneoabneone";
        String pattern = "one";
        System.out.println(KMP.getAllMatchedIndex(s.toCharArray(), pattern.toCharArray()));
    }
}
