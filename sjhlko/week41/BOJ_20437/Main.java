package sjhlko.week41.BOJ_20437;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    //https://www.acmicpc.net/problem/20437
    //문자열 게임2
    static String W;
    static int K;
    static int ans3 = Integer.MAX_VALUE, ans4 = Integer.MIN_VALUE;

    static boolean isValid(char c) {
        return c >= 'a' && c <= 'z';
    }

    static void solution(char c) {
        if (!isValid(c)) return;
        int count = 0;
        int l = 0, r = 0;
        while (W.length()>l && W.charAt(l) != c) {
            l++;
        }
        if(l==W.length()) return;
        r = l;
        count++;
        while (true) {
            if (count == K) {
                if (l >= W.length()) break;
                ans4 = Math.max(ans4, r - l + 1);
                ans3 = Math.min(ans3, r - l + 1);
                while (l + 1 < W.length() && W.charAt(l + 1) != c) l++;
                count--;
                l++;
            } else if (count < K) {
                if (++r >= W.length())
                    break;
                if (W.charAt(r) == c) count++;
            } else {
                if (W.charAt(l) == c) count--;
                l++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            W = bf.readLine();
            K = Integer.parseInt(bf.readLine());
            ans3 = Integer.MAX_VALUE;
            ans4 = Integer.MIN_VALUE;
            for (char c = 'a'; c <= 'z'; c++) {
                solution(c);
            }
            sb.append(ans3 != Integer.MAX_VALUE ? ans3 + " " + ans4 : "-1")
              .append("\n");
        }
        System.out.println(sb);

    }
}