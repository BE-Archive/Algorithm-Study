package Ing.BOJ_15654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15654 {

    static StringBuilder sb = new StringBuilder();
    static int n, m;


    static void solve(int[] arr, int[] ans, int cnt, int check) {
        if (cnt == m) {
            for (int j = 0; j < m; j++) {
                sb.append(ans[j]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int j = 0; j < n; j++) {
            if ((check & (1 << j)) > 0) continue;
            ans[cnt] = arr[j];
            solve(arr, ans, cnt + 1, check | (1 << j));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int[] ans = new int[m];
        solve(arr, ans, 0, 0);
        System.out.print(sb);
    }
}
