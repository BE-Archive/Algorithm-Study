import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BOJ2573_3.prob2573();
    }
}

/**
 * dfs 풀이 메모리: 45860 KB, 시간: 400 ms
 */

class BOJ2573_3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int[][] map;
    static int[][] meltMap;

    static boolean[][] isVisited;
    static int[] dy = { -1, 0, 0, 1 };
    static int[] dx = { 0, -1, 1, 0 };
    static int N;
    static int M;
    static int year = 0;
    static int iceCount = 0;

    public static void prob2573() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        meltMap = new int[N][M];
        isVisited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                meltMap[i][j] = map[i][j];
            }
        }
        int res = 0;
        while (true) {
            res = melt();
            if (res != -1) {
                break;
            }
        }
        sb.append(res);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int melt() {
        year++;
        iceCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = meltMap[i][j];
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                // 얼음 개수를 샌다.
                for (int k = 0; k < 4; k++) {
                    int row = i + dy[k];
                    int col = j + dx[k];
                    if (row < 0 || row >= N || col < 0 || col >= M) {
                        continue;
                    }
                    // 주의 : 왼쪽이 2 -> 0이 된 상태로
                    // 다음 탐색 시 이번에 녹은 0도 포함해서 녹을 수 있는 것
                    if (map[row][col] > 0 || meltMap[i][j] <= 0) {
                        continue;
                    }
                    meltMap[i][j]--;
                }
                iceCount = meltMap[i][j] > 0 ? iceCount + 1 : iceCount;
            }
        }
        if (iceCount == 0) {
            return 0;
        }
        isVisited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (meltMap[i][j] == 0 || isVisited[i][j]) {
                    continue;
                }
                // 1회 탐색 시 얼음 개수가 0이 아니라면 분할된 것이다.
                dfs(i, j);
                if (iceCount != 0) {
                    return year;
                }
            }
        }
        return -1;
    }

    private static void dfs(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= M || isVisited[row][col] || meltMap[row][col] == 0) {
            return;
        }
        iceCount--;
        isVisited[row][col] = true;
        for (int i = 0; i < 4; i++) {
            int y = row + dy[i];
            int x = col + dx[i];
            dfs(y, x);
        }
    }
}

/**
 * bfs풀이 메모리: 105088 KB, 시간: 528 ms
 */
class BOJ2573_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int[][] map;
    static int[][] meltMap;

    static boolean[][] isVisited;
    static int[] dy = { -1, 0, 0, 1 };
    static int[] dx = { 0, -1, 1, 0 };
    static int N;
    static int M;
    static int year = 0;
    static int iceCount = 0;

    public static void prob2573() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        meltMap = new int[N][M];
        isVisited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                meltMap[i][j] = map[i][j];
            }
        }
        int res = 0;
        while (true) {
            res = melt();
            if (res != -1) {
                break;
            }
        }
        sb.append(res);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int melt() {
        year++;
        iceCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = meltMap[i][j];
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                // 얼음 개수를 샌다.
                for (int k = 0; k < 4; k++) {
                    int row = i + dy[k];
                    int col = j + dx[k];
                    if (row < 0 || row >= N || col < 0 || col >= M) {
                        continue;
                    }
                    // 주의 : 왼쪽이 2 -> 0이 된 상태로
                    // 다음 탐색 시 이번에 녹은 0도 포함해서 녹을 수 있는 것
                    if (map[row][col] > 0 || meltMap[i][j] <= 0) {
                        continue;
                    }
                    meltMap[i][j]--;
                }
                iceCount = meltMap[i][j] > 0 ? iceCount + 1 : iceCount;
            }
        }
        if (iceCount == 0) {
            return 0;
        }
        isVisited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (meltMap[i][j] == 0 || isVisited[i][j]) {
                    continue;
                }
                // 1회 탐색 시 얼음 개수가 0이 아니라면 분할된 것이다.
                bfs(i, j);
                if (iceCount != 0) {
                    return year;
                }
            }
        }
        return -1;
    }

    private static void bfs(int row, int col) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { row, col });
        isVisited[row][col] = true;
        iceCount--;

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int curY = point[0];
            int curX = point[1];

            for (int i = 0; i < 4; i++) {
                int y = curY + dy[i];
                int x = curX + dx[i];

                if (y < 0 || y >= N || x < 0 || x >= M || isVisited[y][x] || meltMap[y][x] == 0) {
                    continue;
                }
                isVisited[y][x] = true;
                iceCount--; // 방문한 얼음 개수를 뺀다.
                queue.add(new int[] { y, x });
            }
        }
    }

}