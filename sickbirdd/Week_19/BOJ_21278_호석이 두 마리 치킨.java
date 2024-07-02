import java.io.*;
import java.util.*;

public class Main {
	final static int INF = (int) 1e6;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		int[][] dist = new int[N + 1][N + 1];

		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				dist[i][j] = (i == j) ? 0 : INF;
			}
		}

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken());
			dist[A][B] = 1;
			dist[B][A] = 1;
		}

		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}

		int ans = INF, start = -1, end = -1;
		for(int i = 1; i < N; i++) {
			for(int j = i + 1; j <= N; j++) {
				int sum = 0;
				for(int k = 1; k <= N; k++) {
					sum += Math.min(dist[i][k], dist[j][k]);
				}
				if(sum < ans) {
					start = i;
					end = j;
					ans = sum;
				}
			}
		}
		System.out.println(start + " " + end + " " + ans * 2);
	}
}