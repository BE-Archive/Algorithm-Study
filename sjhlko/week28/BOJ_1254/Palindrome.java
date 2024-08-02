package sjhlko.week28.BOJ_1254;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Palindrome {
    //https://www.acmicpc.net/problem/1254
    //팰린드롬 만들기
    static String s;
    static int solution() {
        int ans = 2 * s.length();
        for (int i = 0; i < s.length(); i++) {
            int l = i - 1;
            int r = i + 1;
            boolean isValid = true;
            while (r < s.length()) {
                if (l < 0 || s.charAt(l) != s.charAt(r)) {
                    isValid = false;
                    break;
                }
                l--;
                r++;
            }
            if (isValid) ans = Math.min(ans, i * 2 + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            int l = i - 1;
            int r = i;
            boolean isValid = true;
            while (r < s.length()) {
                if (l < 0 || s.charAt(l) != s.charAt(r)) {
                    isValid = false;
                    break;
                }
                l--;
                r++;
            }
            if (isValid) ans = Math.min(ans, i * 2);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        s = bf.readLine();
        System.out.println(solution());
    }

}
