import java.io.*;
import java.util.*;

public class Main {

    static List<Queue<Integer>> buildings = new ArrayList<>();
    static int N, cost[];
    static int dp[];
    static boolean[] visited;

    static int solve(final int idx)
    {
        Queue<Integer> q = buildings.get(idx);

        int front, result = 0;

        while(!q.isEmpty())
        {
            front = q.poll();

            if(visited[front])
                result = Math.max(result, cost[front]);
            else
                result = Math.max(result, solve(front));
        }
        visited[idx] = true;
        return cost[idx] += result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        N = Integer.parseInt(br.readLine());
        cost = new int[N+1];
        visited = new boolean[N+1];
        dp = new int[N+1];
        for(int i = 0; i <= N; i++)
            buildings.add(new ArrayDeque<>());

        for(int from=1; from<=N; ++from)
        {
            st = new StringTokenizer(br.readLine());
            cost[from] = Integer.parseInt(st.nextToken());
            int cnt = st.countTokens()-1;
            for(int j=0; j<cnt; ++j)
            {
                int to = Integer.parseInt(st.nextToken());
                buildings.get(from).add(to);
            }
        }

        for(int i=1; i<=N; ++i)
        {
            solve(i);
        }

        for(int i=1; i<=N; ++i)
        {
            System.out.println(cost[i]);
        }
    }
}
