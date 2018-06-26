package j.examination.xiaohongshu;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by j on 2017/10/13.
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        char[] str = s.toCharArray();
        boolean[] omit = new boolean[str.length];
        for (int i = 0; i < str.length - 2;i++) {
            if (!omit[i] && str[i] == 'R' && str[i + 1] == 'E' && str[i + 2] == 'D') {
                omit[i++] = true;
                omit[i++] = true;
                omit[i] = true;
            }
        }

        BigInteger max = new BigInteger("-1");
        for (int i = 0; i < str.length; i++) {
            if (!omit[i] && str[i] >= '0' && str[i] <= '9') {
                StringBuilder buf = new StringBuilder();
                while (i < str.length) {
                    if (omit[i]) {
                        i++;
                        continue;
                    }

                    if (str[i] >= '0' && str[i] <= '9') {
                        buf.append(str[i]);
                        i++;
                    } else {
                        break;
                    }
                }

                BigInteger integer = new BigInteger(buf.toString());
                if (integer.compareTo(max) > 0)
                    max = integer;
            }
        }

        System.out.println(max.toString());
    }
}
