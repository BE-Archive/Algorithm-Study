package sjhlko.week19.BOJ_1461;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Library {
    //https://www.acmicpc.net/problem/1461
    //도서관
    static int N, M;
    static int ans;
    static PriorityQueue<Integer> left = new PriorityQueue<>(), right = new PriorityQueue<>(Collections.reverseOrder());

    static void solution(PriorityQueue<Integer> pq) {
        while (!pq.isEmpty()) {
            int count = M;
            int plus = Math.abs(pq.peek());
            while (!pq.isEmpty() && count > 0) {
                if(pq.peek()==0) {
                    pq.poll();
                    continue;
                }
                plus = Math.max(plus, Math.abs(pq.poll()));
                count--;
            }
            ans += 2 * plus;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        left.add(0);
        right.add(0);
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(st.nextToken());
            if (a < 0) left.add(a);
            else right.add(a);
        }
        int minus = - Math.max(Math.abs(left.peek()), right.peek());
        solution(left);
        solution(right);
        System.out.println(minus + ans);
    }

}
