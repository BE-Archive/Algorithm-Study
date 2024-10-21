package Week_40.BOJ_17175_피보나치는_지겨웡;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_17175_피보나치는_지겨웡 {

    static int N;
    static int[] dp;
    static int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        
        dp = new int[51];
        dp[0] = dp[1] = 1;
        
        for(int i=2; i<=N; i++){
            dp[i] = (1 + dp[i-1] + dp[i-2]) % MOD;
        }

        System.out.println(dp[N]);
    }
}