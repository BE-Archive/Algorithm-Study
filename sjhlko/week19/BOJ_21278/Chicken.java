package sjhlko.week19.BOJ_21278;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Chicken {
    //https://www.acmicpc.net/problem/21278
    //호석이 두 마리 치킨
    static int N, M;
    static int[][] dist;

    static int[] solution() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    if (dist[i][j] == Integer.MAX_VALUE || dist[i][k] == Integer.MAX_VALUE) continue;
                    dist[j][k] = Math.min(dist[j][k], dist[i][j] + dist[i][k]);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        int[] ans = null;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int sum = 0;
                for (int k = 1; k <= N; k++) {
                    if (dist[i][k] == Integer.MAX_VALUE || dist[j][k] == Integer.MAX_VALUE) {
                        sum = Integer.MAX_VALUE;
                        break;
                    }
                    sum += Math.min(dist[i][k], dist[j][k]);
                }
                if (sum < min) {
                    min = sum;
                    ans = new int[]{i, j, sum * 2};
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dist = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dist[a][b] = 1;
            dist[b][a] = 1;
        }
        int[] ans = solution();
        System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
    }
}
