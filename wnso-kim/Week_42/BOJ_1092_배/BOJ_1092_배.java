import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N,M;
    static int[] CRANES, BOXS;
    static int[] sizes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        CRANES = new int[N];
        sizes = new int[N];

        StringTokenizer stk = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            CRANES[i] = Integer.parseInt(stk.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        BOXS = new int[M];

        stk = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            BOXS[i] = Integer.parseInt(stk.nextToken());
        }

        Arrays.sort(CRANES);
        Arrays.sort(BOXS);

        // 크레인으로 이동 가능 여부 판단
        if(!move()){
            System.out.println(-1);
            return;
        }

        int answer = 0;
        for(int i=0; i<N; i++){
            moveToAnotherCrane(i);
            answer = Math.max(answer, sizes[i]);
        }

        System.out.println(answer);
    }

    static boolean move(){
        for(int m=0,n=0; m<M; m++){
            int weight = BOXS[m];

            while(n < N && weight > CRANES[n]){
                n++;
            }

            // 이동 불가능
            if(n==N) return false;

            sizes[n]++;
        }

        return true;
    }

    static void moveToAnotherCrane(int n){
        // crane| {index, size}
        Queue<int[]> pq = new PriorityQueue<>((q1, q2) -> (q1[1] - q2[1]));

        for(int i=n+1; i<N; i++){
            pq.add(new int[]{i, sizes[i]});
        }

        while(!pq.isEmpty() && sizes[n] > pq.peek()[1]){
            sizes[n]--;

            int[] next = pq.poll();
            next[1]++;
            sizes[next[0]]++;

            pq.add(next);
        }
    }
}