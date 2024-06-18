package com.javajava.week05;

import java.io.*;
import java.util.*;

public class BOJ19238 {
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Map<Pair, Pair> passengers;
    static int N, M, K;
    static Pair start;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        start = new Pair(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);

        passengers = new HashMap<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int destX = Integer.parseInt(st.nextToken()) - 1;
            int destY = Integer.parseInt(st.nextToken()) - 1;
            passengers.put(new Pair(x, y, 0), new Pair(destX, destY, 0));
        }

        sb.append(solve());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int solve() {
        while (!passengers.isEmpty()) {
            Pair dest = bfs1();
            if (dest == null) return -1;
            if (!bfs2(dest)) return -1;

        }
        return K;
    }

    private static boolean bfs2(Pair dest) {
        //승객에게 도착한 후 목적지로 이동 시키기
        boolean[][] isv = new boolean[N][N];
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(start.x, start.y, 0));
        isv[start.x][start.y] = true;
        if (start.x == dest.x && start.y == dest.y) return true;


        while (!queue.isEmpty()) {
            Pair p = queue.poll();

            if(K < p.breadth) return false;
            if (p.equals(dest)) {
                K += p.breadth;// K - (p.breadth + 1) + (2 * (p.breadth + 1));
                start = p;
                return true;
            }
            for (int i = 0; i < 4; i++) {
                int x = p.x + dx[i];
                int y = p.y + dy[i];

                if (x < 0 || x >= N || y < 0 || y >= N || isv[x][y] || map[x][y] == 1) continue;

                isv[x][y] = true;
                queue.offer(new Pair(x, y, p.breadth + 1));
            }
        }
        return false;
    }

    private static Pair bfs1() {
        boolean[][] isv = new boolean[N][N];
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(start.x, start.y, 0));
        isv[start.x][start.y] = true;

        int size = 0; //동일 너비 처리
        Pair passenger = null;
        Pair dest = null;
        Pair pass = passengers.get(new Pair(start.x, start.y, 0));
        if(pass != null) {
            dest = pass;
            passengers.remove(new Pair(start.x, start.y, 0));
            return dest;
        }
        while (!queue.isEmpty()) {
            size = queue.size();
            while (size-- > 0) {
                Pair p = queue.poll();

                if (K < p.breadth) return null;
                Pair check = passengers.get(p);
                if(check != null){
                    if (passenger == null) {
                        passenger = p;
                        dest = check;
                    } else {
                        if (passenger.x > p.x || (passenger.x == p.x && passenger.y > p.y)) {
                            passenger = p;
                            dest = check;
                        }
                    }
                }
                for (int i = 0; i < 4; i++) {
                    int x = p.x + dx[i];
                    int y = p.y + dy[i];

                    if (x < 0 || x >= N || y < 0 || y >= N || isv[x][y] || map[x][y] == 1) continue;

                    Pair passen = new Pair(x, y, p.breadth + 1);
                    isv[x][y] = true;
                    queue.offer(passen);
                }
            }
            if (passenger != null) {
                K -= passenger.breadth;
                passengers.remove(passenger);
                start.x = passenger.x;
                start.y = passenger.y;
                break;
            }
        }
        if(passenger == null) return null;
        return dest;
    }
}

class Pair implements Comparable<Pair>{
    int x, y, breadth;

    public Pair(int x, int y, int breadth) {
        this.x = x;
        this.y = y;
        this.breadth = breadth;
    }

    @Override
    public int compareTo(Pair o) {
        if (this.x == o.x) {
            return Integer.compare(this.y, o.y);
        }
        return Integer.compare(this.x, o.x);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return x == pair.x && y == pair.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
