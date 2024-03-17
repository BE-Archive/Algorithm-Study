package sjhlko.week8.BOJ_11005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11005_가장큰증가하는부분수열 {
    //https://www.acmicpc.net/problem/11055
    //가장 큰 증가하는 부분 수열
    static int N;
    static int[] arr;
    static int[] dp;
    static int ans;

    static void solution() {
        for (int i = 1; i < N + 1; i++) {
            int maxIndex = 0;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && dp[j] >= dp[maxIndex]) maxIndex = j;
            }
            dp[i] = dp[maxIndex] + arr[i];
            ans = Math.max(ans, dp[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        arr = new int[N + 1];
        dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        solution();
        System.out.println(ans);
    }
}
