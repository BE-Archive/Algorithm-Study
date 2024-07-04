package sjhlko.week17.BOJ2638;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2638_치즈 {
    //https://www.acmicpc.net/problem/2638
    //치즈
    static int[][] map;
    static boolean[][] dp;

    public static boolean bfs(boolean[][] tmpDP, int startI, int startJ, int N, int M) {
        Queue<int[]> queue = new LinkedList<>();
        tmpDP[startI][startJ] = true;
        queue.add(new int[]{startI, startJ});
        while (!queue.isEmpty()) {
            int[] next = queue.poll();
            int i = next[0];
            int j = next[1];
            int[] dx = {-1, 1, 0, 0};
            int[] dy = {0, 0, -1, 1};
            for (int k = 0; k < 4; k++) {
                int nextI = i + dx[k];
                int nextJ = j + dy[k];
                if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= M) continue;
                if (map[nextI][nextJ] == 1) continue;
                if (tmpDP[nextI][nextJ]) continue;
                tmpDP[nextI][nextJ] = true;
                queue.add(new int[]{nextI, nextJ});
            }
        }
        return false;
    }

    public static boolean solution(int N, int M) {
        int[][] tmpMap = new int[N][M];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        dp = new boolean[N][M];
        boolean[][] tmpDp = new boolean[N][M];
        boolean ret = true;
        bfs(dp, 0, 0, N, M);
        bfs(tmpDp, 0, 0, N, M);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) continue;
                ret = false;
                int count = 0;
                for (int k = 0; k < 4; k++) {
                    int nextI = i + dx[k];
                    int nextJ = j + dy[k];
                    if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= M) continue;
                    if (map[nextI][nextJ] == 0 && dp[nextI][nextJ]) {
                        count++;
                    }
                }
                if (count >= 2) {
                    tmpMap[i][j] = 0;
                    dp[i][j] = true;
                    bfs(tmpDp, i, j, N, M);
                } else tmpMap[i][j] = map[i][j];
            }
        }
        map = tmpMap;
        dp = tmpDp;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int count = 0;
        while (!solution(N, M)) {
            count++;
        }
        System.out.println(count);
    }

}
