import java.io.*;
import java.util.*;

public class Main {

    final static int INF = Integer.MAX_VALUE;

    static BufferedReader br;
    static StringTokenizer st;

    static int V, E, P;
    static class Info
    {
        public Info(int vertex, boolean isSave, int sum) {
            this.vertex = vertex;
            this.isSave = isSave;
            this.sum = sum;
        }

        int vertex;
        int sum;
        boolean isSave = false;
    }

    static boolean solve(final int start, final int end) throws IOException
    {
        boolean answer = false;

        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < 5001; ++i)
            graph.add(new ArrayList<>());

        int from, to, c;
        for (int i = 0; i < E; ++i)
        {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            graph.get(from).add(new int[]{to, c});
            graph.get(to).add(new int[]{from, c});
        }

        int[] dp = new int[V + 1];
        Arrays.fill(dp, INF);
        dp[1] = 0;

        PriorityQueue<Info> q = new PriorityQueue<>((a, b) -> Integer.compare(a.sum, b.sum));
        for (final int[] node : graph.get(start))
        {
            q.add(new Info(node[0], node[0] == P, node[1]));
        }

        Info front;
        int minValue = INF;
        while (!q.isEmpty())
        {
            front = q.poll();
            if(front.sum > minValue) break;
            if(front.vertex == end)
            {
                minValue = front.sum;
                //minValue = dp[end];
                if(front.isSave)
                {
                    answer = true;
                    break;
                }
            }

            for (final int[] info : graph.get(front.vertex))
            {
                int next = info[0];
                int cost = info[1];

                // 이미 방문한 곳의 가중치가 더 큰 방법으로 다가가려고 한다. -> continue
                if (front.sum + cost <= dp[next])
                {
                    dp[next] = front.sum + cost;
                    q.offer(new Info(next, next == P ? true : front.isSave,  dp[next]));
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        boolean answer = false;
        if(P == V || P == 1)
            answer = true;
        else
            answer = solve(1, V);

        System.out.print(answer ? "SAVE HIM" : "GOOD BYE");
    } // main
}
