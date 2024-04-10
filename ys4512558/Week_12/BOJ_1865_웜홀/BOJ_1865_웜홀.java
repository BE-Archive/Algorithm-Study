import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Edge> edges;

    static int[] dist;
    static boolean[] isv;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int INF = 10000;
    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            sb.append(solve(N, M, W) ? "YES" : "NO").append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static boolean solve(int n, int m, int w) throws IOException {
        edges = new ArrayList<Edge>();
        dist = new int[n + 1];
        isv = new boolean[n + 1];

        for (int i = 0; i < m + w; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(i < m) {
                edges.add(new Edge(s, e, c));
                edges.add(new Edge(e, s, c));
            } else {
                edges.add(new Edge(s, e, -c));
            }
        }
        for (int i = 1; i <= n; i++) {
            if(isv[i]) continue;
            if(bellmanford(n, i)) return true;
        }
        return false;
    }

    private static boolean bellmanford(int n, int start) {
        Arrays.fill(dist, INF);
        isv[start] = true;
        dist[start] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < edges.size(); j++) {
                Edge edge = edges.get(j);
                if(dist[edge.s] == INF) continue;
                isv[edge.s] = true;
                if (dist[edge.e] > dist[edge.s] + edge.cost) {
                    dist[edge.e] = dist[edge.s] + edge.cost;
                    isv[edge.e] = true;
                    if(i == n) return true;
                }
            }
        }
        return false;
    }
}

class Edge{
    int s, e, cost;

    public Edge(int s, int e, int cost) {
        this.s = s;
        this.e = e;
        this.cost = cost;
    }
}