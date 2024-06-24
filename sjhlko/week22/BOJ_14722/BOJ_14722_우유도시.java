package sjhlko.week22.BOJ_14722;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14722_우유도시 {
    //https://www.acmicpc.net/problem/14722
    //우유 도시
    static int N;
    static int[][] map;

    static int solution() {
        int[][][] dp = new int[N + 1][N + 1][3];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int now = map[i - 1][j - 1];
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i][j - 1][k]);
                }
                if (now == 0) dp[i][j][now] = Math.max(dp[i][j][now], 1);
                if (dp[i - 1][j][(now - 1 + 3) % 3] == 0 && dp[i][j - 1][(now - 1 + 3) % 3] == 0) continue;
                dp[i][j][now] = Math.max(dp[i][j][now], Math.max(dp[i - 1][j][(now - 1 + 3) % 3], dp[i][j - 1][(now - 1 + 3) % 3]) + 1);
            }
        }
        int ret = 0;
        for (int i = 0; i < 3; i++) {
            ret = Math.max(ret, dp[N][N][i]);
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution());

    }

}
