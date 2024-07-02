package sjhlko.week3.BOJ_20992;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_20992_겹치는건싫어 {
    //https://www.acmicpc.net/problem/20922
    //겹치는건 싫어
    static int N, K;
    static int[] arr;
    static int ans;
    static Map<Integer, Integer> count = new HashMap<>();

    static void solution() {
        int start = 0;
        int end = 0;
        while (end < N) {
            count.putIfAbsent(arr[end], 0);
            count.put(arr[end], count.get(arr[end]) + 1);
            if (count.get(arr[end]) <= K) ans = Math.max(ans, end - start + 1);
            while (count.get(arr[end]) > K) {
                count.put(arr[start], count.get(arr[start]) - 1);
                start++;
            }
            end++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        solution();
        System.out.println(ans);
    }

}
