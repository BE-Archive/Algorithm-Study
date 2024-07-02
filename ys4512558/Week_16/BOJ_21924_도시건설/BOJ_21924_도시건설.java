import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ21924 {
    static int[] parents;
    static int[] rank;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parents = new int[N + 1];
        rank = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
            rank[i] = 1;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        long sum = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            sum += cost;
            pq.offer(new Edge(from, to, cost));
        }

        System.out.println(Kruskal(N, sum, pq));
    }

    private static long Kruskal(int n, long sum, PriorityQueue<Edge> pq) {
        int count = 0;
        long cost = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (count == n - 1) return sum - cost;
            if (union(edge.from, edge.to)) {
                cost += edge.cost;
                count++;
            }
        }
        return -1;
    }

    public static int find(int v){
        if(v == parents[v]) return v;
        return parents[v] = find(parents[v]);
    }

    public static boolean union(int v, int u) {
        int rep1 = find(v);
        int rep2 = find(u);

        if(rep1 == rep2) return false;
        if (rank[rep1] < rank[rep2]) {
            parents[rep1] = rep2;
            return true;
        }
        parents[rep2] = rep1;
        rank[rep1] = rank[rep1] == rank[rep2] ? rank[rep1] + 1 : rank[rep1];
        return true;
    }
}

class Edge implements Comparable<Edge>{
    int from, to, cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.cost, o.cost);
    }
}