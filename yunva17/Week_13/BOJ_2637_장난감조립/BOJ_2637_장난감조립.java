import java.io.*;
import java.util.StringTokenizer;

public class ToyAssemble {

	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		int dp[][] = new int[N + 1][N + 1];
		boolean basic[] = new boolean[N+1]; // 기본

		StringTokenizer st;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			dp[X][Y] = K;
			
			basic[X] = true;

		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= N; k++) {
					if(basic[k]) continue;
					
					dp[i][k] += dp[j][k] * dp[i][j];
				}
			}
		}
		
		for(int i = 1; i<=N; i++) {
			if(!basic[i]) {
				System.out.println(i+" "+dp[N][i]);
			}
		}
		

	}


}
