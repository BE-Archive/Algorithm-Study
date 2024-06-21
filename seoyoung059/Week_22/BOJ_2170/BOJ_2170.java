package Week_22.BOJ_2170;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2170 {

    static class Line implements Comparable<Line>{
        int s;
        int e;

        Line(int s, int e){
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Line o) {
            return this.s - o.s;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        PriorityQueue<Line> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int answer = 0;
        int start = -Integer.MAX_VALUE;
        int end = -Integer.MAX_VALUE;
        Line curr;
        while(!pq.isEmpty()){
            curr = pq.poll();
            if(end < curr.s){
                answer += end - start;
                start = curr.s;
                end = curr.e;
            } else if(end < curr.e) {
                end = curr.e;
            }
        }

        answer += end - start;
        System.out.println(answer);
    }
}