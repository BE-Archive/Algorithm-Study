package Week_21.PGS_산_모양_타일링;

class PGS_산_모양_타일링 {
    public int solution(int n, int[] tops) {
        int answer = 0;
        int[][] dp = new int[n + 1][2];
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            if (tops[i - 1] == 1) {
                dp[i][0] = (dp[i - 1][0] * 3 + dp[i - 1][1] * 2) % 10007;
                dp[i][1] = (dp[i - 1][0] + dp[i - 1][1]) % 10007;
            } else {
                dp[i][0] = (dp[i - 1][0] * 2 + dp[i - 1][1]) % 10007;
                dp[i][1] = (dp[i - 1][0] + dp[i - 1][1]) % 10007;
            }
        }

        answer = (dp[n][0] + dp[n][1]) % 10007;
        return answer;
    }
}
