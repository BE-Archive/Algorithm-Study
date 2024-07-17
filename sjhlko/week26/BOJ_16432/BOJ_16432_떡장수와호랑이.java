package sjhlko.week26.BOJ_16432;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16432_떡장수와호랑이 {
    //https://www.acmicpc.net/problem/16432
    //떡장수와 호랑이
    static int N;
    static List<Set<Integer>> infos = new ArrayList<>();

    static boolean init() {
        boolean[] visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (infos.get(i).size() > 1 || visited[i]) continue;
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(i);
            visited[i] = true;
            while (!queue.isEmpty()) {
                int now = queue.poll();
                for (int j = -1; j < 2; j += 2) {
                    int next = now + j;
                    if (next < 0 || next >= N) continue;
                    infos.get(next).removeAll(infos.get(now));
                    if(infos.get(next).isEmpty()) return false;
                    if(visited[next]) continue;
                    visited[next] = true;
                    if(infos.get(next).size()==1) queue.add(next);
                }
            }
        }
        return true;
    }

    static String solution() {
        StringBuilder sb = new StringBuilder();
        if(!init()) return "-1";
        int before = infos.get(0).stream().findAny().get();
        sb.append(before).append("\n");
        for (int i = 1; i < N; i++) {
            infos.get(i).remove(before);
            before = infos.get(i).stream().findAny().get();
            sb.append(before).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int c = Integer.parseInt(st.nextToken());
            infos.add(new HashSet<>());
            for (int j = 0; j < c; j++) {
                infos.get(i).add(Integer.valueOf(st.nextToken()));
            }
        }
        System.out.print(solution());
    }
}
