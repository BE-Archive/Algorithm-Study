package sjhlko.week24.BOJ_21606;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_21606_아침산책 {
    //아침산책
    //https://www.acmicpc.net/problem/21606
    static int N, ans;
    static boolean[] out, visited, visitedOut;
    static List<List<Integer>> graph = new ArrayList<>();

    static void solution() {
        visitedOut = new boolean[N];
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            if (!out[i]) continue;
            if (visitedOut[i]) continue;
            visitedOut[i] = true;
            visited[i] = true;
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(i);
            int count = 0;
            while (!queue.isEmpty()) {
                int now = queue.poll();
                for (int j = 0; j < graph.get(now).size(); j++) {
                    int next = graph.get(now).get(j);
                    if (visited[next] || visitedOut[next]) continue;
                    visited[next] = true;
                    if (!out[next]) {
                        count++;
                        continue;
                    }
                    queue.add(next);
                    visitedOut[next] = true;
                }
            }
            ans += (count) * (count - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        out = new boolean[N];
        String s = bf.readLine();
        for (int i = 0; i < s.length(); i++) {
            out[i] = s.charAt(i) == '0';
        }
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph.get(a).add(b);
            graph.get(b).add(a);
            if (!out[a] && !out[b]) ans += 2;
        }
        solution();
        System.out.println(ans);
    }
}