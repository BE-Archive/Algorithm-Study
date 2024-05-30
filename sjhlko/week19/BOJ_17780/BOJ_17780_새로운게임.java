package sjhlko.week19.BOJ_17780;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17780_새로운게임 {
    //https://www.acmicpc.net/problem/17780
    //새로운 게임
    static final int WHITE = 0, RED = 1, BLUE = 2;
    static int N, K;
    static Deque<Info>[][] map;
    static List<Info> infos = new ArrayList<>();
    static int[][] color;
    static int[] dx = {0, 0, -1, 1}, dy = {1, -1, 0, 0};

    static class Info {
        int index;
        int i, j;
        int direction;

        public Info(int index, int i, int j, int direction) {
            this.index = index;
            this.i = i;
            this.j = j;
            this.direction = direction;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Info info = (Info) o;
            return index == info.index;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index);
        }
    }

    static int getColor(int i, int j) {
        if (i < 0 || i >= N || j < 0 || j >= N) return BLUE;
        return color[i][j];
    }

    static int getReverseDirection(int d) {
        if (d < 2) return 1 - d;
        return 5 - d;
    }

    static int solution() {
        int count = 0;
        while (++count < 1000) {
            for (Info now : infos) {
                if (!map[now.i][now.j].peekFirst().equals(now)) continue;
                int nowI = now.i, nowJ = now.j;
                int nextI = now.i + dx[now.direction];
                int nextJ = now.j + dy[now.direction];
                int nextColor = getColor(nextI, nextJ);
                switch (nextColor) {
                    case WHITE:
                        while (!map[nowI][nowJ].isEmpty()) {
                            Info info = map[nowI][nowJ].pollFirst();
                            info.i = nextI;
                            info.j = nextJ;
                            map[nextI][nextJ].addLast(info);
                        }
                        if (map[nextI][nextJ].size() >= 4) return count;
                        break;
                    case RED:
                        while (!map[nowI][nowJ].isEmpty()) {
                            Info info = map[nowI][nowJ].pollLast();
                            info.i = nextI;
                            info.j = nextJ;
                            map[nextI][nextJ].addLast(info);
                        }
                        if (map[nextI][nextJ].size() >= 4) return count;
                        break;
                    case BLUE:
                        int reverseDirection = getReverseDirection(now.direction);
                        now.direction = reverseDirection;
                        nextI = nowI +  dx[reverseDirection];
                        nextJ  = nowJ +  dy[reverseDirection];
                        nextColor = getColor(nextI, nextJ);
                        switch (nextColor) {
                            case WHITE:
                                while (!map[nowI][nowJ].isEmpty()) {
                                    Info info = map[nowI][nowJ].pollFirst();
                                    info.i = nextI;
                                    info.j = nextJ;
                                    map[nextI][nextJ].addLast(info);
                                }
                                if (map[nextI][nextJ].size() >= 4) return count;
                                break;
                            case RED:
                                while (!map[nowI][nowJ].isEmpty()) {
                                    Info info = map[nowI][nowJ].pollLast();
                                    info.i = nextI;
                                    info.j = nextJ;
                                    map[nextI][nextJ].addLast(info);
                                }
                                if (map[nextI][nextJ].size() >= 4) return count;
                                break;
                        }
                        break;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new Deque[N][N];
        color = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                color[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = new LinkedList<>();
            }
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            Info info = new Info(i, a, b, c);
            infos.add(info);
            map[a][b].add(info);
        }
        System.out.println(solution());
    }
}