package sjhlko.week24.BOJ_17396;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17396_백도어 {
    //https://www.acmicpc.net/problem/17396
    //백도어
    static int N, M;
    static boolean[] visible;
    static List<List<Info>> infos = new ArrayList<>();

    static class Info implements Comparable<Info> {
        int to;
        long cost;

        public Info(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Info o) {
            return Long.compare(cost, o.cost);
        }
    }

    static long solution() {
        long[] dist = new long[N];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(0, 0));
        while (!pq.isEmpty()) {
            Info now = pq.poll();
            if (now.cost > dist[now.to]) continue;
            for (int i = 0; i < infos.get(now.to).size(); i++) {
                Info next = infos.get(now.to).get(i);
                if (visible[next.to] && next.to != N - 1) continue;
                if (dist[next.to] == Long.MAX_VALUE || dist[next.to] > dist[now.to] + next.cost) {
                    dist[next.to] = dist[now.to] + next.cost;
                    pq.add(new Info(next.to, dist[next.to]));
                }
            }
        }
        return dist[N - 1] == Long.MAX_VALUE ? -1 : dist[N - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visible = new boolean[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            visible[i] = st.nextToken().equals("1");
            infos.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            infos.get(a).add(new Info(b, c));
            infos.get(b).add(new Info(a, c));
        }
        System.out.println(solution());
    }
}
