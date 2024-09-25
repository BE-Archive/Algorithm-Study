package trankill1127.w36;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_28017 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken()); // 게임 회차
		int m = Integer.parseInt(st.nextToken()); // 무기 종류
		int[][] times = new int[n][m]; // 회차마다 특정 무기 썼을 때 클리어까지 걸리는 시간

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				times[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] dp = new int[n][m];
		for (int j = 0; j < m; j++) dp[0][j] = times[0][j];

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < m; j++) {
				dp[i][j] = Integer.MAX_VALUE;
				for (int k = 0; k < m; k++) {
					if (j != k) {
						dp[i][j] = Math.min(dp[i][j], dp[i-1][k] + times[i][j]);
					}
				}
			}
		}

		int minTime = Integer.MAX_VALUE;
		for (int j = 0; j < m; j++) {
			minTime = Math.min(minTime, dp[n-1][j]);
		}
		System.out.println(minTime);
	}
}
