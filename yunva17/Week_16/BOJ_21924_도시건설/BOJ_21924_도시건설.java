import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_21924_도시건설 {

    static int N, M;
    static Edge[] edgeList;
    static int[] parents;

    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        long answer = 0;
        long sum = 0;

        edgeList = new Edge[M];
        parents = new int[N + 1];

        Arrays.fill(parents, -1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            sum += c;

            edgeList[i] = new Edge(a, b, c);
        }

        Arrays.sort(edgeList);

        int count = 0;
        for (Edge e : edgeList) {
            if (union(e.from, e.to)) {
                answer += e.weight;

                if (++count == N - 1) break; // MST 완성
            }
        }

        System.out.println(count != N - 1 ? -1 : sum - answer);

    }


    public static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) {
            return false;
        }

        if (rootA < rootB) {
            parents[rootA] += parents[rootB];
            parents[rootB] = rootA;
        } else {
            parents[rootB] += parents[rootA];
            parents[rootA] = rootB;
        }

        return true;
    }

    public static int find(int x) {
        if (parents[x] < 0) return x;

        return parents[x] = find(parents[x]);
    }

}