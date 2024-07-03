import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		int[][] orders = new int[N][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			orders[i][0] = Integer.parseInt(st.nextToken());
			orders[i][1] = Integer.parseInt(st.nextToken());
		}

		int[][] dp = new int[M + 1][K + 1];
		for(int i = 0; i < N; i++) {
			for(int j = M; j >= orders[i][0]; j--) {
				if(K < orders[i][1]) break;
				for(int k = K; k >= orders[i][1]; k--) {
					dp[j][k] = Math.max(dp[j][k], dp[j - orders[i][0]][k - orders[i][1]] + 1);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(dp[M][K]);
		System.out.println(sb);
	}
}