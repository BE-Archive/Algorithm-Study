package sjhlko.week2.boj_30024;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
public class BOJ_30024_옥수수밭 {
    static int N, M, K;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static PriorityQueue<CornInfo> canPick = new PriorityQueue<>();
    static boolean[][] alreadyAdded;


    static class CornInfo implements Comparable<CornInfo> {
        int i, j;
        int value;

        public CornInfo(int i, int j, int value) {
            this.i = i;
            this.j = j;
            this.value = value;
        }

        @Override
        public int compareTo(CornInfo o) {
            return o.value - this.value;
        }
    }

    static void parseInput() {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            alreadyAdded = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (i == 0 || j == 0 || i == N - 1 || j == M - 1) {
                        canPick.add(new CornInfo(i, j, map[i][j]));
                        alreadyAdded[i][j] = true;
                    }
                }
            }
            K = Integer.parseInt(bf.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void solution() {
        int count = 0;
        StringBuilder answer = new StringBuilder();
        while (count < K) {
            CornInfo now = canPick.poll();
            answer.append(now.i + 1).append(" ").append(now.j + 1).append("\n");
            for (int i = 0; i < 4; i++) {
                int nextI = now.i + dx[i];
                int nextJ = now.j + dy[i];
                if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= M) continue;
                if (alreadyAdded[nextI][nextJ]) continue;
                alreadyAdded[nextI][nextJ] = true;
                canPick.add(new CornInfo(nextI, nextJ, map[nextI][nextJ]));
            }
            count++;
        }
        System.out.println(answer);
    }

    public static void main(String[] args) {
        parseInput();
        solution();
    }
}
