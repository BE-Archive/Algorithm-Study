import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int user,m,n,answer;
    static int[] M,N;
    static Queue<Integer> A = new ArrayDeque<>();
    static Queue<Integer> B = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        user = Integer.parseInt(br.readLine());

        StringTokenizer stk = new StringTokenizer(br.readLine());
        m = Integer.parseInt(stk.nextToken());
        n = Integer.parseInt(stk.nextToken());

        M = new int[1000*m+1];
        N = new int[1000*n+1];

        for(int i=0; i<m; i++){
            A.offer(Integer.parseInt(br.readLine()));
        }
        
        for(int i=0; i<n; i++){
            B.offer(Integer.parseInt(br.readLine()));
        }

        fill(m, M, A);
        fill(n, N, B);
        
        find(m, M, N);
        find(n, N, M);

        System.out.println(answer);
    }

    static void fill(int size, int[] dp, Queue<Integer> pizza){
        for(int i=0; i<size; i++){
            int sum = 0;
            for(int j=0; j<size; j++){
                int piece = pizza.poll();
                dp[sum+=piece]++;
                pizza.offer(piece);
            }
            dp[sum] = 1;
            pizza.offer(pizza.poll());
        }
    }

    static void find(int size, int[] dp1, int[] dp2){
        for(int i=1000*size; i>0; i--){
            if(dp1[i]==0) continue;

            if(i==user){
                answer += dp1[i];
                dp1[i] = 0;
            }
            else if(user-i>0 && dp2[user-i]!=0){
                answer += dp1[i]*dp2[user-i];
                dp1[i] = dp2[user-i] = 0;
            }
        }
    }
}
