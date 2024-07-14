import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dxh = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dyh = {-2, -1, 1, 2, -2, -1, 1, 2};
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int[][] map;
    static boolean[][][] isv;
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        map = new int[M][N];
        isv = new boolean[M][N][K + 1];
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if(N == 1 && M == 1) {
            System.out.println(0);
        } else {
            System.out.println(bfs());
        }
    }

    private static int bfs() {
        Queue<Piece> queue = new ArrayDeque<>();
        queue.offer(new Piece(0, 0, K));
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            result++;
            while (size-- > 0) {
                Piece piece = queue.poll();

                if (piece.k > 0) {
                    for (int i = 0; i < 8; i++) {
                        int nx = piece.x + dxh[i];
                        int ny = piece.y + dyh[i];

                        if(nx == M - 1 && ny == N - 1) return result;
                        if (isOutRange(nx, ny) || isv[nx][ny][piece.k - 1] || map[nx][ny] == 1) continue;

                        isv[nx][ny][piece.k - 1] = true;
                        queue.offer(new Piece(nx, ny, piece.k - 1));
                    }
                }
                for (int i = 0; i < 4; i++) {
                    int nx = piece.x + dx[i];
                    int ny = piece.y + dy[i];

                    if(nx == M - 1 && ny == N - 1) return result;

                    if (isOutRange(nx, ny) || isv[nx][ny][piece.k] || map[nx][ny] == 1) continue;
                    isv[nx][ny][piece.k] = true;
                    queue.offer(new Piece(nx, ny, piece.k));
                }
            }
        }
        return -1;
    }

    private static boolean isOutRange(int x, int y) {
        if (x < 0 || y < 0 || x >= M || y >= N) return true;
        return false;
    }
}

class Piece {
    int x, y, k;

    public Piece(int x, int y, int k) {
        this.x = x;
        this.y = y;
        this.k = k; //남은 말의 이동 횟수
    }
}