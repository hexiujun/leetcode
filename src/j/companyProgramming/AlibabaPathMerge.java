package j.companyProgramming;

import java.util.*;

/**
 * Created by j on 2017/6/25.
 */

public class AlibabaPathMerge {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in

        );
        List<UnilateralLine> lineList = new ArrayList<UnilateralLine>();
        while (scanner.hasNextLine()) {
            String[] options = scanner.nextLine().split(";");
            if (options.length < 5) {
                break;
            }
            lineList.add(new UnilateralLine(options[0], options[1], options[2], options[3], options[4], options[5]));
        }
        scanner.close();

        // wirte your code here
        List<String> result = calculateUnilateral(lineList);

        for (String str : result) {
            System.out.println(str);
        }
    }

    public static List<String> calculateUnilateral(List<UnilateralLine> lineList) {
        List<String> result = new ArrayList<String>();
        int n = lineList.size();
        boolean[] choosed = new boolean[n];

        for (int rule = 1; rule <= 4; rule++) {
            for (int i = 0; i < n; i++) {
                if (choosed[i]) continue;
                UnilateralLine a = lineList.get(i);
                for (int j = 0; j < n; j++) {
                    if (choosed[j] || j == i) continue;
                    UnilateralLine b = lineList.get(j);
                    if (rule == 1 || rule == 4) {
                        if ((rule == 1) ? matchRule1(a, b) : matchRule4(a, b)) {
                            result.add(a.getId() + "+" + b.getId());
                            choosed[i] = true;
                            choosed[j] = true;
                        }
                    } else {
                        for (int k = 0; k < n; k++) {
                            if (choosed[k] || k == i || k == j) continue;
                            UnilateralLine c = lineList.get(k);
                            if ((rule == 2) ? matchRule2(a, b, c) : matchRule3(a, b, c)) {
                                result.add(a.getId() + "+" + b.getId() + "+" + c.getId());
                                choosed[i] = true;
                                choosed[j] = true;
                                choosed[k] = true;
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    private static boolean matchRule1(UnilateralLine a, UnilateralLine b) {
        return ownSameType(a, b) && pathFollowAnother(a, b) && pathFollowAnother(b, a);
    }

    private static boolean matchRule2(UnilateralLine a, UnilateralLine b, UnilateralLine c) {
        return a.tType.equals("9.6m") && b.tType.equals("9.6m") && c.tType.equals("17.5m") &&
                ownSamePath(a, b) && pathFollowAnother(a, c) && pathFollowAnother(c, a);
    }

    private static boolean matchRule3(UnilateralLine a, UnilateralLine b, UnilateralLine c) {
        return ownSameType(a, b) && ownSameType(b, c) &&
                pathFollowAnother(a, b) && pathFollowAnother(b, c) && pathFollowAnother(c, a);
    }

    private static boolean matchRule4(UnilateralLine a, UnilateralLine b) {
        return pathFollowAnother(a, b) && a.sPro.equals(b.ePro);
    }

    private static boolean ownSamePath(UnilateralLine a, UnilateralLine b) {
        return a.sCen.equals(b.sCen) && a.sPro.equals(b.sPro) &&
                a.eCen.equals(b.eCen) && a.ePro.equals(b.ePro);
    }

    private static boolean pathFollowAnother(UnilateralLine a, UnilateralLine b) {
        return a.eCen.equals(b.eCen) && a.ePro.equals(b.ePro);
    }

    private static boolean ownSameType(UnilateralLine a, UnilateralLine b) {
        return a.tType.equals(b.tType);
    }

    public static class UnilateralLine {
        private String id;
        private String sCen;//出发分拨
        private String sPro;//出发省
        private String eCen;//到达分拨
        private String ePro;//到达省
        //9.6m/17.5m
        private String tType;//车型

        public UnilateralLine(String id, String sCen, String sPro, String eCen, String ePro, String tType) {
            this.id

                    = id;
            this.sCen = sCen;
            this.sPro = sPro;
            this.eCen = eCen;
            this.ePro = ePro;
            this.tType = tType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id

                    = id;
        }

        public String getSCen() {
            return sCen;
        }

        public void setSCen(String ePro) {
            this.ePro = ePro;
        }

        public String getSPro() {
            return sPro;
        }

        public void setSPro(String sPro) {
            this.sPro = sPro;
        }

        public String getECen() {
            return eCen;
        }

        public void setECen(String eCen) {
            this.eCen = eCen;
        }

        public String getEPro() {
            return ePro;
        }

        public void setEPro(String ePro) {
            this.ePro = ePro;
        }

        public String getTType() {
            return tType;
        }

        public void setTType(String tType) {
            this.tType = tType;
        }
    }
}


