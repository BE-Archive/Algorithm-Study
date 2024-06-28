import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_2515_전시장 {

    static int N,S;
    static int[] dp = new int[20_000_001];
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        S = Integer.parseInt(stk.nextToken());

        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<N; i++){
            stk = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(stk.nextToken());
            int C = Integer.parseInt(stk.nextToken());

            if(map.containsKey(H))  map.put(H, Math.max(map.get(H), C));
            else map.put(H, C);
        }

        for(int i=1; i<=20_000_000; i++){
            dp[i] = dp[i-1];

            if(map.containsKey(i)){
                dp[i] = Math.max(dp[i], dp[i-S] + map.get(i));
            }
        }

        System.out.println(dp[20_000_000]);
    }
}
