package Week_22.BOJ_22255;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_22255 {

    static int n, m;

    static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }

    static class Node implements Comparable {
        int y;
        int x;
        int d;
        int move;

        Node(int y, int x, int d, int move) {
            this.y = y;
            this.x = x;
            this.d = d;
            this.move = move;
        }

        @Override
        public int compareTo(Object o) {
            return this.d - ((Node) o).d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int sy = Integer.parseInt(st.nextToken()) - 1;
        int sx = Integer.parseInt(st.nextToken()) - 1;
        int ey = Integer.parseInt(st.nextToken()) - 1;
        int ex = Integer.parseInt(st.nextToken()) - 1;

        int[][][] arr = new int[4][n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[0][i][j] = Integer.parseInt(st.nextToken());
                arr[1][i][j] = Integer.MAX_VALUE;
                arr[2][i][j] = Integer.MAX_VALUE;
                arr[3][i][j] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(sy, sx, 0, 0));
        arr[1][sy][sx] = 0;
        Node curr;
        int ny, nx;
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        while (!pq.isEmpty()) {
            curr = pq.poll();
            if (curr.y == ey && curr.x == ex) {
                System.out.println(curr.d);
                return;
            }
            switch ((curr.move + 1) % 3) {
                case 0:
                    for (int i = 0; i < 4; i++) {
                        ny = curr.y + dy[i];
                        nx = curr.x + dx[i];
                        if (isValid(ny, nx) && arr[0][ny][nx] != -1 && arr[1][ny][nx] > curr.d + arr[0][ny][nx]) {
                            arr[1][ny][nx] = curr.d + arr[0][ny][nx];
                            pq.offer(new Node(ny, nx, arr[1][ny][nx], curr.move + 1));
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 2; i++) {
                        ny = curr.y + dy[i];
                        nx = curr.x + dx[i];
                        if (isValid(ny, nx) && arr[0][ny][nx] != -1 && arr[2][ny][nx] > curr.d + arr[0][ny][nx]) {
                            arr[2][ny][nx] = curr.d + arr[0][ny][nx];
                            pq.offer(new Node(ny, nx, arr[2][ny][nx], curr.move + 1));
                        }
                    }
                    break;
                case 2:
                    for (int i = 2; i < 4; i++) {
                        ny = curr.y + dy[i];
                        nx = curr.x + dx[i];
                        if (isValid(ny, nx) && arr[0][ny][nx] != -1 && arr[3][ny][nx] > curr.d + arr[0][ny][nx]) {
                            arr[3][ny][nx] = curr.d + arr[0][ny][nx];
                            pq.offer(new Node(ny, nx, arr[3][ny][nx], curr.move + 1));
                        }
                    }
                    break;
            }
        }
        System.out.println(-1);
    }
}
