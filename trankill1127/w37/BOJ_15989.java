package trankill1127.w37;

import java.io.*;

public class BOJ_15989 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		int n = 0; //이걸 1, 2, 3의 합으로 나타내는 방법은?

		int[][] dp = new int[10001][4];
		dp[1][1]=1;
		dp[2][1]=1; dp[2][2]=1;
		dp[3][3]=1; dp[3][2]=1; dp[3][1]=1;
		for (int i=4; i<10001; i++){
			dp[i][1]=dp[i-1][1];
			dp[i][2]=dp[i-2][1]+dp[i-2][2];
			dp[i][3]=dp[i-3][1]+dp[i-3][2]+dp[i-3][3];
		}

		for (int tc=0; tc<t; tc++){
			n = Integer.parseInt(br.readLine());
			System.out.println(dp[n][1]+dp[n][2]+dp[n][3]);
		}
	}
}