import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_4485 {

    public static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int n;
    static Integer[][] map;
    static Integer[][] rupee; //손해액 기록 배열


    public static void BFS(int x, int y) {
        Queue<Pair> q = new LinkedList<>();
        rupee[0][0] = map[0][0];
        q.add(new Pair(x, y));

        while (!q.isEmpty()) {
            Pair now = q.poll();

            for (int i = 0; i < 4; i++) {
                int ix = now.x + dx[i];
                int iy = now.y + dy[i];
                if (0 <= ix && ix < n && 0 <= iy && iy < n && map[ix][iy]+rupee[now.x][now.y]<rupee[ix][iy]) {
                    rupee[ix][iy]=map[ix][iy]+rupee[now.x][now.y];
                    q.add(new Pair(ix, iy));
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());

        int trial = 1;
        while (n != 0) {
            map = new Integer[n][n];
            for (int i = 0; i < n; i++) {
                String[] s = bf.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(s[j]);
                }
            }

            rupee = new Integer[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    rupee[i][j] = Integer.MAX_VALUE;
                }
            }

            BFS(0, 0);
            System.out.println("Problem " + trial + ": " + rupee[n - 1][n - 1]);

            n = Integer.parseInt(bf.readLine());
            trial++;
        }
    }
}