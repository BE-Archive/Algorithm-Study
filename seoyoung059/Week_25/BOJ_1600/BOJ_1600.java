package Week_25.BOJ_1600;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_1600 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());

        int[] hy = {-2, -2, -1, -1, +1, +1, +2, +2};
        int[] hx = {-1, +1, -2, +2, -2, +2, -1, +1};
        int[] dy = {0, 0, -1, +1};
        int[] dx = {-1, +1, 0, 0};

        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[][] board = new int[h][w];

        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                board[i][j] = -Integer.parseInt(st.nextToken());
            }
        }

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0, 0});
        int qSize, ny, nx;
        int[] curr;
        int cnt = 0, answer = -1;
        loop:
        while (!q.isEmpty()) {
            qSize = q.size();
            while (qSize-- > 0) {
                curr = q.pollFirst();
                if (curr[0] == h - 1 && curr[1] == w - 1) {
                    answer = cnt;
                    break loop;
                }
                for (int i = 0; i < 4; i++) {
                    ny = curr[0] + dy[i];
                    nx = curr[1] + dx[i];
                    if (ny < 0 || h <= ny || nx < 0 || w <= nx) continue;
                    if (board[ny][nx] == -1 || (board[ny][nx] & (1 << curr[2])) > 0) continue;
                    board[ny][nx] |= (1 << curr[2]);
                    q.offerLast(new int[]{ny, nx, curr[2]});
                }

                if (curr[2] == k) continue;
                for (int i = 0; i < 8; i++) {
                    ny = curr[0] + hy[i];
                    nx = curr[1] + hx[i];
                    if (ny < 0 || h <= ny || nx < 0 || w <= nx) continue;
                    if (board[ny][nx] == -1 || (board[ny][nx] & (1 << (curr[2] + 1))) > 0) continue;
                    board[ny][nx] |= (1 << (curr[2] + 1));
                    q.offerLast(new int[]{ny, nx, curr[2] + 1});
                }
            }
            cnt++;
        }

        System.out.println(answer);
    }
}