import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int dp[] = new int[N+1];
        int tmp[] = new int [2];
        boolean visited[] = new boolean[N+1];
        ArrayList<int[]>[] adj = new ArrayList[N+1]; // 버스 번호가 1부터 시작
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        for (int i = 0; i <= N; ++i) 
        {
            dp[i] = Integer.MAX_VALUE;
            adj[i] = new ArrayList<>();
        }

        int from, to, weight;

        for (int i = 0; i < M; ++i) 
        {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());
            adj[from].add(new int[]{to, weight});
        }

        st = new StringTokenizer(br.readLine());
        from = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());

        dp[from] = 0;//자기 자신으로 가는건 0
        pq.offer(new int[]{from, 0});

        while (!pq.isEmpty())
        {
            tmp = pq.poll();
            if(visited[tmp[0]]) continue;

            visited[tmp[0]] = true;
            for(final int[] iter : adj[tmp[0]])
            {
                if(dp[iter[0]] > dp[tmp[0]] + iter[1])
                {
                    dp[iter[0]] = dp[tmp[0]] + iter[1];
                    pq.offer(new int[]{iter[0], dp[iter[0]]});
                }
            }
        }

        System.out.print(dp[to]);
    }

}
