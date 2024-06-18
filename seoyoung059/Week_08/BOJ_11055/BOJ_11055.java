package Week_08.BOJ_11055;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11055 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());


        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        int tmp; int answer = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            tmp = 0;
            for (int j = 0; j < i; j++) {
                if(arr[j] < arr[i])
                    tmp = Math.max(tmp,dp[j]);
            }
            dp[i] = arr[i]+tmp;
            answer = Math.max(answer, dp[i]);
        }
        System.out.println(answer);
    }
}
