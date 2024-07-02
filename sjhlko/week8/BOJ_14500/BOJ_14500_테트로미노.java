package sjhlko.week8.BOJ_14500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500_테트로미노 {
    //https://www.acmicpc.net/problem/14500
    //테트로미노
    static int N;
    static int M;
    static int answer = 0;
    static boolean[][] visited;

    //한붓그리기로 해결할 수 있는것을 해결한다
    public static void dfs(int[][] map, int i, int j, int count, int ans) {
        if (count == 4) {
            answer = Math.max(ans, answer);
            return;
        }
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        visited[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int nextI = i + dx[k];
            int nextJ = j + dy[k];
            if (nextI < 0 || nextJ < 0 || nextI > N - 1 || nextJ > M - 1)
                continue;
            if (visited[nextI][nextJ]) continue;
            visited[nextI][nextJ] = true;
            dfs(map, nextI, nextJ, count + 1, ans + map[nextI][nextJ]);
            visited[nextI][nextJ] = false;
        }
        visited[i][j] = false;
    }

    public static void checkThatShape1(int[][] map, int i, int j) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int ans = map[i][j];
        //십자가 모양
        for (int k = 0; k < 4; k++) {
            ans += map[i + dx[k]][j + dy[k]];
        }
        //십자가 모양에서 끝점 하나를 빼서 ㅗ 으로 만든다
        for (int k = 0; k < 4; k++) {
            answer = Math.max(answer, ans - map[i + dx[k]][j + dy[k]]);
        }
    }

    //벽에 딱붙어있는 ㅗ 를 만든다
    public static void checkThatShape2(int[][] map) {
        for (int k = 1; k < M - 1; k++) {
            int ans = map[0][k] + map[0][k - 1] + map[0][k + 1] + map[1][k];
            answer = Math.max(answer, ans);
            ans = map[N - 1][k] + map[N - 1][k - 1] + map[N - 1][k + 1] + map[N - 2][k];
            answer = Math.max(answer, ans);
        }
        for (int k = 1; k < N - 1; k++) {
            int ans = map[k][0] + map[k - 1][0] + map[k + 1][0] + map[k][1];
            answer = Math.max(answer, ans);
            ans = map[k][M - 1] + map[k - 1][M - 1] + map[k + 1][M - 1] + map[k][M - 2];
            answer = Math.max(answer, ans);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(map, i, j, 1, map[i][j]);
                if (i != 0 && j != 0 && i != N - 1 && j != M - 1)
                    checkThatShape1(map, i, j);
            }
        }
        checkThatShape2(map);
        System.out.println(answer);
    }
}