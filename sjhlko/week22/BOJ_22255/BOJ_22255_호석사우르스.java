package sjhlko.week22.BOJ_22255;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_22255_호석사우르스 {
    //https://www.acmicpc.net/problem/22255
    //호석 사우르스
    static int N, M, si, sj, ei, ej;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    static class Info implements Comparable<Info> {
        int i, j;
        int type;
        int cost;

        public Info(int i, int j, int type, int cost) {
            this.i = i;
            this.j = j;
            this.type = type;
            this.cost = cost;
        }

        @Override
        public int compareTo(Info o) {
            return cost - o.cost;
        }
    }

    static boolean isValid(int i, int j) {
        return i >= 0 && j >= 0 && i < N && j < M && map[i][j] >= 0;
    }

    static int solution() {
        boolean[][][] isVisited = new boolean[N][M][3];
        isVisited[si][sj][1] = true;
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(si, sj, 1, map[si][sj]));
        while (!pq.isEmpty()) {
            Info now = pq.poll();
            int[] dir = null;
            switch (now.type) {
                case 0:
                    dir = new int[]{0, 1, 2, 3};
                    break;
                case 1:
                    dir = new int[]{0, 1};
                    break;
                case 2:
                    dir = new int[]{2, 3};
            }
            for (int i = 0; i < dir.length; i++) {
                int nextI = now.i + dx[dir[i]];
                int nextJ = now.j + dy[dir[i]];
                if (!isValid(nextI, nextJ)) continue;
                if (isVisited[nextI][nextJ][(now.type + 1) % 3]) continue;
                isVisited[nextI][nextJ][(now.type + 1) % 3] = true;
                if (nextI == ei && nextJ == ej) return now.cost;
                pq.add(new Info(nextI, nextJ, (now.type + 1) % 3, now.cost + map[nextI][nextJ]));
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        st = new StringTokenizer(bf.readLine());
        si = Integer.parseInt(st.nextToken()) - 1;
        sj = Integer.parseInt(st.nextToken()) - 1;
        ei = Integer.parseInt(st.nextToken()) - 1;
        ej = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution());
    }
}
