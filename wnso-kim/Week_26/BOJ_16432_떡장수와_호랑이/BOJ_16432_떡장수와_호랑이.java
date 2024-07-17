import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] TTEOK; 
    static int[][] dp;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력| N
        N = Integer.parseInt(br.readLine());
        TTEOK = new int[N+1][10];

        // 입력| TTEOK 조합
        for(int i=1; i<=N; i++){
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(stk.nextToken());
            TTEOK[i][0] = size;
            
            while(stk.hasMoreTokens()){
                int index = Integer.parseInt(stk.nextToken());
                TTEOK[i][index] = 1;
            }
        }

        // dp
        dp = new int[N+1][10];
        dp[0][0] = 1;
        for(int i=1; i<=N; i++){
            int size = dp[i-1][0];

            for(int j=1; j<10; j++){
                if(TTEOK[i][j] == 0) continue;

                // 이전에 떡을 줄 수 있는 경우
                dp[i][j] = size - (dp[i-1][j]>0? 1:0);
                if(dp[i][j] >0) dp[i][0]++;
            }
        }

        // 역으로 확인
        Stack<Integer> stack = new Stack<>();
        for(int j=1; j<10; j++){
            if(dp[N][j] > 0){
                stack.push(j);
                break;
            }
        }

        if(stack.isEmpty()) {System.out.println(-1); return;}

        for(int i=N-1; i>0; i--){
            for(int j=1; j<10; j++){
                if(dp[i][j] > 0 && j != stack.peek()){
                    stack.push(j);
                    break;
                }
            }
        }

        if(stack.size() != N) System.out.println(-1);
        else{
            while(!stack.isEmpty()){
                System.out.println(stack.pop());
            }
        }
    }
}