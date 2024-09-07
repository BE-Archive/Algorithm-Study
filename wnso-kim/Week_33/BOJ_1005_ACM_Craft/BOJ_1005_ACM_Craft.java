import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1005_ACM_Craft {

    static int T;
    static int N,K;
    static int W;
    static ACM[] acms;

    static class ACM{
        int index;
        int time;
        int size = 0;
        List<Integer> next = new ArrayList<>();

        ACM(int index, int time){
            this.index = index;
            this.time = time;
        }
    }

    static int topologicalSort(){
        Queue<int[]> pq = new PriorityQueue<>((q1, q2) -> (q1[1]-q2[1]));   // index, time

        for(int i=1; i<=N; i++){
            if(acms[i].size == 0){
                pq.add(new int[]{acms[i].index, acms[i].time});
            }
        }

        while(!pq.isEmpty()){
            int[] now = pq.poll();
            int index = now[0];
            int time = now[1];

            if(index == W){
                return time;
            }

            for(int next: acms[index].next){
                if(acms[next].size != 1){
                    acms[next].size--;
                    continue;
                } 

                int nextTime = time + acms[next].time;
                pq.add(new int[]{next, nextTime});
            }
        }

        return 0;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            StringTokenizer stk = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stk.nextToken());
            K = Integer.parseInt(stk.nextToken());

            acms = new ACM[N+1];
            stk = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++){
                acms[i] = new ACM(i, Integer.parseInt(stk.nextToken()));
            }

            for(int i=0; i<K; i++){
                stk = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(stk.nextToken());
                int Y = Integer.parseInt(stk.nextToken());

                acms[X].next.add(Y);
                acms[Y].size++;
            }

            W = Integer.parseInt(br.readLine());

            System.out.println(topologicalSort());
        }
    }
}