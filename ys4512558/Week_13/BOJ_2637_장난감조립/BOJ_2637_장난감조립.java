package com.javajava.week13;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2637 {
    static int[][] counts;
    static boolean[] isv;
    static List<int[]>[] adjList;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        isv = new boolean[N + 1];
        counts = new int[N + 1][N + 1];
        adjList = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            adjList[X].add(new int[]{Y, K});
        }
        dfs(N, 1, 0);
        for (int i = 1; i <= N; i++) {
            if(adjList[i].isEmpty()) sb.append(i).append(" ").append(counts[N][i]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void dfs(int n, int cnt, int pre) {
        if (adjList[n].isEmpty()) {
            counts[pre][n] += cnt;
            return;
        }
        for (int i = 0; i < adjList[n].size(); i++) {
            int[] next = adjList[n].get(i);
            if(!isv[next[0]]) dfs(next[0], next[1], n);
            for (int j = 1; j <= N; j++) {
                counts[n][j] += counts[next[0]][j] * next[1];
            }
        }
        isv[n] = true;
    }
}