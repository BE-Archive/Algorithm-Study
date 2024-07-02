import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1516 {
    static Node[] adjList;
    static int[] times, counts, results;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        adjList = new Node[N + 1];
        counts = new int[N + 1];
        results = new int[N + 1];
        times = new int[N + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());
            int cnt = st.countTokens();
            if(cnt == 1) {
                queue.offer(i);
                results[i] = times[i];
            }
            for (int j = 0; j < cnt - 1; j++) {
                int vertex = Integer.parseInt(st.nextToken());
                adjList[vertex] = new Node(i, adjList[vertex]);
                counts[i]++;
            }
        }

        topologySort(queue);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(results[i]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void topologySort(Queue<Integer> queue) {
        while (!queue.isEmpty()) {
            Integer p = queue.poll();

            for (Node node = adjList[p]; node != null; node = node.next) {
                if (--counts[node.vertex] == 0) {
                    queue.offer(node.vertex);
                    results[node.vertex] = Math.max(results[node.vertex], results[p]) + times[node.vertex];
                } else {
                    //아직끝나지 않은 경우 (해당 작업을 하기 전에 끝나야할 작업 중 가장 큰 시간이 드는 작업으로 갱신)
                    //동시에 만들어질 수 있다 -> 사전 작업 중 가장 오래 걸리는거 + 내 작업시간이 총 걸리는 시간임
                    results[node.vertex] = Math.max(results[node.vertex], results[p]);
                }
            }
        }
    }
}

class Node{
    int vertex;
    Node next;

    public Node(int vertex, Node next) {
        this.vertex = vertex;
        this.next = next;
    }
}