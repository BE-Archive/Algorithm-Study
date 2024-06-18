package com.javajava.week05;

import java.io.*;
import java.util.*;

public class BOJ19238_Fail {
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Passenger> passengers;
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

        passengers = new ArrayList<>(M);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int destX = Integer.parseInt(st.nextToken()) - 1;
            int destY = Integer.parseInt(st.nextToken()) - 1;
            passengers.add(new Passenger(x, y, 0, destX, destY));
        }

        sb.append(findShort());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int findShort() {
        for (int i = 0; i < passengers.size(); i++) {
            if (!bfs()) return -1;
            i--;
        }
        return K;
    }

    private static boolean bfs() {
        boolean[][] isv = new boolean[N][N];
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(start.x, start.y, 0));
        isv[start.x][start.y] = true;

        int size = 0; //동일 너비 처리
        int breadth = 0;
        Passenger passenger = null;
        Loop: while (!queue.isEmpty()) {
            for (int i = 0; i < passengers.size(); i++) {
                if(passengers.get(i).x == start.x && passengers.get(i).y == start.y){
                    passenger = passengers.get(i);
                    break Loop;
                }
            }
            size = queue.size();
            while (size-- > 0) {
                Pair p = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int x = p.x + dx[i];
                    int y = p.y + dy[i];

                    if (x < 0 || x >= N || y < 0 || y >= N || isv[x][y] || map[x][y] == 1) continue;
                    for (int j = 0; j < passengers.size(); j++) {
                        Passenger pass = passengers.get(j);
                        if (x == pass.x && y == pass.y) {
                            if(K < (p.breadth + 1)) return false;
                            breadth = (p.breadth + 1);
                            if(passenger == null){
                                passenger = pass;
                            } else {
                                if(passenger.x == pass.x){
                                    passenger = passenger.y < pass.y ? passenger : pass;
                                } else{
                                    passenger = passenger.x < pass.x ? passenger : pass;
                                }
                            }
                        }
                    }
                    isv[x][y] = true;
                    queue.offer(new Pair(x, y, p.breadth + 1));
                }
            }
            if (passenger != null) {
                passengers.remove(passenger);
                K -= breadth;
                break;
            }
        }
        if(passenger == null) return false;

        //승객에게 도착한 후 목적지로 이동 시키기
        isv = new boolean[N][N];
        queue = new ArrayDeque<>();
        queue.offer(new Pair(passenger.x, passenger.y, 0));
        isv[passenger.x][passenger.y] = true;

        while (!queue.isEmpty()) {
            Pair p = queue.poll();

            for (int i = 0; i < 4; i++) {
                int x = p.x + dx[i];
                int y = p.y + dy[i];

                if (x < 0 || x >= N || y < 0 || y >= N || isv[x][y] || map[x][y] == 1) continue;
                if(K < 0) return false;
                if (x == passenger.destX && y == passenger.destY) {
                    if(K < (p.breadth + 1)) return false;
                    K += (p.breadth + 1);// K - (p.breadth + 1) + (2 * (p.breadth + 1));
                    start.x = x;
                    start.y = y;
                    return true;
                }
                isv[x][y] = true;
                queue.offer(new Pair(x, y, p.breadth + 1));
            }
        }
        return false;
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
}

class Passenger extends Pair{
    int destX, destY;

    public Passenger(int x, int y, int breadth, int destX, int destY) {
        super(x, y, breadth);
        this.destX = destX;
        this.destY = destY;
    }
}