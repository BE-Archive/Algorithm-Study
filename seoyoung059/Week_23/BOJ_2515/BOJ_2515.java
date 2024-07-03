package Week_23.BOJ_2515;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_2515 {

    static class Painting implements Comparable<Painting> {
        int h;
        int c;

        public Painting(int h, int c) {
            this.h = h;
            this.c = c;
        }


        @Override
        public int compareTo(Painting o) {
            return this.h - o.h;
        }

        @Override
        public String toString() {
            return "Painting{" +
                    "h=" + h +
                    ", c=" + c +
                    '}';
        }
    }

    static int binarySearch(int[][] arr, int h) {
        int s = 0, e = arr.length - 1, m;
        while (s <= e) {
            m = (s + e) / 2;
            if (arr[m][0] > h) {
                e = m - 1;
            } else {
                s = m + 1;
            }
        }
        return s;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][2];
        int h, c;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            arr[i][0] = h;
            arr[i][1] = c;
        }

        Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));

        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            dp[i + 1] = Math.max(dp[i], dp[binarySearch(arr, arr[i][0] - s)] + arr[i][1]);
        }

        System.out.println(dp[n]);
    }
}