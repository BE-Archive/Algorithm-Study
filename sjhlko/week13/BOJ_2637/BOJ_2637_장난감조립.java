package sjhlko.week13.BOJ_2637;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2637_장난감조립 {
    //https://www.acmicpc.net/problem/2637
    //장난감 조립
    static int N, M;
    static int[] ans;
    static int[][] dp;
    static boolean[] isBasic;
    static List<List<Info>> infos = new ArrayList<>();

    static class Info {
        int index;
        int count;

        public Info(int index, int count) {
            this.index = index;
            this.count = count;
        }
    }

    static int[] solution(int index) {
        if (dp[index][N + 1] != -1) {
            return dp[index];
        }
        dp[index][N + 1] = 1;
        if (infos.get(index).size() == 0) {
            isBasic[index] = true;
            dp[index][index] = 1;
        }
        for (int i = 0; i < infos.get(index).size(); i++) {
            Info next = infos.get(index).get(i);
            int[] nextDp = solution(next.index);
            for (int j = 1; j < N + 1; j++) {
                dp[index][j] += nextDp[j] * next.count;
            }
        }
        return dp[index];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N + 1; i++) {
            infos.add(new ArrayList<>());
        }
        ans = new int[N + 1];
        isBasic = new boolean[N + 1];
        dp = new int[N + 1][N + 2];
        for (int i = 0; i < N + 1; i++) {
            dp[i][N + 1] = -1;
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            infos.get(x).add(new Info(y, k));
        }
        solution(N);

        StringBuilder print = new StringBuilder();
        for (int i = 0; i < N + 1; i++) {
            if (isBasic[i]) {
                print.append(i).append(" ").append(dp[N][i]).append("\n");
            }
        }
        System.out.println(print);

    }
}
