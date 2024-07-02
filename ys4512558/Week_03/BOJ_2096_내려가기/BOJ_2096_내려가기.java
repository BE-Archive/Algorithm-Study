package com.javajava.week03;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ2096 {
    static StringBuilder sb = new StringBuilder();
    static int[][] dpMax;
    static int[][] dpMin;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int max = 0;
        int min = Integer.MAX_VALUE;

        dpMax = new int[N + 1][3];
        dpMin = new int[N + 1][3];
        arr = new int[N + 1][3];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
            dpMax[i][0] = Math.max(dpMax[i - 1][0], dpMax[i - 1][1]) + arr[i][0];
            dpMax[i][1] = Math.max(dpMax[i][0] - arr[i][0], dpMax[i - 1][2]) + arr[i][1];
            dpMax[i][2] = Math.max(dpMax[i - 1][1], dpMax[i - 1][2]) + arr[i][2];;

            dpMin[i][0] = Math.min(dpMin[i - 1][0], dpMin[i - 1][1]) + arr[i][0];
            dpMin[i][1] = Math.min(dpMin[i][0] - arr[i][0], dpMin[i - 1][2]) + arr[i][1];;
            dpMin[i][2] = Math.min(dpMin[i - 1][1], dpMin[i - 1][2]) + arr[i][2];;
        }

        for (int i = 0; i < 3; i++) {
            max = Math.max(max, dpMax[N][i]);
            min = Math.min(min, dpMin[N][i]);
        }
        sb.append(max).append(" ").append(min);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}