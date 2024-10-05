package sjhlko.week37.BOJ_15989;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LeeHi {
    //https://www.acmicpc.net/problem/15989
    //1,2,3 더하기 4
    static int N;

    static int solution() {
        int[][] dp = new int[N+4][3];
        // 이전것 + 1,2,3 해서 N을 만들 수 있는 경우의 수
        dp[1][0] = 1;
        dp[2][0] = 1;
        dp[2][1] = 1;
        dp[3][0] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        for (int i = 4; i <= N; i++) {
            dp[i][0] = dp[i-1][0];
            dp[i][1] = dp[i-2][0]+dp[i-2][1];
            dp[i][2] = dp[i-3][0]+dp[i-3][1]+dp[i-3][2];
        }
        return dp[N][0] + dp[N][1] + dp[N][2];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(bf.readLine());
            System.out.println(solution());
        }

    }

}

