package Week_06.BOJ_14865;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_14865 {


    static class P implements Comparable {
        int s;
        int e;

        P(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Object o) {
            return this.s - ((P) o).s;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        int tmp = Integer.MIN_VALUE;
        int prevy = 0;
        PriorityQueue<P> pq = new PriorityQueue<>();

        int y, x = 0;
        int sy = 0, sx = 0;
        boolean find = false;
        int firstX = Integer.MIN_VALUE;
        boolean first = true;
        for (int i = 0; i < n + 1; i++) {
            if (i < n) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                if (first) {
                    sy = y;
                    sx = x;
                    first = false;
                }
            } else {
                y = sy;
                x = sx;
            }
            if (prevy < 0 && y > 0) {
                tmp = x;
                find = true;
            } else if (prevy > 0 && y < 0) {
                if (find) {
                    pq.offer((tmp < x) ? new P(tmp, x) : new P(x, tmp));
                    find = false;
                } else {
                    firstX = x;
                }
            }
            prevy = y;
        }

        if (firstX != Integer.MIN_VALUE) {
            pq.offer((firstX < tmp) ? new P(firstX, tmp) : new P(tmp, firstX));
        }

        P curr, prev;
        int cnt1 = 0;
        int cnt2 = 0;
        ArrayDeque<P> stack = new ArrayDeque<>();
        while (!pq.isEmpty()) {
            curr = pq.poll();
            /*
               현재 s보다 e가 작은건 다 빼고
               스택이 비면 제일 바깥거
                내거 다음거의 s가 내 end보다 크면 나는 제일 안쪽거
             */
            while (!stack.isEmpty() && stack.peekLast().e < curr.s) {
                stack.pollLast();
            }
            if (stack.isEmpty()) cnt1++;
            if (pq.isEmpty() || pq.peek().s > curr.e) cnt2++;
            stack.offerLast(curr);
        }
        System.out.println(cnt1 + " " + cnt2);
    }
}