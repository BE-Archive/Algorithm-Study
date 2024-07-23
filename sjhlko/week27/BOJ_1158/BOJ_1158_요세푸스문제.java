package sjhlko.week27.BOJ_1158;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1158_요세푸스문제 {
    //https://www.acmicpc.net/problem/1158
    //요세푸스 문제
    public static void main(String[] args) throws IOException {
        Queue<Integer> queue = new LinkedList<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            queue.offer(i);
        }
        int count = K;
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        while (queue.peek() != null) {
            count--;
            int next = queue.poll();
            if (count == 0) {
                sb.append(next);
                if (queue.peek() == null) {
                    sb.append(">");
                    break;
                }
                sb.append(", ");
                count = K;
                continue;
            }
            queue.offer(next);
        }
        System.out.println(sb);
    }
}
