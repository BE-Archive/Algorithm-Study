package sjhlko.week14.BOJ_16946;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16946_벽부수고이동하기4 {
    //https://www.acmicpc.net/problem/16946
    //벽 부수고 이동하기 4
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static char[][] map;
    static int[] parent, height;
    static int N, M;

    static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) return false;
        if (height[px] < height[py]) {
            int tmp = px;
            px = py;
            py = tmp;
        }
        if (height[px] == height[py]) height[px]++;
        parent[px] += parent[py];
        parent[py] = px;
        height[py] = 0;
        return true;
    }

    static void init() {
        parent = new int[N * M];
        height = new int[N * M];
        Arrays.fill(parent, -1);
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) continue;
                if (map[i][j] == '1') continue;
                visited[i][j] = true;
                Queue<int[]> queue = new ArrayDeque<>();
                queue.add(new int[]{i, j});
                while (!queue.isEmpty()) {
                    int[] now = queue.poll();
                    for (int k = 0; k < 4; k++) {
                        int nextI = now[0] + dx[k];
                        int nextJ = now[1] + dy[k];
                        if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= M) continue;
                        if (map[nextI][nextJ] == '1') continue;
                        if (visited[nextI][nextJ]) continue;
                        visited[nextI][nextJ] = true;
                        union(i * M + j, nextI * M + nextJ);
                        queue.add(new int[]{nextI, nextJ});
                    }
                }
            }
        }
    }

    static void solution() {
        init();
        int[][] ans = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '0') continue;
                ans[i][j] = 1;
                HashSet<Integer> keys = new HashSet<>();
                for (int k = 0; k < 4; k++) {
                    int nextI = i + dx[k];
                    int nextJ = j + dy[k];
                    if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= M) continue;
                    if (map[nextI][nextJ] == '1') continue;
                    int pNext = find(nextI * M + nextJ);
                    if (keys.contains(pNext)) continue;
                    keys.add(pNext);
                    ans[i][j] -= parent[pNext];
                    ans[i][j] %= 10;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(ans[i][j] % 10);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = bf.readLine().toCharArray();
        }
        solution();
    }
}