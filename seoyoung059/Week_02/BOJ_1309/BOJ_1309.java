package seoyoung059.Week_02;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1309 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n+2];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 2; i++) {
            dp[i] = (2*dp[i-1]+dp[i-2])%9901;
        }

        System.out.println(dp[n+1]);
    }
}
