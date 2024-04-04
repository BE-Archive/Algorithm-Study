import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class B1423 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

  static int N, D;
  static long sum;

  static int[] cnt, power;
  static long[] DP;


    private static void init() throws IOException
    {
        N = Integer.parseInt(br.readLine());
        cnt = new int[N+1];
        power = new int[N+1];
 
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) 
            cnt[i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i)
            power[i] = Integer.parseInt(st.nextToken());

        D = Integer.parseInt(br.readLine());
        
        
        
        
        
        
        // 플레이 가능 일수로 DP를 만들어야 하는 문제다. 
        DP = new long[D+1];
    }

    private static void solve()
    {
        for (int i = 1; i <= N; ++i)
        {
            sum += (long) cnt[i] * power[i];
            cnt[i] = (cnt[i] < D ? cnt[i] : D);
        }

        // 주어진 캐릭터 레벨대를 1부터 쭉 올려보자
        for (int i = 1; i <= N; ++i)
        {
            // 있는 레벨 (최대 D) 을 다 레벨 업 해보자
            while (cnt[i]-- > 0)
            {
                for (int j = D; j >= 0; --j)
                {
                    for (int k = i + 1; (k <= N) && ((k + j - i) <= D); ++k)
                        DP[k + j - i] = Math.max(DP[k + j - i], DP[j] + power[k] - power[i]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
    	
        
        init();
        
        solve();      
        System.out.println(DP[D] + sum);
        
    } // main
}
