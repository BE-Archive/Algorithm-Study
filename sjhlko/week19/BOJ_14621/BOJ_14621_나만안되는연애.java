package sjhlko.week19.BOJ_14621;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_14621_나만안되는연애 {
    //https://www.acmicpc.net/problem/14621
    //나만 안되는 연애
    static class Edge implements Comparable<Edge> {
        int a;
        int b;
        int value;

        public Edge(int a, int b, int value) {
            this.a = a;
            this.b = b;
            this.value = value;
        }

        @Override
        public int compareTo(Edge o) {
            return this.value - o.value;
        }
    }

    public static int find(int a, int[] graph) {
        if (graph[a] == -1) return a;
        return graph[a] = find(graph[a], graph);
    }

    public static boolean union(int a, int b, int[] graph, int[] height) {
        int rootA = find(a, graph);
        int rootB = find(b, graph);
        if (rootB == rootA) return false;
        if (rootA >= rootB) {
            int tmp = rootA;
            rootA = rootB;
            rootB = tmp;
        }
        if (height[rootA] == height[rootB]) {
            graph[rootA] = rootB;
            height[rootA] = -1;
            height[rootB]++;
            return true;
        }
        graph[rootA] = rootB;
        height[rootA] = -1;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        HashMap<Integer, Character> hm = new HashMap<>();
        for (int i = 1; i < N + 1; i++) {
            hm.put(i, st.nextToken().charAt(0));
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            pq.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        int[] graph = new int[N + 1];
        int[] height = new int[N + 1];
        Arrays.fill(graph, -1);
        int ans = 0;
        int count = 0;
        while (!pq.isEmpty() && count != N - 1) {
            Edge e = pq.poll();
            if (hm.get(e.a) == hm.get(e.b)) continue;
            if (union(e.a, e.b, graph, height)){
                ans += e.value;
                count++;
            }
        }
        System.out.println(count == N - 1 ? ans : -1);
    }
}
