package Week_38.BOJ_17845;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17845 {

        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] dp = new int[n+1];
            int i, t;
            for(int j=0; j<k; j++){
                st = new StringTokenizer(br.readLine());
                i = Integer.parseInt(st.nextToken());
                t = Integer.parseInt(st.nextToken());
                for(int l=n; l>=t; l--){
                    dp[l] = Math.max(dp[l-t] + i, dp[l]);
                }
            }
            System.out.println(dp[n]);
        }
    }
