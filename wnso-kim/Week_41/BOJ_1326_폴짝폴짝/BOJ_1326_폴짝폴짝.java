package BOJ_1326_폴짝폴짝;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1326_폴짝폴짝 {

    static int N;
    static int a,b;
    static int[] jing, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        jing = new int[N+1];
        

        StringTokenizer stk = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){ 
            jing[i] = Integer.parseInt(stk.nextToken());
        }

        stk = new StringTokenizer(br.readLine());
        a = Integer.parseInt(stk.nextToken());
        b = Integer.parseInt(stk.nextToken());

        bfs();

        System.out.println(dp[b]!=Integer.MAX_VALUE? dp[b]: -1);
    }

    static void bfs(){
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(a);

        dp = new int[N+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[a] = 0;

        while(!queue.isEmpty()){
            int cur = queue.poll();

            for(int next=cur+jing[cur]; next<=N; next+=jing[cur]){
                if(dp[next] < dp[cur]+1) continue;

                dp[next] = dp[cur]+1;
                queue.add(next);
            }

            for(int next=cur-jing[cur]; next>0; next-=jing[cur]){
                if(dp[next] < dp[cur]+1) continue;

                dp[next] = dp[cur]+1;
                queue.add(next);
            }
        }
    }
}
