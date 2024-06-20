package sjhlko.week22.BOJ_2170;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2170_선긋기 {
    //https://www.acmicpc.net/problem/2170
    //선 긋기
    static int N;
    static PriorityQueue<Info> pq = new PriorityQueue<>();

    static class Info implements Comparable<Info> {
        int start, end;

        public Info(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Info o) {
            if(start!=o.start) return start - o.start;
            return end - o.end;
        }
    }

    static int solution() {
        int ans = 0;
        int start = pq.peek().start;
        int end = pq.peek().end;
        while (!pq.isEmpty()) {
            Info now = pq.poll();
            if (end < now.start) {
                ans += end - start;
                start = now.start;
            }
            end = Math.max(end,now.end);
        }
        return ans + end - start;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            pq.add(new Info(s, e));
        }
        System.out.println(solution());
    }
}
