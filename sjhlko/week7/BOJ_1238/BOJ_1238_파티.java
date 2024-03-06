package sjhlko.week7.BOJ_1238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1238_파티 {
    //https://www.acmicpc.net/problem/1238
    //파티
    static int X;
    static int N;

    public static class Node {
        int dest;
        int cost;

        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    public static int dijkstra(int source, int dest, ArrayList<ArrayList<Node>> graph) {
        PriorityQueue<Node> pq = new PriorityQueue<>(
                Comparator.comparingInt(o1 -> o1.cost)
        );
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        pq.add(new Node(source, 0));
        while (!pq.isEmpty()) {
            Node u = pq.poll();
            if (u.cost > dist[u.dest]) continue;
                // u에서 갈 수 있는 모든 길(노드) 탐색
                for (Node v : graph.get(u.dest)) {
                    // u에서 v로 갈 수 있는 비용 갱신
                    if (dist[v.dest] <= dist[u.dest] + v.cost) continue;
                    dist[v.dest] = dist[u.dest] + v.cost;
                    // 갱신되었다면 기존 v에서 갈 수 있는 길들의 비용을 다시 갱신하기 위해 큐에 삽입
                    pq.add(new Node(v.dest, dist[v.dest]));
                }
        }

        return dist[dest];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, cost));
        }
        int ans = 0;
        for (int i = 1; i < N + 1; i++) {
            ans = Math.max(ans, dijkstra(i, X, graph) + dijkstra(X, i, graph));
        }
        System.out.println(ans);
    }
}
