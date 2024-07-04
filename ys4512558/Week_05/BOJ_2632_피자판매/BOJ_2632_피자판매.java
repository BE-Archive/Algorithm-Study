package com.javajava.week05;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2632 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int[] dp1 = new int[1000001];
        int[] dp2 = new int[1000001];

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[] pizza1 = new int[A];
        int[] pizza2 = new int[B];
        int sumA = 0;
        for (int i = 0; i < A; i++) {
            pizza1[i] = Integer.parseInt(br.readLine());
            sumA += pizza1[i];
        }
        dp1[sumA] = 1;
        int sumB = 0;
        for (int i = 0; i < B; i++) {
            pizza2[i] = Integer.parseInt(br.readLine());
            sumB += pizza2[i];
        }
        dp2[sumB] = 1;

        for (int i = 0; i < A; i++) {
            int sum = 0;
            for (int j = i; j < i + A - 1; j++) {
                sum += pizza1[j % A];
                dp1[sum]++;
            }
        }
        dp1[0] = dp2[0] = 1;
        for (int i = 0; i < B; i++) {
            int sum = 0;
            for (int j = i; j < i + B - 1; j++) {
                sum += pizza2[j % B];
                dp2[sum]++;
            }
        }
        int count = 0;
        for (int i = 0; i <= N; i++) {
            count += dp1[i] * dp2[N - i];
        }
        sb.append(count);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
