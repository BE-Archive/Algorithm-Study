package Week_19.BOJ_17609;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17609 {

    static int solve(int left, int right, String str, boolean check) {
        while (left < right) {
            if (str.charAt(left) == str.charAt(right)) {
                left++;
                right--;
            } else {
                if (check) return 2;
                return Math.min(Math.min(solve(left + 1, right, str, true),
                        solve(left, right - 1, str, true)), 2);
            }
        }
        return check ? 1 : 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        String str;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            str = br.readLine();
            sb.append(solve(0, str.length() - 1, str, false)).append("\n");
        }

        System.out.print(sb);
    }
}
