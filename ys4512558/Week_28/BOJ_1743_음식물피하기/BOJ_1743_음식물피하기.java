import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};
    static int N, M;
    static boolean[][] map, isv;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        isv = new boolean[N][M];

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = true;
            list.add(new int[]{r, c});
        }
        int max = 0;
        for (int[] p : list) {
            if(isv[p[0]][p[1]]) continue;
            max = Math.max(max, dfs(p[0], p[1]));
        }
        System.out.println(max);
    }

    private static int dfs(int x, int y) {
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isOutRange(nx, ny)) {
                continue;
            }
            isv[nx][ny] = true;
            sum += dfs(nx, ny) + 1;
        }
        return sum;
    }

    public static boolean isOutRange(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= M || isv[x][y] || !map[x][y]) {
            return true;
        }
        return false;
    }
}