package sjhlko.week28.BOJ_1743;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class WatchOutFood {
    //https://www.acmicpc.net/problem/1743
    //음식물 피하기
    static int N, M, K;
    static boolean[][] map;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    static boolean isValid(int i, int j) {
        return i >= 0 && j >= 0 && i < N && j < M;
    }

    static int solution() {
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!map[i][j]) continue;
                Queue<int[]> queue = new ArrayDeque<>();
                queue.add(new int[]{i, j});
                map[i][j] = false;
                int tmpAns = 1;
                while (!queue.isEmpty()) {
                    int[] now = queue.poll();
                    for (int k = 0; k < 4; k++) {
                        int nextI = now[0] + dx[k];
                        int nextJ = now[1] + dy[k];
                        if (!isValid(nextI, nextJ)) continue;
                        if (!map[nextI][nextJ]) continue;
                        map[nextI][nextJ] = false;
                        queue.add(new int[]{nextI, nextJ});
                        tmpAns++;
                    }
                }
                ans = Math.max(ans, tmpAns);
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            map[a][b] = true;
        }
        System.out.println(solution());
    }
}
