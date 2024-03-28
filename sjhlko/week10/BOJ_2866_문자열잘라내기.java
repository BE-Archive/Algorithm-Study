package sjhlko.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_2866_문자열잘라내기 {
    //https://www.acmicpc.net/problem/2866
    //문자열 잘라내기
    static int R, C;
    static String[] strings;

    static int solution() {
        String[] next = new String[C];
        int count = R - 1;
        for (int i = R - 1; i >= 0; i--) {
            Set<String> set = new HashSet<>();
            for (int j = 0; j < C; j++) {
                if (i == R - 1) {
                    next[j] = String.valueOf(strings[i].charAt(j));
                } else next[j] = new StringBuilder(next[j]).append(strings[i].charAt(j)).toString();
                if (set.contains(next[j])) count = i - 1;
                else set.add(next[j]);
            }
        }
        return Math.max(count, 0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        strings = new String[R];
        for (int i = 0; i < R; i++) {
            strings[i] = bf.readLine();
        }
        System.out.println(solution());
    }

}
