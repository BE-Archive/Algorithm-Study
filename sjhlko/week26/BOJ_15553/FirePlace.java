package sjhlko.week26.BOJ_15553;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class FirePlace {
    //https://www.acmicpc.net/problem/15553
    //난로
    static int N, K;
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()) - 1;
        int before = Integer.parseInt(bf.readLine());
        int next = before;
        int start = before;
        for (int i = 1; i < N; i++) {
            next = Integer.parseInt(bf.readLine());
            pq.add(next - before - 1);
            before = next;
        }
        int max = next + 1 - start;
        while (K-- > 0 && !pq.isEmpty()) {
            max -= pq.poll();
        }
        System.out.println(max);

    }
}
