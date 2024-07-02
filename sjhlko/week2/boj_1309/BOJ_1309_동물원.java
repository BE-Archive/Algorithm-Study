package sjhlko.week2.boj_1309;

import java.util.Scanner;

public class BOJ_1309_동물원 {
    //https://www.acmicpc.net/problem/1309
    //동물원
    static final int MOD = 9901;
    static int N;

    //dp[i][j] = i번째 행에 사자를 j상태로 배치한다고 할때, 가능한 경우의 수
    //j 의 상태
    //0 : 아없
    //1: 왼
    //2 : 오
    static int[][] dp;

    private static void solution() {
        dp[0][0] = 1;
        dp[0][1] = 1;
        dp[0][2] = 1;
        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2];
            dp[i][1] = dp[i - 1][2] + dp[i - 1][0];
            dp[i][2] = dp[i - 1][1] + dp[i - 1][0];
            for (int j = 0; j < 3; j++) {
                dp[i][j] %= MOD;
            }
        }
        int ans = (dp[N-1][0] + dp[N-1][1] + dp[N-1][2]) % MOD;
        System.out.println(ans);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        dp = new int[N][3];
        solution();
    }
}