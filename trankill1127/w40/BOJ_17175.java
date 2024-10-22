package trankill1127.w40;

import java.io.*;

public class BOJ_17175 {
    public static int[] cnt=new int[51];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        cnt[0]=1;
        cnt[1]=1;
        for (int i=2; i<=n; i++){
            cnt[i]=(cnt[i-2]+cnt[i-1]+1)%1000000007;
        }

        System.out.print(cnt[n]);
    }
}
