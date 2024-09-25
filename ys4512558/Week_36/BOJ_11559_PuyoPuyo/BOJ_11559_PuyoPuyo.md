# 소스코드

```Java
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

```

# 소요시간

1시간

# 알고리즘

> 시뮬레이션

# 풀이

# BOJ 11559 PuyoPuyo

1. 초기에 색별 Queue를 매핑하기 위한 맵을 만들고 초기화한다.
2. 맵 정보를 입력받으며, map[i][j] != '.'이라면 해당 색의 큐에 넣는다.
3. 시뮬레이션 시작 (각 색에 대한 큐를 통해 하나씩 꺼내서 해당 뿌요로 부터 4개이상 연결이 되는지 확인)
   1. BFS를 통해 수행할 때 방문하는 뿌요의 위치와 색을 삭제를 위한 새로운 큐(removeQueue)에 담는다.
   2. 만약 4개 이상 연결되면 removeQueue에 있는 모든 뿌요를 삭제한다.
   3. 삭제로직은 map[][]위치를 '.'로 바꿔준다.
   4. 위 과정을 색별로 다 해보며, 이전 탐색에서 사라진 뿌요가 남아있을 수 있으므로 isv를 색에 대해 하나만 관리하도록 한다.
4. 모든 색에 대해 1회차 시뮬레이션을 수행하였을 때 하나라도 폭파되었다면 공중에 뜬 뿌요를 내려줘야한다.
   1. 내려주는 작업은 열에 대해 행을 밑에서부터 확인하며 map[i][j] != '.'인 경우를 순차적으로 관리하기 위해 Queue에 넣는다.
   2. Queue에 넣어주며 map[i][j] = '.'으로 다시 초기화한다.
   3. 행이 11~0까지 수행이 끝나면 Queue에는 색이 밑에서부터 순서대로 들어가있으므로  
       Queue에서 하나씩 꺼내 이를 다시 밑에서부터 차례로 초기화해준다.
5. 이후, 카운트를 하나 올린 후 다음 시뮬레이션을 진행했을 때 하나도 터지지 않는다면 종료한다.

---
