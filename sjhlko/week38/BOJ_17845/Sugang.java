package sjhlko.week38.BOJ_17845;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sugang {
    // https://www.acmicpc.net/problem/17845
    // 수강 과목
    static int N, K;
    static int[][] info;

    static long solution() {
        long[][] dp = new long[K][N + 1];
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < N + 1; j++) {
                if(i>0) dp[i][j] = dp[i - 1][j];
                if (j >= info[i][1]) {
                    dp[i][j] = Math.max(dp[i][j], info[i][0]);
                    if(i>0) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - info[i][1]]+info[i][0]);
                }
            }
        }
        return dp[K-1][N];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        info = new int[K][2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            info[i][0] = a;
            info[i][1] = b;
        }
        System.out.println(solution());
    }
}
