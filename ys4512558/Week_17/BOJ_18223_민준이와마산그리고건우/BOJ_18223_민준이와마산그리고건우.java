import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int V, E, P;
    static Node[] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        adjList = new Node[V + 1];
        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            //from , to 중 P가 존재하면 save = true 로 설정
            adjList[from] = new Node(to, cost, from == P || to == P, adjList[from]);
            adjList[to] = new Node(from, cost, from == P || to == P, adjList[to]);
        }
        System.out.println(dijkstra() ? "SAVE HIM" : "GOOD BYE");
    }

    private static boolean dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0, 1 == P, null));
        int[] dist = new int[V + 1];
        Arrays.fill(dist, 5000 * 10000);
        dist[1] = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            /**
             * 같은 가중치인 node.save가 나중에 나올 수 있으므로
             * 구할 수 있는 경우에는 true로 더 이상 탐색 X
             * 구할 수 없는 경우는 다음 node까지 확인해본다.
             */
            if (node.vertex == V) {
                if(node.save) return true;
                continue;
            }

            for (Node n = adjList[node.vertex]; n != null; n = n.next) {
                if (dist[n.vertex] > dist[node.vertex] + n.cost) {
                    dist[n.vertex] = dist[node.vertex] + n.cost;
                    //현재 정점에 건우가 존재하거나, 이전 경로에 건우가 존재할 때 save = true
                    pq.offer(new Node(n.vertex, dist[n.vertex], node.save || n.vertex == P, null));
                }
                //같으면 save가 true 일때만
                if (dist[n.vertex] == dist[node.vertex] + n.cost && n.save) {
                    /**
                     * 위의 dist[n.vertext] > dist[node.vertex] + n.cost는 같은 경우 탐지 못함
                     * 하지만, 가중치가 같을 때
                     * 이전 경로는 save = false 이고, 
                     * 현재 경로는 save = true 일 수 있다.
                     * 따라서 dist가 같을 때 구할 수 있는 경우에는 큐에 추가해준다.
                     */
                    pq.offer(new Node(n.vertex, dist[n.vertex], true, null));
                }
            }
        }
        //모든 경로를 확인했을 때 save가 불가능한 경우
        return false;
    }
}

class Node implements Comparable<Node>{
    int vertex, cost;
    //구할 수 있는지 상태를 가지고 있는 변수 추가
    boolean save;
    Node next;

    public Node(int vertex, int cost, boolean save, Node next) {
        this.vertex = vertex;
        this.cost = cost;
        this.save = save;
        this.next = next;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }
}