package sjhlko.week41.BOJ_1972;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Surprise {
    //https://www.acmicpc.net/problem/1972
    //놀라운 문자열
    static String OK = " is surprising.\n", NO = " is NOT surprising.\n";

    static String solution(String s) {
        for (int i = 1; i <= s.length()-2; i++) {
            HashSet<String> visited = new HashSet<>();
            for (int j = 0; j < s.length()-i; j++) {
                String now = s.charAt(j) + "" + s.charAt(j+i);
                if (visited.contains(now)) return s + NO;
                visited.add(now);
            }
        }
        return s + OK;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String s = bf.readLine();
            if(s.equals("*")) break;
            sb.append(solution(s));
        }
        System.out.println(sb);
    }
}
