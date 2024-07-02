import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17396_백도어 {
    final static long INF = Long.MAX_VALUE;
    static List<Edge>[] graph;
    static int N, M;

    static class Edge implements Comparable<Edge> {
        int num;
        long weight;

        public Edge(int num, long weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] see = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            see[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            // 양쪽 모두 시야에 보이지 않는 경우
            if (see[a] == 0 && see[b] == 0) {
                graph[a].add(new Edge(b, t));
                graph[b].add(new Edge(a, t));
            }
            // 한쪽이 넥서스이고 다른 쪽이 시야에 보이지 않는 경우
            else if (a == N - 1 && see[b] == 0) {
                graph[a].add(new Edge(b, t));
                graph[b].add(new Edge(a, t));
            }
            else if (see[a] == 0 && b == N - 1) {
                graph[a].add(new Edge(b, t));
                graph[b].add(new Edge(a, t));
            }
        }

        System.out.println(solve());
    }

    public static long solve() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        long[] distance = new long[N];
        Arrays.fill(distance, INF);

        pq.offer(new Edge(0, 0));
        distance[0] = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if (now.num == N - 1) return now.weight;
            if (now.weight > distance[now.num]) continue;

            for (Edge next : graph[now.num]) {
                if (now.weight + next.weight < distance[next.num]) {
                    distance[next.num] = now.weight + next.weight;
                    pq.offer(new Edge(next.num, distance[next.num]));
                }
            }
        }

        return -1;  // 도달할 수 없는 경우
    }
}
