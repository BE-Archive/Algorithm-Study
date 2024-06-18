package Week_06.BOJ_1931;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1931 {
    static class P implements Comparable<P>{
        int s;
        int e;

        P(int s, int e){
            this.s = s;
            this.e = e;
        }

        @Override
        public String toString(){
            return "["+this.s+" - "+e+"]";
        }

        @Override
        public int compareTo(P o) {
            if(this.e==o.e) return this.s - o.s;
            return this.e - o.e;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<P> pq = new PriorityQueue<>();
        P curr = new P(-1,-1);
        int answer = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new P(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        while(!pq.isEmpty()){
            if(curr.e <= pq.peek().s){
//                System.out.println(pq.peek());
                curr = pq.poll();
                answer ++;
            }
            else pq.poll();
        }
        System.out.println(answer);
    }
}