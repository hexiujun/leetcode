package j.hash;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by j on 2017/3/23.
 */
public class StringClassify {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] strings = new String[n];
        for (int i = 0; i < n; i++)
            strings[i] = in.next();

        HashSet<Feature> set = new HashSet<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            Feature feature = new Feature(strings[i]);
            if (!set.contains(feature)) {
                set.add(feature);
                res++;
            }
        }
        System.out.println("" + res);
    }
}
class Feature {
    int[] feature = new int[256];

    public Feature(String s) {
        for (int i = 0; i < s.length(); i++)
            feature[s.charAt(i)]++;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object comp) {
        if (this == comp) {
            return true;
        }

        if (comp instanceof Feature) {
            Feature antherFeature = (Feature)comp;
            for (int i = 0; i < 256; i++) {
                if (feature[i] != antherFeature.feature[i]) return false;
            }
            return true;
        }
        return false;
    }

}
