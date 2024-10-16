package trankill1127.w39;

import java.io.*;
import java.util.*;

public class BOJ_9996 {
    static String[] ps = null;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        String fullP = br.readLine().trim();
        ps = fullP.split("\\*");
        for (int i=0; i<N; i++){
            String fileName = br.readLine().trim();
            if (isPattern(fileName)) System.out.println("DA");
            else System.out.println("NE");
        }
    }

    public static boolean isPattern(String fileName){
        if (fileName.length()<ps[0].length()+ps[1].length()) return false;

        if (fileName.length()<ps[0].length()) return false;
        String l = fileName.substring(0,ps[0].length());
        if (!l.equals(ps[0])) return false;

        if (fileName.length()<ps[1].length()) return false;
        String r = fileName.substring(fileName.length()-ps[1].length(), fileName.length());
        if (!r.equals(ps[1])) return false;

        return true;
    }
}
