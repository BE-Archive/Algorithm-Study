package sjhlko.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_7983_내일할거야 {
    //https://www.acmicpc.net/problem/7983
    //내일 할거야
    static int N;
    static PriorityQueue<HW> pq = new PriorityQueue<>();
    static class HW implements Comparable<HW> {
        int d;
        int t;
        public HW(int d, int t) {
            this.d = d;
            this.t = t;
        }

        @Override
        public int compareTo(HW o) {
            return Integer.compare(o.t, t);
        }
    }

    static int solution() {
        int due = pq.peek().t;
        while (!pq.isEmpty()) {
            HW hw = pq.poll();
            due = Math.min(due, hw.t);
            due -= hw.d;
        }
        return due;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            pq.add(new HW(a, b));
        }
        System.out.println(solution());
    }
}
