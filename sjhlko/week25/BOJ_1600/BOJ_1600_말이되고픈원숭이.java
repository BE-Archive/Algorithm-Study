package sjhlko.week25.BOJ_1600;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1600_말이되고픈원숭이 {
    //https://www.acmicpc.net/problem/1600
    //말이 되고픈 원숭이
    static int K, N, M;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static int[] hdx = {-2, -2, -1, -1, 1, 1, 2, 2}, hdy = {-1, 1, -2, 2, -2, 2, -1, 1};

    static boolean isValid(int i, int j) {
        return i >= 0 && j >= 0 && i < N && j < M && map[i][j] == 0;
    }

    static int solution() {
        int ans = Integer.MAX_VALUE;
        boolean[][][] visited = new boolean[N][M][K+1];
        visited[0][0][0] = true;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0, 0, 0});
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            if (now[0] == N - 1 && now[1] == M - 1) return now[2];
            for (int i = 0; i < 4; i++) {
                int nextI = now[0] + dx[i];
                int nextJ = now[1] + dy[i];
                if (!isValid(nextI, nextJ)) continue;
                if (visited[nextI][nextJ][now[3]]) continue;
                visited[nextI][nextJ][now[3]] = true;
                queue.add(new int[]{nextI, nextJ, now[2] + 1, now[3]});
            }
            if (now[3] == K) continue;
            for (int i = 0; i < 8; i++) {
                int nextI = now[0] + hdx[i];
                int nextJ = now[1] + hdy[i];
                if (!isValid(nextI, nextJ)) continue;
                if (visited[nextI][nextJ][now[3] + 1]) continue;
                visited[nextI][nextJ][now[3] + 1] = true;
                queue.add(new int[]{nextI, nextJ, now[2] + 1, now[3] + 1});
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution());
    }
}
