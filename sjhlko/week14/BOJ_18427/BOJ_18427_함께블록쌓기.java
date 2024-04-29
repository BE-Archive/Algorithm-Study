package sjhlko.week14.BOJ_18427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_18427_함께블록쌓기 {
    //https://www.acmicpc.net/problem/18427
    //함께 블록 쌓기
    static int N, M, H;
    static int[][] dp;
    static List<List<Integer>> info = new ArrayList<>();

    static void solution() {
        for (int i = 0; i < N; i++) {
            for (Integer block : info.get(i)) {
                dp[i][block]++;
            }
            if (i == 0) continue;
            List<Integer> now = info.get(i);
            for (int j = 0; j < H + 1; j++) {
                dp[i][j] += dp[i - 1][j];
                dp[i][j] %= 10007;
                for (int k = 0; k < now.size(); k++) {
                    if (j >= now.get(k)) {
                        dp[i][j] += dp[i - 1][j - now.get(k)];
                        dp[i][j] %= 10007;
                    }
                }
            }
        }
        System.out.println(dp[N - 1][H] % 10007);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        dp = new int[N][H + 1];
        for (int i = 0; i < N; i++) {
            List<Integer> list = new ArrayList<>();
            st = new StringTokenizer(bf.readLine());
            int size = st.countTokens();
            while (size-- > 0) {
                list.add(Integer.valueOf(st.nextToken()));
            }
            info.add(list);
        }
        solution();
    }
}