import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_10711 {

    static int H, W;
    static char[][] sands;
    static int[][] direct = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        sands = new char[H][W];

        for (int i = 0; i < H; i++) {
            sands[i] = br.readLine().toCharArray();
        }

        solve();
        System.out.println(answer);


    }

    public static void solve() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[H][W];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (sands[i][j] != '.' && isEnd(i, j)) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;

                }
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean collapsed = false;

            for (int i = 0; i < size; i++) {
                int[] now = queue.poll();
                int x = now[0];
                int y = now[1];

                if (sands[x][y] == '.') continue;

                if (isEnd(x, y)) {
                    sands[x][y] = '.';
                    collapsed = true;
                    for (int[] d : direct) {
                        int nx = x + d[0];
                        int ny = y + d[1];
                        if (nx >= 0 && nx < H && ny >= 0 && ny < W && sands[nx][ny] != '.' && !visited[nx][ny]) {
                            queue.offer(new int[]{nx, ny});
                            visited[nx][ny] = true;

                        }
                    }
                }
            }

            if (collapsed) answer++;
        }

    }

    // 무너진다..
    public static boolean isEnd(int x, int y) {
        int count = 0;
        for (int[] d : direct) {
            int nx = x + d[0];
            int ny = y + d[1];
            if (nx >= 0 && nx < H && ny >= 0 && ny < W && sands[nx][ny] == '.') {
                count++;
            }
        }
        return count >= (sands[x][y] - '0');
    }


}
