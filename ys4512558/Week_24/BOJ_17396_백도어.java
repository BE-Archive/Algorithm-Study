import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ17396 {
    static Node[] adjList;
    static boolean[] ward;
    static int N, M;
    static final long INF = 100_000L * 100_000L + 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ward = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ward[i] = Integer.parseInt(st.nextToken()) == 0;
        }

        adjList = new Node[N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            //두 정점이 모두 보이지 않을때만 가도록 인접 리스트 생성
            //넥서스는 무조건 보이므로 제외
            if ((ward[from] && ward[to])) {
                adjList[from] = new Node(to, cost, adjList[from]);
                adjList[to] = new Node(from, cost, adjList[to]);
            } else if (from == N - 1 && ward[to]) {
                adjList[to] = new Node(from, cost, adjList[to]);
            } else if (to == N - 1 && ward[from]) {
                adjList[from] = new Node(to, cost, adjList[from]);
            }
        }

        System.out.println(dijkstra());
    }

    private static long dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, null));
        long[] dist = new long[N];
        Arrays.fill(dist, INF);
        dist[0] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            //중요!!! 시간 초과 포인트
            //이전에 들어왔지만 우선순위가 늦어서 즉, 코스트가 높아서 못나오다가 뒤늦게 나온 node
            //해당 node는 이미 우선순위가 높은 노드들에 의해 dist[node.vertex]가 갱신된 상태인데
            //우선순위가 낮아서 이제야 나왔음 -> 이것도 다시 for문을 돌면 어차피 갱신안됨 
            //이미 다른 노드들에 의해 더 작은 값으로 갱신이 되었으니까!!
            //따라서 해당 노드는 폐기해야함 (for문을 굳이 더 안돌아도 되니 시간도 효율적)
            if(node.cost > dist[node.vertex]) continue;
            if (node.vertex == N - 1) return dist[N - 1];

            for (Node n = adjList[node.vertex]; n != null; n = n.next) {
                if (dist[n.vertex] > dist[node.vertex] + n.cost) {
                    dist[n.vertex] = dist[node.vertex] + n.cost;
                    pq.offer(new Node(n.vertex, dist[n.vertex], null));
                }
            }
        }

        return -1;
    }
}

class Node implements Comparable<Node> {
    int vertex;
    long cost;
    Node next;

    public Node(int vertex, long cost, Node next) {
        this.vertex = vertex;
        this.cost = cost;
        this.next = next;
    }

    @Override
    public int compareTo(Node o) {
        return Long.compare(this.cost, o.cost);
    }
}