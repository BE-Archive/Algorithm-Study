import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_17780_새로운_게임 {

    static int N, K, T;
    static int[][] board;
    static ArrayList<int[]>[][] boardp;
    static int[][] piece;
    static ArrayList<int[]>[] pieceg;

    static int[][] d = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N + 2][N + 2];
        boardp = new ArrayList[N + 2][N + 2];

        for (int i = 0; i <= N + 1; i++) {
            board[i][0] = 2;
            board[0][i] = 2;
            board[i][N + 1] = 2;
            board[N + 1][i] = 2;
            boardp[i][0] = new ArrayList<>();
            boardp[0][i] = new ArrayList<>();
            boardp[i][N + 1] = new ArrayList<>();
            boardp[N + 1][i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                boardp[i][j] = new ArrayList<>();
            }
        }

        piece = new int[K][];
        pieceg = new ArrayList[K];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken()) - 1;
            if (dir == 1) {
                dir = 2;
            } else if (dir == 2) {
                dir = 1;
            }

            piece[i] = new int[] { i, x, y, dir };
            boardp[x][y].add(piece[i]);
            pieceg[i] = boardp[x][y];
        }

        T = 0;
        while (++T <= 1000) {
            for (int i = 0; i < K; i++) {
                if (i != pieceg[i].get(0)[0]) {
                    continue;
                }
                int x = piece[i][1];
                int y = piece[i][2];
                int dir = piece[i][3];
                int nx = x + d[dir][0];
                int ny = y + d[dir][1];
                if (board[nx][ny] == 2) {
                    dir += 2;
                    dir %= 4;
                    nx = x + d[dir][0];
                    ny = y + d[dir][1];
                    piece[i][3] = dir;
                }

                switch (board[nx][ny]) {
                    case 0:
                        boardp[nx][ny].addAll(boardp[x][y]);
                        for (int[] p : boardp[x][y]) {
                            p[1] = nx;
                            p[2] = ny;
                            pieceg[p[0]] = boardp[nx][ny];
                        }
                        boardp[x][y] = new ArrayList<>();
                        break;
                    case 1:
                        Collections.reverse(boardp[x][y]);
                        boardp[nx][ny].addAll(boardp[x][y]);
                        for (int[] p : boardp[x][y]) {
                            p[1] = nx;
                            p[2] = ny;
                            pieceg[p[0]] = boardp[nx][ny];
                        }
                        boardp[x][y] = new ArrayList<>();
                        break;
                }

                if (pieceg[i].size() >= 4) {
                    System.out.println(T);
                    System.exit(0);
                }
            }
        }

        System.out.println(-1);
    }
}
