import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ14621 {

    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        char[] gender = new char[N + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            gender[i] = st.nextToken().charAt(0);
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if(gender[v] == gender[u]) continue;
            pq.offer(new Edge(v, u, d));
        }

        System.out.println(kruskal(pq));
    }

    private static int kruskal(PriorityQueue<Edge> pq) {
        int dist = 0;
        int count = 0;
        UnionFind uf = new UnionFind(N);

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (uf.union(edge.v, edge.u)) {
                dist += edge.d;
                count++;
            }

            if(count == N - 1) return dist;
        }

        return -1;
    }
}

class Edge implements Comparable<Edge>{
    int v, u, d;

    public Edge(int v, int u, int d) {
        this.v = v;
        this.u = u;
        this.d = d;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.d, o.d);
    }
}

class UnionFind{
    int[] parents;
    int[] rank;

    public UnionFind(int size) {
        this.parents = new int[size + 1];
        this.rank = new int[size + 1];

        for (int i = 1; i <= size; i++) {
            parents[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int v) {
        if(parents[v] == v) return v;
        return parents[v] = find(parents[v]);
    }

    public boolean union(int v, int u) {
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