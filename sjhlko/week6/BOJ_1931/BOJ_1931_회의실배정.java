package sjhlko.week6.BOJ_1931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1931_회의실배정 {
    // https://www.acmicpc.net/problem/1931
    // 회의실 배정
    static PriorityQueue<MeetingInfo> pq = new PriorityQueue<>();

    static class MeetingInfo implements Comparable<MeetingInfo> {
        int start;
        int end;

        public MeetingInfo(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(MeetingInfo o) {
            if (end != o.end)
                return end - o.end;
            return start - o.start;
        }
    }

    static int solution() {
        int ans = 0;
        int end = -1;
        while (!pq.isEmpty()) {
            MeetingInfo m = pq.poll();
            if (end <= m.start) {
                ans++;
                end = m.end;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.add(new MeetingInfo(start, end));
        }
        System.out.println(solution());
    }

}
