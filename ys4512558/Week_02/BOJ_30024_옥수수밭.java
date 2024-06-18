import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *  우선순위 큐 메모리: 125080 KB, 시간: 880 ms
 */

public class Main {
    static int cnt;
    static int[][] map;
    static boolean[][] isVisited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        isVisited = new boolean[N][M];

        PriorityQueue<Field> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (i == 0 || j == 0 || i == N - 1 || j == M - 1) {
                    pq.add(new Field(i, j, map[i][j]));
                    isVisited[i][j] = true;
                }
            }
        }

        cnt = Integer.parseInt(br.readLine());
        bfs(N, M, pq);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void bfs(int n, int m, PriorityQueue<Field> pq) {
        int[] dy = {-1, 0, 0, 1};
        int[] dx = {0, -1, 1, 0};

        while (!pq.isEmpty()) {
            Field field = pq.poll();
            cnt--;
            sb.append(field.row + 1).append(" ").append(field.col + 1).append("\n");
            if (cnt == 0) {
                return;
            }

            int row = field.row;
            int col = field.col;

            for (int i = 0; i < 4; i++) {
                int y = row + dy[i];
                int x = col + dx[i];

                if (y < 0 || y >= n || x < 0 || x >= m || isVisited[y][x]) {
                    continue;
                }
                isVisited[y][x] = true;
                pq.add(new Field(y, x, map[y][x]));
            }
        }
    }
}

class Field implements Comparable<Field>{
    int row;
    int col;
    int cost;

    public Field(int row, int col, int cost) {
        this.row = row;
        this.col = col;
        this.cost = cost;
    }

    @Override
    public int compareTo(Field o) {
        return o.cost - this.cost;
    }
}