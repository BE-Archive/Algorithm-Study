package trankill1127.w41;

import java.io.*;
import java.util.*;

public class BOJ_1972 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String s = br.readLine();
        while (!s.equals("*")) {

            sb.append(s).append(" is");
            if (!check(s)) {
                sb.append(" NOT");
            }
            sb.append(" surprising.\n");

            s = br.readLine();
        }

        System.out.print(sb.toString());
    }

    public static boolean check(String s) {
        Set<String> set = new HashSet<>();

        for (int i = 1; i <= s.length() - 2; i++) {
            for (int j = 0; j < s.length() - i; j++) {
                String tmp = "" + s.charAt(j) + s.charAt(j + i);

                //System.out.println("============="+tmp);

                if (set.contains(tmp)) {
                    set.clear();
                    return false;
                }
                set.add(tmp);
            }

            set.clear();
        }

        return true;
    }
}
