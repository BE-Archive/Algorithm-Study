package sjhlko.week36.BOJ_28017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    //https://www.acmicpc.net/problem/28017
    //게임을 클리어하자
    static int N, M;
    static int[][] info;
    static class Info implements Comparable<Info> {
        int index, value;

        public Info(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Info o) {
            return value - o.value;
        }
    }
    static int solution() {
        int[][] dp = new int[N][M];
        PriorityQueue<Info> pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            dp[0][i] = info[0][i];
            pq.add(new Info(i, dp[0][i]));
        }
        for (int i = 1; i < N; i++) {
            PriorityQueue<Info> next = new PriorityQueue<>();
            for (int j = 0; j < M; j++) {
                Info now = pq.peek();
                if (now.index == j) {
                    Info tmp = pq.poll();
                    dp[i][j] = pq.peek().value + info[i][j];
                    pq.add(tmp);
                } else {
                    dp[i][j] = now.value + info[i][j];
                }
                next.add(new Info(j, dp[i][j]));
            }
            pq = next;
        }
        return pq.poll().value;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        info = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                info[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution());
    }

}