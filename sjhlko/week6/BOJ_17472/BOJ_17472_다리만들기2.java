package sjhlko.week6.BOJ_17472;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17472_다리만들기2 {
    // https://www.acmicpc.net/problem/17472
    // 다리 만들기 2

    static int N, M, iCount;
    static int[][] map;
    static int[] parent, height;
    static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
    static PriorityQueue<Edge> edges = new PriorityQueue<>();

    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }

    static void bfs(int i, int j, int index) {
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new ArrayDeque<>();
        visited[i][j] = true;
        queue.offer(new int[] { i, j });
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            map[now[0]][now[1]] = index;
            for (int k = 0; k < 4; k++) {
                int nextI = now[0] + dx[k];
                int nextJ = now[1] + dy[k];
                if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= M)
                    continue;
                if (visited[nextI][nextJ])
                    continue;
                if (map[nextI][nextJ] != 1)
                    continue;
                visited[nextI][nextJ] = true;
                queue.offer(new int[] { nextI, nextJ });
            }
        }
    }

    static void indexingIslands() {
        int index = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 1)
                    continue;
                bfs(i, j, index++);
            }
        }
        iCount = index;
    }

    static int find(int x) {
        if (parent[x] < 0)
            return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py)
            return false;
        if (height[py] > height[px]) {
            int tmp = px;
            px = py;
            py = tmp;
        }
        if (height[px] == height[py])
            height[px]++;
        parent[px] += parent[py];
        parent[py] = px;
        return true;
    }

    static void makeEdge(int index) {
        int[] visited = new int[iCount];
        Arrays.fill(visited, Integer.MAX_VALUE);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != index)
                    continue;
                for (int k = 0; k < 4; k++) {
                    int count = 0;
                    int nextI = i;
                    int nextJ = j;
                    while (true) {
                        nextI += dx[k];
                        nextJ += dy[k];
                        if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= M)
                            break;
                        if (map[nextI][nextJ] == index)
                            break;
                        if (map[nextI][nextJ] != 0) {
                            if (count == 1)
                                break;
                            if (visited[map[nextI][nextJ]] <= count)
                                break;
                            visited[map[nextI][nextJ]] = count;
                            edges.add(new Edge(map[i][j], map[nextI][nextJ], count));
                            break;
                        }
                        count++;
                    }
                }
            }
        }
    }

    static int connect() {
        for (int i = 2; i < iCount; i++) {
            makeEdge(i);
        }
        parent = new int[iCount];
        height = new int[iCount];
        Arrays.fill(parent, -1);
        int ans = 0;
        int count = 0;
        while (!edges.isEmpty()) {
            if (count == iCount - 3)
                return ans;
            Edge e = edges.poll();
            if (union(e.start, e.end)) {
                ans += e.cost;
                count++;
            }
        }
        return count == iCount - 3 ? ans : -1;
    }

    static int solution() {
        indexingIslands();
        return connect();

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution());
    }
}
