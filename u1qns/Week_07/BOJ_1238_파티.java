import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

	static final int INF = 1000000;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		int from, to, answer = -1;
		int[][] W = new int[N+1][N+1];

		for (int i = 1; i <= N; ++i) {
			Arrays.fill(W[i], INF);
		}

		for (int i = 0; i < M; ++i) {
			
			st = new StringTokenizer(br.readLine());

			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());

			W[from][to] = Integer.parseInt(st.nextToken());
		}

		for (int k = 1; k <= N; ++k) {
			for (int i = 1; i <= N; ++i) {
				for (int j = 1; j <= N; ++j) {
					if (W[i][k] + W[k][j] < W[i][j])
						W[i][j] = W[i][k] + W[k][j];
				}
			}
		}
		
		for (int i = 1; i <= N; ++i)
			if (i != X)
				answer = Math.max(answer, W[i][X] + W[X][i]);

		System.out.print(answer);

	}

}
