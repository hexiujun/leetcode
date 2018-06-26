package j.hihoCoder.offer30;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by j on 2017/10/8.
 */
public class A {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        char[] str = line.toCharArray();

        List<String> users = new ArrayList<>();
        int index = 0;
        while (index < str.length) {
            if (str[index] != '@') index++;
            else {
                int j = index + 1;
                while (j < str.length && isCharacter(str[j])) j++;
                if (j - index > 1)
                    users.add(String.valueOf(str, index + 1, j - index - 1));
                index = j;
            }
        }

        for (int i = 0; i < users.size(); i++) {
            if (i == 0)
                System.out.print(users.get(i));
            else
                System.out.print(" " + users.get(i));
        }
        System.out.println();
        in.close();
    }

    static boolean isCharacter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
}
