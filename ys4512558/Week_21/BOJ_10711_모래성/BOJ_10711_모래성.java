package com.javajava.week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ10711 {
    static int[][] map;
    static int[][] count;
    static Queue<Pair> queue = new ArrayDeque<Pair>();
    static int N, M;

    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        count = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = input.charAt(j);
                if (c == '.') {
                    map[i][j] = 0;
                    queue.offer(new Pair(i, j));
                } else {
                    map[i][j] = c - '0';
                }
            }
        }
        int time = 0;
        while (true) {
            if(simulation()) break;
            time++;
        }
        System.out.println(time);
    }

    private static boolean simulation() {
        Queue<Pair> nextQueue = new ArrayDeque<>();
        while (!queue.isEmpty()) {
            Pair p = queue.poll();

            for (int i = 0; i < 8; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 0) continue;
                if (++count[nx][ny] == map[nx][ny]) {
                    map[nx][ny] = 0;
                    nextQueue.offer(new Pair(nx, ny));
                }
            }
        }

        if(nextQueue.isEmpty()) return true;
        queue = nextQueue;
        return false;
    }
}

class Pair{
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}