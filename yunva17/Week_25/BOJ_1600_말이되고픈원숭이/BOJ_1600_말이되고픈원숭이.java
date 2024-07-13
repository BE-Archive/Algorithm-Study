import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1600_말이되고픈원숭이 {

    static int dir1[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int dir2[][] = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
    static int K, W, H;
    static int map[][];
    static boolean visited[][][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        visited = new boolean[H][W][K + 1];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs(0, 0));
    }

    public static int bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();

        visited[x][y][0] = true;
        queue.offer(new int[]{x, y, 0});

        int count = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] now = queue.poll();

                // 도착 지점에 도달한 경우
                if (now[0] == H - 1 && now[1] == W - 1) {
                    return count;
                }

                for (int[] d1 : dir1) {
                    int dx = now[0] + d1[0];
                    int dy = now[1] + d1[1];

                    if (!isMap(dx, dy) || visited[dx][dy][now[2]]) continue;

                    visited[dx][dy][now[2]] = true;
                    queue.offer(new int[]{dx, dy, now[2]});
                }

                if (now[2] >= K) continue;

                for (int[] d2 : dir2) {
                    int dx = now[0] + d2[0];
                    int dy = now[1] + d2[1];

                    if (isMap(dx, dy) && !visited[dx][dy][now[2] + 1]) {
                        visited[dx][dy][now[2] + 1] = true;
                        queue.offer(new int[]{dx, dy, now[2] + 1});
                    }
                }
            }
            count++;
        }

        return -1;

    }

    public static boolean isMap(int x, int y) {
        if (x >= H || y >= W || x < 0 || y < 0 || map[x][y] == 1) return false;

        return true;
    }
}
