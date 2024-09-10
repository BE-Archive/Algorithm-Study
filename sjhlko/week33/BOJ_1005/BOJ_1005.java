package sjhlko.week33.BOJ_1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1005 {
    //https://www.acmicpc.net/problem/1005
    //ACM Craft
    public static long solution(ArrayList<ArrayList<Integer>> graph, int target, int[] cost, long[] dp) {
        long tmpAns = 0;
        if (graph.get(target).isEmpty()) {
            dp[target] = cost[target];
            return cost[target];
        }
        for (int i = 0; i < graph.get(target).size(); i++) {
            if (dp[graph.get(target).get(i)] != -1) {
                tmpAns = Math.max(tmpAns, dp[graph.get(target).get(i)]);
                continue;
            }
            tmpAns = Math.max(tmpAns, solution(graph, graph.get(target).get(i), cost, dp));
        }
        dp[target] = tmpAns + cost[target];
        return tmpAns + cost[target];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] cost = new int[N + 1];
            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            graph.add(new ArrayList<>());
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                graph.add(new ArrayList<>());
                cost[j + 1] = Integer.parseInt(st.nextToken());
            }
            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(bf.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph.get(b).add(a);
            }
            long[] dp = new long[N + 1];
            Arrays.fill(dp, -1);
            System.out.println(solution(graph, Integer.parseInt(bf.readLine()), cost, dp));

        }
    }
}
