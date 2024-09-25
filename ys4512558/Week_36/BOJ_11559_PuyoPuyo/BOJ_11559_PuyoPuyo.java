package com.javajava.Week36;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ11559 {

    static int[] dx = {-1 ,1 ,0, 0}, dy = {0, 0, 1, -1};
    static int R = 12, C = 6;
    static Map<Character, Queue<Puyo>> puyoMap;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        puyoMap = new HashMap<>();
        puyoMap.put('R', new ArrayDeque<>());
        puyoMap.put('G', new ArrayDeque<>());
        puyoMap.put('B', new ArrayDeque<>());
        puyoMap.put('P', new ArrayDeque<>());
        puyoMap.put('Y', new ArrayDeque<>());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == '.') continue;
                Queue<Puyo> puyos = puyoMap.get(map[i][j]);
                puyos.offer(new Puyo(map[i][j], i, j));
            }
        }

        int cnt = 0;
        while (true) {
            boolean flag = false;
            for (Character key : puyoMap.keySet()) {
                flag |= boom(puyoMap.get(key));
            }
            if (!flag) break;
            down();
            cnt++;
        }
        System.out.println(cnt);
    }

    private static void down() {
        Queue<Character> queue = new ArrayDeque<>();
        for (int i = 0; i < C; i++) {
            for (int j = R - 1; j >= 0; j--) {
                //.이 아닌 색을 밑에서 부터 찾아서 큐에 순서대로 넣기
                if(map[j][i] == '.') continue;
                queue.offer(map[j][i]);
                //해당 위치 색을 .으로 변경
                map[j][i] = '.';
            }
            //맨 밑에서부터 색깔별로 쌓기
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                Character color = queue.poll();
                map[R - j - 1][i] = color;
                Queue<Puyo> next = puyoMap.getOrDefault(color, new ArrayDeque<>());
                next.offer(new Puyo(color, R - j - 1, i));
                puyoMap.put(color, next);
            }
        }
    }

    private static boolean boom(Queue<Puyo> queue) {
        boolean[][] isv = new boolean[R][C];
        boolean flag = false;
        while (!queue.isEmpty()){
            Puyo puyo = queue.poll();
            //이미 이전에 탐색되었으면 해보지말기
            if(isv[puyo.x][puyo.y]) continue;
            flag |= bfs(puyo, isv);
        }
        return flag;
    }

    private static boolean bfs(Puyo start, boolean[][] isv) {
        Queue<Puyo> queue = new ArrayDeque<>();
        queue.add(start);
        Queue<Puyo> removeQueue = new ArrayDeque<>();
        removeQueue.add(start);
        int cnt = 0;
        while (!queue.isEmpty()) {
            Puyo puyo = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = puyo.x + dx[i];
                int ny = puyo.y + dy[i];

                if(isOutRange(nx, ny, isv, puyo.color)) continue;
                removeQueue.add(new Puyo(puyo.color, nx, ny));
                queue.add(new Puyo(puyo.color, nx, ny));
                isv[nx][ny] = true;
                cnt++;
            }
        }
        //개수가 4이상이면 없애기
        if (cnt >= 4) {
            remove(removeQueue);
            return true;
        }
        return false;
    }

    private static void remove(Queue<Puyo> removeQueue) {
        while (!removeQueue.isEmpty()) {
            Puyo puyo = removeQueue.poll();

            map[puyo.x][puyo.y] = '.';
        }
    }

    private static boolean isOutRange(int x, int y, boolean[][] isv, char color) {
        if(x < 0 || y < 0 || x >= R || y >= C || isv[x][y] || map[x][y] != color) return true;
        return false;
    }


}

class Puyo {
    char color;
    int x, y;

    public Puyo(char color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }
}