import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        int[][] ans = new int[N][M];
        int row = 0, col = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 2) {
                    row = i;
                    col = j;
                }
            }
        }

        bfs(arr, ans, row, col);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(arr[i][j] == 1 && ans[i][j] == 0){
                    sb.append(-1).append(" ");
                    continue;
                }
                sb.append(ans[i][j]).append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void bfs(int[][] arr, int[][] ans, int row, int col) {
        boolean[][] isv = new boolean[N][M];
        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, -1, 0, 1};

        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(row, col, 0));
        isv[row][col] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int r = p.row;
            int c = p.col;
            int b = p.breadth;

            for (int i = 0; i < 4; i++) {
                int x = r + dx[i];
                int y = c + dy[i];
                if(x < 0 || x >= N || y < 0 || y >= M) continue;
                if(arr[x][y] != 1 || isv[x][y]) continue;
                queue.offer(new Point(x, y, b + 1));
                isv[x][y] = true;
                ans[x][y] = b + 1;
            }
        }
    }
}

class Point{
    int row, col, breadth;

    public Point(int row, int col, int breadth) {
        this.row = row;
        this.col = col;
        this.breadth = breadth;
    }
}