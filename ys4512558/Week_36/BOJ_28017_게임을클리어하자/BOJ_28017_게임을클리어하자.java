package BOJ28017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ28017 {
    static int INF = 10000 *  500 + 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (i == 0) {
                    dp[i][j] = arr[i][j];
                    continue;
                }
                dp[i][j] = INF;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < M; k++) {
                    if(j == k) continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + arr[i][j]);
                }
                if (i == N - 1) {
                    min = Math.min(min, dp[i][j]);
                }
            }
        }

        System.out.println(min);

    }

}