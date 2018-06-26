package j.examination.perfectworld;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String time1 = in.nextLine();
        String time2 = in.nextLine();
        String[] str1 = time1.split("[/ :]");
        String[] str2 = time2.split("[/ :]");
        Calendar calendar1 = new GregorianCalendar();
        Calendar calendar2 = new GregorianCalendar();
        calendar1.set(parse(str1[0]), parse(str1[1]), parse(str1[2]),
                parse(str1[3]), parse(str1[4]), parse(str1[5]));
        calendar2.set(parse(str2[0]), parse(str2[1]), parse(str2[2]),
                parse(str2[3]), parse(str2[4]), parse(str2[5]));

        if (calendar1.compareTo(calendar2) > 0) {
            Calendar tmp;
            tmp = calendar1;
            calendar1 = calendar2;
            calendar2 = tmp;
        }

        long days = (calendar2.getTimeInMillis() - calendar1.getTimeInMillis()) / (1000 * 60 * 60 * 24);
        //System.out.println(days);
        //System.out.println(calendar2.get(Calendar.HOUR));

        long zero = 0, thursday = 0;
        zero = days;
        if (calendar2.get(Calendar.HOUR_OF_DAY) == 0 && calendar2.get(Calendar.MINUTE) == 0
                && calendar2.get(Calendar.SECOND) == 0)
            zero --;
        if (zero < 0) zero = 0;

        thursday = days / 7 - 1;
//        System.out.println(calendar2.get(Calendar.DAY_OF_WEEK));
//        if (calendar2.get(Calendar.DAY_OF_WEEK) > 5 && days % 7 != 0)
//            thursday++;
        System.out.println(zero + "," + thursday);
    }

    private static int parse(String intstr) {
        return Integer.parseInt(intstr);
    }
}

//2017/9/7 0:0:0
//2017/9/21 0:0:0