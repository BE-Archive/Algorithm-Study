package Week_24.BOJ_21606;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_21606 {

    static long answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String isInside = br.readLine();
        ArrayDeque<Integer>[] graph = new ArrayDeque[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayDeque<>();
        }


        int[][] edge = new int[n - 1][2];
        StringTokenizer st;
        int a, b;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            edge[i][0] = a;
            edge[i][1] = b;
            graph[a].offer(i);
            graph[b].offer(i);
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n - 1];
        int cnt;
        int curr;
        for (int i = 0; i < n - 1; i++) {
            if (visited[i]) continue;
            cnt = 0;
            q.offer(i);
            visited[i] = true;
            while (!q.isEmpty()) {
                curr = q.pollFirst();
                for (int j = 0; j < 2; j++) {
                    if (isInside.charAt(edge[curr][j]) == '0') {
                        for (Integer e : graph[edge[curr][j]]) {
                            if (visited[e]) continue;
                            visited[e] = true;
                            q.offer(e);
                        }
                    } else {
                        cnt++;
                    }
                }
            }
            answer += cnt * (cnt - 1);
        }
        System.out.println(answer);
    }
}