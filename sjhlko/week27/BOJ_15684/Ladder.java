package sjhlko.week27.BOJ_15684;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ladder {
    //https://www.acmicpc.net/problem/15684
    //사다리 조작
    static int N, M, H;
    static boolean[][] map;
    static int ans = 4;

    static boolean isValid() {
        for (int i = 0; i < N; i++) {
            int end = i;
            for (int j = 0; j < H; j++) {
                if (end > 0 && map[j][end - 1]) end--;
                else if (map[j][end]) end++;
            }
            if (end != i) return false;
        }
        return true;
    }

    static boolean solution(int count, int max, int start) {
        if (count == max) {
            if (isValid()) {
                ans = count;
                return true;
            }
            return false;
        }
        for (int i = start; i < H; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (map[i][j]) continue;
                map[i][j] = true;
                if(solution(count + 1, max, i)) return true;
                map[i][j] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new boolean[H][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            map[a][b] = true;
        }
        if (isValid()) System.out.println(0);
        else {
            for (int i = 0; i < 4; i++) {
                if (solution(0, i, 0)) {
                    System.out.println(i);
                    return;
                }
            }
            System.out.println(-1);
        }
    }
}
