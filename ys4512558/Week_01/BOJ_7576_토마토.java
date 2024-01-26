import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BOJ7576.prob7576();
    }
}

class BOJ7576 {
    public static void prob7576() throws IOException {
        class Point{
            int row;
            int col;
            int day;

            public Point(int row, int col, int day) {
                this.row = row;
                this.col = col;
                this.day = day;
            }
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        int max = 0;
        int tomato = 0;

        Queue<Point> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) {
                    queue.add(new Point(i, j, 0));
                } else if (num == 0) {
                    tomato++;
                }
                map[i][j] = num;
            }
        }

        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, -1, 0, 1};

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int row = point.row;
            int col = point.col;
            int day = point.day;

            for (int i = 0; i < 4; i++) {
                int y = row + dy[i];
                int x = col + dx[i];

                if (y < 0 || y >= N || x < 0 || x >= M || map[y][x] == -1 || map[y][x] == 1) {
                    continue;
                }
                map[y][x] = 1;
                tomato--;
                queue.add(new Point(y, x, day+1));
                max = Math.max(max, day + 1);
            }
        }

        sb.append(tomato == 0 ? max : -1);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
