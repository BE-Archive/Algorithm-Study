import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1719 {

    static int[][][] dist;
    static Node[] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        //from -> to 으로 가는 경로에서 상태 저장
        dist = new int[N + 1][N + 1][2]; // 0 : 거리, 1 : 첫 방문 집하장
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dist[i][j] = new int[]{(Integer.MAX_VALUE / 2), j};
            }
        }

        adjList = new Node[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adjList[from] = new Node(to, cost, to, adjList[from]);
            adjList[to] = new Node(from, cost, from, adjList[to]);
        }
        for (int i = 1; i <= N; i++) {
            dijkstra(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    sb.append("- ");
                } else {
                    sb.append(dist[i][j][1] + " ");
                }
            }
            sb.append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void dijkstra(int vertex) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(vertex, 0, vertex,null));
        dist[vertex][vertex] = new int[]{0, vertex};
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if(dist[vertex][node.vertex][0] < node.cost) continue;

            for (Node n = adjList[node.vertex]; n != null; n = n.next) {
                if (dist[vertex][n.vertex][0] > dist[vertex][node.vertex][0] + n.cost) {
                    int cost = dist[vertex][node.vertex][0] + n.cost;
                    dist[vertex][n.vertex][0] = cost;
                    int first = node.first == vertex ? n.first : node.first;
                    dist[vertex][n.vertex][1] = first;
                    pq.offer(new Node(n.vertex, cost, first, null));
                }
            }
        }
    }
}

class Node implements Comparable<Node> {
    int vertex, cost, first;
    Node next;

    public Node(int vertex, int cost, int first, Node next) {
        this.vertex = vertex;
        this.cost = cost;
        this.first = first;
        this.next = next;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }
}