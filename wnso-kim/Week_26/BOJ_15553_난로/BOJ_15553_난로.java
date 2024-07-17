import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_15553_난로 {
    static int N,K;
    static int[] Ti;
    static Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());

        Ti = new int[N];
        Ti[0] = Integer.parseInt(br.readLine());

        for(int i=1; i<N; i++){
            Ti[i] = Integer.parseInt(br.readLine());
            pq.add(Ti[i] - Ti[i-1] - 1);
        }

        int answer = (Ti[N-1]+1) - Ti[0];

        // 가장 긴 공백 제거
        for(int k=1; k<K; k++){
            answer -= pq.poll();
        }

        System.out.println(answer);
    }
}