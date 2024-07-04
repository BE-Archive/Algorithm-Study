import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Flower> pq = new PriorityQueue<>();

        // 입력 N
        int N = Integer.parseInt(br.readLine());

        // 입력 꽃: 개화,낙화
        for(int i=0; i<N; i++){
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int sMonth = Integer.parseInt(stk.nextToken());
            int sDay = Integer.parseInt(stk.nextToken());
            int eMonth = Integer.parseInt(stk.nextToken());
            int eDay = Integer.parseInt(stk.nextToken());

            if(eMonth<3 || 11<sMonth) continue;

            pq.offer(new Flower(sMonth, sDay, eMonth, eDay));
        }

        // 꽃 확인
        Flower ppre = new Flower(0, 0, 0, 0);
        Flower pre = new Flower(1, 1, 3, 1);
        int result = 0;
        while(!pq.isEmpty()){
            Flower cur = pq.poll();

            // cur낙화 <= pre낙화: PASS
            if(compare(pre.eMonth, pre.eDay, cur.eMonth, cur.eDay) >=0) continue;
            // pre낙화 < cur개화: NOT FOUND
            if(compare(pre.eMonth, pre.eDay, cur.sMonth, cur.sDay) <0) break;

            // ppre낙화 >= cur개화 
            // ppre ----
            // pre   ----
            // cur    ------
            if(compare(ppre.eMonth, ppre.eDay, cur.sMonth, cur.sDay) >=0){
                pre = cur;
            }
            // ppre낙화 < cur개화
            // ppre ----
            // pre   ----
            // cur      ------
            else{
                ppre = pre;
                pre = cur;
                result++;
            }

            if(compare(12,1,cur.eMonth,cur.eDay) <=0){
                System.out.println(result);
                return;
            }
        }

        // 출력
        System.out.println(0);
        return;
    }

    static class Flower implements Comparable<Flower>{
        int sMonth,sDay;
        int eMonth,eDay; 

        public Flower(int sMonth, int sDay, int eMonth, int eDay) {
            this.sMonth = sMonth;
            this.sDay = sDay;
            this.eMonth = eMonth;
            this.eDay = eDay;
        }

        @Override
        public int compareTo(Flower f) {
            if(this.sMonth == f.sMonth){
                if(this.sDay == f.sDay){
                    return compare(f.eMonth, f.eDay, this.eMonth, this.eDay);
                }
                return this.sDay-f.sDay;
            }
            return this.sMonth-f.sMonth;
        }
    }

    static int compare(int m1, int d1, int m2, int d2){
        return m1==m2? d1-d2: m1-m2;
    }
}