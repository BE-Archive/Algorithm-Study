package seoyoung059.Week_40.BOJ_17175;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17175 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] fibo = new int[n+3];
        fibo[0] = 1;
        fibo[1] = 1;
        for (int i = 2; i <= n; i++) {
            fibo[i] = (1+fibo[i-1] + fibo[i-2]) % 1000000007;
        }

        System.out.println(fibo[n]);
    }
}
