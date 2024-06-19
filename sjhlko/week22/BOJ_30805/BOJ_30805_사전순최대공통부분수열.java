package sjhlko.week22.BOJ_30805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_30805_사전순최대공통부분수열 {
    //https://www.acmicpc.net/problem/30805
    //사전순 최대 공통 부분 수열
    static int N, M;
    static int[] arrA, arrB;

    static class Info implements Comparable<Info> {
        int index;
        int value;

        public Info(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Info o) {
            if (value != o.value) return o.value - value;
            return index - o.index;
        }
    }

    static void add(PriorityQueue<Info> pq, int[] arr, int start) {
        for (int i = start; i < arr.length; i++) {
            pq.add(new Info(i, arr[i]));
        }
    }

    static void solution() {
        StringBuilder ans = new StringBuilder();
        PriorityQueue<Info> pa = new PriorityQueue<>();
        PriorityQueue<Info> pb = new PriorityQueue<>();
        int sa = 0, sb = 0, ansCount = 0;
        boolean flag = true;
        while (flag) {
            flag = false;
            add(pa, arrA, sa);
            add(pb, arrB, sb);
            while (!pa.isEmpty() && !pb.isEmpty()) {
                Info ia = pa.peek();
                Info ib = pb.peek();
                if (ia.value == ib.value) {
                    ans.append(ia.value).append(" ");
                    ansCount++;
                    sa = ia.index + 1;
                    sb = ib.index + 1;
                    pa.clear();
                    pb.clear();
                    flag = true;
                    break;
                }
                if (ia.value > ib.value) pa.poll();
                else pb.poll();
            }
        }
        System.out.println(ansCount);
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        arrA = new int[N];
        for (int i = 0; i < N; i++) {
            arrA[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(bf.readLine());
        arrB = new int[M];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < M; i++) {
            arrB[i] = Integer.parseInt(st.nextToken());
        }
        solution();
    }
}
