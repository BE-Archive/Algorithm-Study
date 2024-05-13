package sjhlko.week16.BOJ_16928;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ16928_뱀과사다리게임 {
    //https://www.acmicpc.net/problem/16928
    //뱀과 사다리 게임
    static int N, M;
    static Map<Integer, Integer> snakeOrLadder = new HashMap<>();
    static boolean[] visited = new boolean[101];

    static int bfs() {
        int ans = Integer.MAX_VALUE;
        visited[1] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1, 0});
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            if (now[0] == 100) ans = Math.min(now[1], ans);
            for (int i = 1; i <= 6; i++) {
                int next = now[0] + i;
                if (next == 100) ans = Math.min(now[1] + 1, ans);
                if (next <= 0 || next > 100) continue;
                if (visited[next]) continue;
                if (snakeOrLadder.containsKey(next)) {
                    visited[next] = true;
                    int afterNext = next;
                    while (snakeOrLadder.containsKey(afterNext)){
                        visited[afterNext] = true;
                        afterNext = snakeOrLadder.get(afterNext);
                    }
                    if (visited[afterNext]) continue;
                    visited[afterNext] = true;
                    queue.add(new int[]{afterNext, now[1] + 1});
                    continue;
                }
                visited[next] = true;
                queue.add(new int[]{next, now[1] + 1});

            }
        }
        return ans;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            snakeOrLadder.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            snakeOrLadder.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        System.out.println(bfs());
    }
}
