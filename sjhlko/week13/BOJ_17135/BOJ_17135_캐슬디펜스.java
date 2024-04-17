package sjhlko.week13.BOJ_17135;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17135_캐슬디펜스 {
    //https://www.acmicpc.net/problem/17135
    //캐슬 디펜스
    static int N, M, D;
    static int[] dx = {0, -1, 0}, dy = {-1, 0, 1};
    static boolean[][] map;
    static int total, ans;

    static boolean isValid(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < M;
    }

    static void calcAns() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j]) count++;
            }
        }
        ans = Math.min(ans, count);
    }

    static boolean[][] makeTmp() {
        boolean[][] ret = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            ret[i] = map[i].clone();
        }
        return ret;
    }

    static int[] bfs(int i, int j) {
        boolean[][] visited = new boolean[N + 1][M];
        visited[i][j] = true;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            if (Math.abs(now[0] - i) + Math.abs(now[1] - j) > D) return null;
            if (isValid(now[0], now[1]) && map[now[0]][now[1]] && now[0] != i) {
                return now;
            }
            for (int k = 0; k < 3; k++) {
                int nextI = now[0] + dx[k];
                int nextJ = now[1] + dy[k];
                if (!isValid(nextI, nextJ)) continue;
                if (visited[nextI][nextJ]) continue;
                visited[nextI][nextJ] = true;
                queue.add(new int[]{nextI, nextJ});
            }
        }
        return null;
    }

    static void solution(int[] coordinate) {
        for (int i = N - 1; i >= 0; i--) {
            List<int[]> kill = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                int[] ret = bfs(i + 1, coordinate[j]);
                if (ret != null) kill.add(ret);
            }
            for (int[] coor : kill) {
                map[coor[0]][coor[1]] = false;
            }
        }
        calcAns();
    }

    static void makeCase(int count, int index, int[] coordinate) {
        if (count == 3) {
            boolean[][] tmp = makeTmp();
            solution(coordinate);
            map = tmp;
            return;
        }
        for (int i = index; i < M; i++) {
            coordinate[count] = i;
            makeCase(count + 1, i + 1, coordinate);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                int a = Integer.parseInt(st.nextToken());
                if (a == 1) {
                    total++;
                    map[i][j] = true;
                }
            }
        }
        ans = total;
        makeCase(0, 0, new int[3]);
        System.out.println(total - ans);
    }
}
