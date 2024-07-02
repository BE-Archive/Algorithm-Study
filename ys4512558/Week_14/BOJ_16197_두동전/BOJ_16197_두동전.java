package BOJ16197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16197 {
    static char[][] board;
    static Coin[] coins = new Coin[2];
    static int N, M, min = Integer.MAX_VALUE;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int idx = 0;
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = input.charAt(j);
                if (board[i][j] == 'o') {
                    coins[idx++] = new Coin(i, j);
                }
            }
        }
        dfs(coins[0], coins[1], 1);
        System.out.println(flag ? min : -1);
    }

    public static void dfs(Coin coin1, Coin coin2, int depth) {
        if(depth > 10) return;

        for (int i = 0; i < 4; i++) {
            Coin nCoin1 = move(coin1.x, coin1.y, i);
            Coin nCoin2 = move( coin2.x, coin2.y, i);

            if (nCoin1 == null && nCoin2 == null) continue;
            if (nCoin1 == null || nCoin2 == null) {
                min = Math.min(min, depth);
                flag = true;
                return;
            }
            dfs(nCoin1, nCoin2, depth + 1);
        }
    }

    public static Coin move(int x, int y, int delta) {
        int nx = x + dx[delta];
        int ny = y + dy[delta];

        if (!isValid(nx, ny)) return null;
        if (board[nx][ny] == '#') return new Coin(x, y);
        return new Coin(nx, ny);
    }
    public static boolean isValid(int x, int y){
        if (x < 0 || y < 0 || x >= N || y >= M) return false;
        return true;
    }
}

class Coin{
    int x, y;

    public Coin(int x, int y) {
        this.x = x;
        this.y = y;
    }
}