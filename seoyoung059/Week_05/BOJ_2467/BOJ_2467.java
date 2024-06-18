package seoyoung059.Week_05.BOJ_2467;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2467 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int s, e, m;
        int val = Integer.MAX_VALUE;
        int ans1 = 0;
        int ans2 = 0;
        for (int i = 0; i < n-1; i++) {
            s = i + 1;
            e = n-1;
            while (s < e) {
                m = (s + e) / 2;
                if (arr[i] + arr[m] < 0) {
                    s = m + 1;
                } else e = m;
            }
            int abs = Math.abs(arr[i] + arr[e]);
            if (abs < val) {
                val = abs;
                ans1 = arr[i];
                ans2 = arr[e];
            }
            abs = Math.abs(arr[i] + arr[e - 1]);
            if (i < e - 1 && abs < val) {
                val = abs;
                ans1 = arr[i];
                ans2 = arr[e - 1];
            }
        }
        System.out.println(ans1 + " " + ans2);
    }
}