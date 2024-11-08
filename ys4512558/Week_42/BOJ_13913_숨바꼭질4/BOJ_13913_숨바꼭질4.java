import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, K, MAX = 100000;
    static int[] dp;
    static Node start, end;
    static final int INF = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[100_001];
        Arrays.fill(dp, INF);
        start = new Node(N, null);

        bfs();
        StringBuilder sb = new StringBuilder();
        sb.append(dp[K]).append("\n");
        sb.append(print());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static String print() {
        int[] arr = new int[dp[K] + 1];
        int idx = 0;
        for (Node node = end; node != null; node = node.prev) {
            arr[idx++] = node.idx;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = dp[K]; i >= 0; i--) {
            sb.append(arr[i]).append(" ");
        }
        return sb.toString();
    }

    private static void bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(start);
        dp[N] = 0;
        int cnt = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Node node = queue.poll();
                int idx = node.idx;

                if (idx == K) {
                    end = node;
                    return;
                }
                if (idx - 1 >= 0 && dp[idx - 1] == INF) {
                    dp[idx - 1] = cnt;
                    queue.offer(new Node(idx - 1, node));
                }
                if (idx + 1 <= Math.min(2 * K, MAX) && dp[idx + 1] == INF) {
                    dp[idx + 1] = cnt;
                    queue.offer(new Node(idx + 1, node));
                }
                if (idx * 2 <= Math.min(2 * K, MAX) && dp[idx * 2] == INF) {
                    dp[idx * 2] = cnt;
                    queue.offer(new Node(idx * 2, node));
                }
            }
            cnt++;
        }
    }
}

class Node {
    int idx;
    Node prev;

    public Node(int idx, Node prev) {
        this.idx = idx;
        this.prev = prev;
    }
}