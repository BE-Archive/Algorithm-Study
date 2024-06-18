class Solution {
    public int solution(int n, int[] tops) {
        int answer = 0;
        
        int MOD = 10007;
        int[][] dp = new int[n+1][5]; // 삼각, 왼마름모, 오른마름모, 위마름모, 오위마름모
        
        dp[0][0] = dp[0][2] = 1;

        for (int i = 1; i <= n; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2] + dp[i-1][3] + dp[i-1][4]) % MOD;
            dp[i][1] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][3]) % MOD;
            
            if (i != n) {
                dp[i][2] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2] + dp[i-1][3] + dp[i-1][4]) % MOD;
            }
            
            if (tops[i-1] == 1) {
                dp[i][3] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][3]) % MOD;
                
                if (i != n) {
                    dp[i][4] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][3]) % MOD;
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            answer = (answer + dp[n][i]) % MOD;
        }
        
        return answer;
    }
}