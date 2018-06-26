package j.codejam;

import java.io.*;

/**
 * Created by j on 2017/9/23.
 */
public class Main {
    public static void main(String[] args) {
        String fileName = "src\\j\\codejam\\A-small-practice.in";
        String outputFileName = "src\\j\\codejam\\A-small-practice.txt";
        String lineSeparator = System.getProperty("line.separator");
        File file = new File(fileName);
        File outFile = new File(outputFileName);
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            BufferedWriter out = new BufferedWriter(new FileWriter(outFile));
            StringBuilder buf = new StringBuilder();

            int t = Integer.parseInt(in.readLine());
            while (t-- > 0 && in.ready()) {
                String line = in.readLine();
                System.out.println(line);
                buf.append(line);
                buf.append(lineSeparator);
            }
            out.write(buf.toString());

            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
