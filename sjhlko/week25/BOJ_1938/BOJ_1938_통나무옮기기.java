package sjhlko.week25.BOJ_1938;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;
import java.util.Queue;

public class BOJ_1938_통나무옮기기 {
    static int N;
    static final int RIGHT = 0, DOWN = 1;
    static Info train, target;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    static class Info {
        int[] center;
        int direction;

        public Info(int[] center, int direction) {
            this.center = center;
            this.direction = direction;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Info info = (Info) o;
            return direction == info.direction && Arrays.equals(center, info.center);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(direction);
            result = 31 * result + Arrays.hashCode(center);
            return result;
        }
    }

    static boolean isValid(Info info) {
        int i = info.center[0];
        int j = info.center[1];
        if (info.direction == 1) {
            if (j < 0 || j >= N) return false;
            if (i - 1 < 0 || i + 1 >= N) return false;
            if (map[i - 1][j] == '1' || map[i][j] == '1' || map[i + 1][j] == '1') return false;
            return true;
        }
        if (i < 0 || i >= N) return false;
        if (j - 1 < 0 || j + 1 >= N) return false;
        if (map[i][j] == '1' || map[i][j - 1] == '1' || map[i][j + 1] == '1') return false;
        return true;
    }

    static boolean isRotateValid(Info info) {
        int i = info.center[0];
        int j = info.center[1];
        if (info.direction == 0) {
            for (int k = i - 1; k < i + 2; k++) {
                if (map[k][j - 1] == '1' || map[k][j + 1] == '1') return false;
            }
            return true;
        }
        for (int k = j - 1; k < j + 2; k++) {
            if (map[i - 1][k] == '1' || map[i + 1][k] == '1') return false;
        }
        return true;
    }

    static int solution() {
        boolean[][][] visited = new boolean[N][N][2];
        visited[train.center[0]][train.center[1]][train.direction] = true;
        Queue<Info> queue = new ArrayDeque<>();
        queue.add(train);
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Info now = queue.poll();
                if (now.equals(target)) return count;
                //위왼오아
                for (int i = 0; i < 4; i++) {
                    Info next = new Info(new int[]{now.center[0] + dx[i], now.center[1] + dy[i]}, now.direction);
                    if (isValid(next) && !visited[now.center[0] + dx[i]][now.center[1] + dy[i]][now.direction]) {
                        visited[now.center[0] + dx[i]][now.center[1] + dy[i]][now.direction] = true;
                        queue.add(new Info(new int[]{now.center[0] + dx[i], now.center[1] + dy[i]}, now.direction));
                    }
                }

                //회전
                Info next = new Info(new int[]{now.center[0], now.center[1]}, 1 - now.direction);
                if (isValid(next) && isRotateValid(next) && !visited[now.center[0]][now.center[1]][1 - now.direction]) {
                    visited[now.center[0]][now.center[1]][1 - now.direction] = true;
                    queue.add(new Info(new int[]{now.center[0], now.center[1]}, 1 - now.direction));
                }
            }
            count++;
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        map = new char[N][N];
        int tCount = 0, eCount = 0;
        for (int i = 0; i < N; i++) {
            String s = bf.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'B') {
                    if (tCount == 1) {
                        if (j > 0 && map[i][j - 1] == 'B') train = new Info(new int[]{i, j}, RIGHT);
                        else train = new Info(new int[]{i, j}, DOWN);
                    }
                    tCount++;
                }
                if (map[i][j] == 'E') {
                    if (eCount == 1) {
                        if (j > 0 && map[i][j - 1] == 'E') target = new Info(new int[]{i, j}, RIGHT);
                        else target = new Info(new int[]{i, j}, DOWN);
                    }
                    eCount++;
                }
            }
        }
        System.out.println(solution());
    }
}