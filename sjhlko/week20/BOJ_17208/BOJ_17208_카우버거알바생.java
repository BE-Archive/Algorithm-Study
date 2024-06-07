package sjhlko.week20.BOJ_17208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17208_카우버거알바생 {
    //https://www.acmicpc.net/problem/17208
    //카우버거 알바생
    static int N, M, K;
    static int[][] info;
    static int[][][] ans;

    static void solution() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M + 1; j++) {
                for (int k = 0; k < K + 1; k++) {
                    if (i == 0) {
                        if (j < info[0][0] || k < info[0][1]) continue;
                        ans[0][j][k] = 1;
                        continue;
                    }
                    ans[i][j][k] = ans[i - 1][j][k];
                    if (j < info[i][0] || k < info[i][1]) continue;
                    ans[i][j][k] = Math.max(ans[i][j][k], ans[i - 1][j - info[i][0]][k - info[i][1]] + 1);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        info = new int[N][2];
        ans = new int[N][M + 1][K + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            info[i][0] = a;
            info[i][1] = b;
        }
        solution();
        System.out.println(ans[N - 1][M][K]);
    }
}
