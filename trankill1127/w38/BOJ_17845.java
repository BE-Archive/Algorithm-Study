package trankill1127.w38;

import java.io.*;
import java.util.*;

public class BOJ_17845 {

	public static int n; //최대 공부 시간(제한)
	public static int k; //과목 수(옵션)
	public static int[] imp = new int[1001];  //중요도
	public static int[] time = new int[1001]; //소요시간

	public static int[][] dp = new int[k+1][n+1];

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		for (int i=1; i<=k; i++){
			st = new StringTokenizer(br.readLine());
			imp[i] = Integer.parseInt(st.nextToken());
			time[i] = Integer.parseInt(st.nextToken());
		}

		System.out.print(solution());
	}

	public static int solution(){
		for (int i=1; i<=k; i++){ //과목 수(옵션)
			for (int j=0; j<=n; j++){ //공부 시간(제한)
				if (j>=time[i]) dp[i][j]=Math.max(dp[i-1][j], dp[i-1][j-time[i]]+imp[i]);
				else dp[i][j]=dp[i-1][j];
			}
		}

		return dp[k][n];
	}
}