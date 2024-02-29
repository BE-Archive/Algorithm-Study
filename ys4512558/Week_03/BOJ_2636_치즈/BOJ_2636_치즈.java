import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[][] map;
    static boolean[][] isVisited;
    static boolean[][] isBorder;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N, M;
    static int cheese = 0;
    static int preCheese = 0;
    static int year = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) cheese++;
            }
        }
        preCheese = cheese;

        while (true) {
            if(cheese == 0) break;
            preCheese = cheese;
            isVisited = new boolean[N][M];
            isBorder = new boolean[N][M];
            bfs(0, 0);
        }
        sb.append(year).append("\n");
        sb.append(preCheese);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void bfs(int r, int c){
        year++;
        Queue<Point> queue = new ArrayDeque<>();
        Queue<Point> border = new ArrayDeque<>();
        queue.offer(new Point(r, c));
        isVisited[r][c] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            for (int i = 0; i < 4; i++) {
                int x = p.x + dx[i];
                int y = p.y + dy[i];

                if (x < 0 || x >= N || y < 0 || y >= M || isVisited[x][y]) {
                    continue;
                }
                if (map[x][y] == 1) {
                    border.offer(new Point(x, y));
                    isBorder[x][y] = true;
                } else {
                    queue.offer(new Point(x, y));
                }
                isVisited[x][y] = true;
            }
        }
        meltCheese(border);
    }

    private static void meltCheese(Queue<Point> border) {
        while (!border.isEmpty()) {
            Point p = border.poll();
            map[p.x][p.y] = 0;
            cheese--;
        }
    }
}

class Point{
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}