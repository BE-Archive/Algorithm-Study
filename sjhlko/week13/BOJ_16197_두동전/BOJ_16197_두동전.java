package sjhlko.week13.BOJ_16197_두동전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16197_두동전 {
    //https://www.acmicpc.net/problem/16197
    //두 동전
    static int N, M;
    static char[][] map;
    static List<Info> coin = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    static class Info {
        int i, j;

        public Info(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static boolean isOut(int i, int j) {
        return i < 0 || i >= N || j < 0 || j >= M;
    }

    static int solution() {
        Queue<Info[]> queue = new ArrayDeque<>();
        queue.add(new Info[]{coin.get(0), coin.get(1)});
        boolean[][][][] visited = new boolean[N][M][N][M];
        visited[coin.get(0).i][coin.get(0).j][coin.get(1).i][coin.get(1).j] = true;
        visited[coin.get(1).i][coin.get(1).j][coin.get(0).i][coin.get(0).j] = true;
        int count = 1;
        while (!queue.isEmpty()) {
            if (count > 10) return -1;
            int size = queue.size();
            while (size-- > 0) {
                Info[] now = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nextI0 = now[0].i + dx[i];
                    int nextJ0 = now[0].j + dy[i];
                    int nextI1 = now[1].i + dx[i];
                    int nextJ1 = now[1].j + dy[i];
                    if (isOut(nextI0, nextJ0) && isOut(nextI1, nextJ1)) continue;
                    if (!isOut(nextI0, nextJ0) && map[nextI0][nextJ0] == '#') {
                        nextI0 = now[0].i;
                        nextJ0 = now[0].j;
                    }
                    if (!isOut(nextI1, nextJ1) && map[nextI1][nextJ1] == '#') {
                        nextI1 = now[1].i;
                        nextJ1 = now[1].j;
                    }
                    if (isOut(nextI0, nextJ0) ^ isOut(nextI1, nextJ1)) {
                        return count;
                    }
                    if (visited[nextI0][nextJ0][nextI1][nextJ1]) continue;
                    visited[nextI0][nextJ0][nextI1][nextJ1] = true;
                    visited[nextI1][nextJ1][nextI0][nextJ0] = true;
                    queue.add(new Info[]{new Info(nextI0, nextJ0), new Info(nextI1, nextJ1)});
                }
            }
            count++;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String s = bf.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'o') {
                    map[i][j] = '.';
                    coin.add(new Info(i, j));
                }
            }
        }
        System.out.println(solution());
    }
}