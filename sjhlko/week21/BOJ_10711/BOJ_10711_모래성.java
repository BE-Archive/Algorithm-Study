package sjhlko.week21.BOJ_10711;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_10711_모래성 {
    //https://www.acmicpc.net/problem/10711
    //모래성
    static int H, W;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1}, dy = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
    static int[][] map;
    static Queue<int[]> next = new ArrayDeque<>();
    static boolean[][] visited;

    static boolean isValid(int i, int j) {
        return i >= 0 && j >= 0 && i < H && j < W;
    }

    static int solution() {
        int count = -1;
        while (!next.isEmpty()) {
            boolean end = true;
            int size = next.size();
            while (size-- > 0) {
                int[] now = next.poll();
                for (int k = 0; k < 8; k++) {
                    int nextI = now[0] + dx[k];
                    int nextJ = now[1] + dy[k];
                    if (!isValid(nextI, nextJ) || map[nextI][nextJ] == 0) {
                        continue;
                    }
                    map[nextI][nextJ]--;
                    if (map[nextI][nextJ] == 0 && !visited[nextI][nextJ]) {
                        end = false;
                        next.add(new int[]{nextI, nextJ});
                        visited[nextI][nextJ] = true;
                    }
                }
            }
            count++;
            if (end) return count;
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        visited = new boolean[H][W];
        for (int i = 0; i < H; i++) {
            String s = bf.readLine();
            for (int j = 0; j < W; j++) {
                if (s.charAt(j) == '.') {
                    map[i][j] = 0;
                    next.add(new int[]{i, j});
                    visited[i][j] = true;
                    continue;
                }
                map[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
            }
        }
        System.out.println(solution());
    }
}