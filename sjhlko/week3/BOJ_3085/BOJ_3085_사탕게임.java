package sjhlko.week3.BOJ_3085;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_3085_사탕게임 {
    //https://www.acmicpc.net/problem/3085
    //사탕게임
    static int N, ans = 1;
    static char[][] map;
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};

    static boolean isValid(int i, int j) {
        return i >= 0 && j >= 0 && i < N && j < N;
    }

    static void solution(int i, int j, int startDirection) {
        int count = 1;
        boolean isChanged = false;
        int nextI = i, nextJ = j;
        while (true) {
            nextI += dx[startDirection];
            nextJ += dy[startDirection];
            ans = Math.max(ans, count);
            if (!isValid(nextI, nextJ)) return;
            if (map[nextI][nextJ] == map[i][j]) {
                count++;
                continue;
            }
            if (isChanged) return;
            for (int k = 0; k < 4; k++) {
                int nnI = nextI + dx[k];
                int nnJ = nextJ + dy[k];
                if (!isValid(nnI, nnJ)) continue;
                if (map[nnI][nnJ] != map[i][j]) continue;
                if (k + startDirection == 3) continue;
                if (k == startDirection) {
                    ans = Math.max(ans, count + 1);
                    continue;
                }
                isChanged = true;
                count++;
                break;
            }
            if (!isChanged) break;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String s = bf.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 4; k++) {
                    solution(i, j, k);
                }
            }
        }
        System.out.println(ans);
    }
}
