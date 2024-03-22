package sjhlko.week8.BOJ_16234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_16234_인구이동 {
    //https://www.acmicpc.net/problem/16234
    //인구이동
    static int N, L, R;
    static int[][][] parent;
    static int[][] height, map;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    static void init() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(parent[i][j], -1);
            }
        }
        height = new int[N][N];
    }

    static int[] find(int i, int j) {
        if (parent[i][j][0] == -1 && parent[i][j][1] == -1) return new int[]{i, j};
        return parent[i][j] = find(parent[i][j][0], parent[i][j][1]);
    }

    static boolean union(int[] u, int[] v) {
        u = find(u[0], u[1]);
        v = find(v[0], v[1]);
        if (u[0] == v[0] && u[1] == v[1]) return false;
        if (height[u[0]][u[1]] >= height[v[0]][v[1]]) {
            int[] tmpU = new int[]{u[0], u[1]};
            u = new int[]{v[0], v[1]};
            v = new int[]{tmpU[0], tmpU[1]};
        }
        parent[u[0]][u[1]] = new int[]{v[0], v[1]};
        if (height[u[0]][u[1]] == height[v[0]][v[1]]) height[v[0]][v[1]]++;
        height[u[0]][u[1]] = 0;
        return true;
    }

    static boolean open() {
        boolean ret = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 4; k++) {
                    int nextI = i + dx[k];
                    int nextJ = j + dy[k];
                    if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N) continue;
                    int diff = Math.abs(map[i][j] - map[nextI][nextJ]);
                    if (diff >= L && diff <= R) {
                        if (union(new int[]{i, j}, new int[]{nextI, nextJ})) {
                            ret = true;
                        }
                    }
                }
            }
        }
        return ret;
    }

    static void migration() {
        int[][] people = new int[N][N];
        int[][] count = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int[] root = find(i, j);
                people[root[0]][root[1]] += map[i][j];
                count[root[0]][root[1]]++;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int[] root = find(i, j);
                map[i][j] = people[root[0]][root[1]] / count[root[0]][root[1]];
            }
        }
    }

    static void solution() {
        init();
        int ans = 0;
        while (true) {
            if (!open()) {
                System.out.println(ans);
                return;
            }
            ans++;
            migration();
            init();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        parent = new int[N][N][2];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solution();
    }
}