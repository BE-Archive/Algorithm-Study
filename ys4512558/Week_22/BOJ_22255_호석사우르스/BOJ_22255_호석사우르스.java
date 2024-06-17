package com.javajava.week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ22255 {
    static int[][] dx = {
            {-1, 1, 0, 0},
            {-1, 1},
            {0, 0}
    };
    static int[][] dy = {
            {0, 0, -1, 1},
            {0, 0},
            {-1, 1}
    };
    static int[][] map;
    static int[][][] costMap;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        costMap = new int[3][N][M];

        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken()) - 1;
        int sy = Integer.parseInt(st.nextToken()) - 1;
        int ex = Integer.parseInt(st.nextToken()) - 1;
        int ey = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                for (int k = 0; k < 3; k++) {
                    costMap[k][i][j] = Integer.MAX_VALUE;
                }
            }
        }
        System.out.println(dijkstra(sx, sy, ex, ey));
    }

    private static int dijkstra(int sx, int sy, int ex, int ey) {
        PriorityQueue<Saurus> pq = new PriorityQueue<>();
        pq.offer(new Saurus(sx, sy, 0, 1));

        while (!pq.isEmpty()) {
            Saurus saurus = pq.poll();

            int mod = saurus.move % 3;
            for (int i = 0; i < dx[mod].length; i++) {
                int nx = saurus.x + dx[mod][i];
                int ny = saurus.y + dy[mod][i];

                if(saurus.x == ex && saurus.y == ey) return saurus.cost;
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == -1) continue;
                if(costMap[mod][nx][ny] > saurus.cost + map[nx][ny]){
                    costMap[mod][nx][ny] = saurus.cost + map[nx][ny];
                    pq.offer(new Saurus(nx, ny, costMap[mod][nx][ny], saurus.move + 1));
                }
            }
        }
        return -1;
    }
}
class Saurus implements Comparable<Saurus>{
    int x, y, cost, move;

    public Saurus(int x, int y, int cost, int move) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.move = move;
    }

    @Override
    public int compareTo(Saurus o) {
        return Integer.compare(this.cost, o.cost);
    }
}