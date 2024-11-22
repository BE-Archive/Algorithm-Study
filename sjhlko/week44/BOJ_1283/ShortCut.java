package sjhlko.week44.BOJ_1283;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class ShortCut {
    //https://www.acmicpc.net/problem/1283
    //단축키 지정
    static Set<Character> keys = new HashSet<>();

    static char changeCase(char c) {
        if (c >= 'a' && c <= 'z') return (char) (c - 32);
        return (char) (c + 32);
    }

    static String solution(String[] options) {
        boolean flag = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < options.length; i++) {
            String option = options[i];
            if (!flag && !keys.contains(option.charAt(0))) {
                keys.add(option.charAt(0));
                keys.add(changeCase(option.charAt(0)));
                sb.append('[')
                  .append(option.charAt(0))
                  .append(']');
                sb.append(option.substring(1));
                sb.append(" ");
                flag = true;
                continue;
            }
            sb.append(option)
              .append(" ");
        }
        if(flag) return sb.toString();


        sb = new StringBuilder();
        flag = false;
        for (int i = 0; i < options.length; i++) {
            for (int j = 0; j < options[i].length(); j++) {
                if (!flag && !keys.contains(options[i].charAt(j))) {
                    keys.add(options[i].charAt(j));
                    keys.add(changeCase(options[i].charAt(j)));
                    sb.append('[')
                      .append(options[i].charAt(j))
                      .append(']');
                    flag = true;
                    continue;
                }
                sb.append(options[i].charAt(j));
            }
            sb.append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        while (N-- > 0) {
            String[] options = bf.readLine()
                                 .split(" ");
            sb.append(solution(options))
              .append("\n");
        }
        System.out.println(sb);
    }
}
