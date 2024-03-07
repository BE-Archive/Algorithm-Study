import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1238 {

    public static class Road implements Comparable<Road>{
        int v;
        int time;

        Road(int v, int time){
            this.v=v;
            this.time=time;
        }

        @Override
        public int compareTo(Road o) {
            return this.time-o.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken()); //인원 수
        int m = Integer.parseInt(st.nextToken()); //경로의 수
        x = Integer.parseInt(st.nextToken()); //목적지 마을

        map = new ArrayList<>();
        for (int i=0; i<=n;i++) map.add(new ArrayList<>());
        for (int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            map.get(s).add(new Road(e,t));
        }

        goTime = new int[n+1];
        backTime = new int[n+1];

        for (int i=1; i<=n; i++){
            if (i==x) continue;
            dajikstra(i,x);
        }
        dajikstra(x,0);

        int maxSumTime=0;
        for (int i=1; i<=n; i++){
            int sum = goTime[i]+backTime[i];
            if (maxSumTime<sum) maxSumTime=sum;
        }
        System.out.println(maxSumTime);
    }

    public static int n;
    public static int x;
    public static ArrayList<ArrayList<Road>> map;
    public static int[] goTime;
    public static int[] backTime;

    public static void dajikstra(int start, int goal){
        int[] timeSum = new int[n+1];
        Arrays.fill(timeSum, Integer.MAX_VALUE);

        PriorityQueue<Road> pq = new PriorityQueue<>();
        timeSum[start]=0;
        pq.add(new Road(start, timeSum[start]));

        while(!pq.isEmpty()){
            Road now = pq.poll();

            for (int i=0; i<map.get(now.v).size(); i++){
                Road next = map.get(now.v).get(i);
                if (now.time+next.time < timeSum[next.v]){
                    timeSum[next.v]=now.time+next.time;
                    pq.add(new Road(next.v, timeSum[next.v]));
                }
            }
        }

        if (goal==x){
            goTime[start]=timeSum[goal];
        }
        else {
            for (int i=1; i<=n; i++)
                backTime[i]=timeSum[i];
        }
    }
}
