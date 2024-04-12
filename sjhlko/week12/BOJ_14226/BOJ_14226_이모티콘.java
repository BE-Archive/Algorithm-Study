package sjhlko.week12.BOJ_14226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_14226_이모티콘 {
    //https://www.acmicpc.net/problem/14226
    //이모티콘
    static int N;

    static boolean isValid(int n) {
        return n > 0 && n <= N;
    }

    static int solution() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{1, 0});
        boolean[][] visited = new boolean[N + 1][N + 1];
        visited[1][0] = true;
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] now = queue.poll();
                if (now[0] == N) return count;
                if (!visited[now[0]][now[0]]) {
                    visited[now[0]][now[0]] = true;
                    queue.add(new int[]{now[0], now[0]});
                }
                if (isValid(now[0] + now[1]) && !visited[now[0] + now[1]][now[1]]) {
                    visited[now[0] + now[1]][now[1]] = true;
                    queue.add(new int[]{now[0] + now[1], now[1]});
                }
                if (isValid(now[0] - 1) && !visited[now[0] - 1][now[1]]) {
                    visited[now[0] - 1][now[1]] = true;
                    queue.add(new int[]{now[0] - 1, now[1]});
                }
            }
            count++;
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        System.out.println(solution());
    }
}
