package sjhlko.week12.BOJ_1865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1865_웜홀 {
    //https://www.acmicpc.net/problem/1865
    //웜홀
    static int N, M, W;
    static int[][] info, dist;

    static boolean solution() {
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                for (int k = 1; k < N + 1; k++) {
                    dist[j][k] = Math.min(dist[j][k], info[j][k]);
                    if (dist[j][i] != Integer.MAX_VALUE && dist[i][k] != Integer.MAX_VALUE) {
                        dist[j][k] = Math.min(dist[j][k], dist[j][i] + dist[i][k]);
                    }
                    if (j == k && dist[j][k] < 0) return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            info = new int[N + 1][N + 1];
            dist = new int[N + 1][N + 1];
            for (int i = 1; i < N + 1; i++) {
                Arrays.fill(info[i], Integer.MAX_VALUE);
                Arrays.fill(dist[i], Integer.MAX_VALUE);
                dist[i][i] = 0;
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(bf.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                info[a][b] = Math.min(info[a][b], c);
                info[b][a] = Math.min(info[b][a], c);
            }
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(bf.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                info[a][b] = Math.min(info[a][b], -c);
            }
            sb.append(solution() ? "YES" : "NO").append("\n");
        }
        System.out.println(sb);
    }
}
