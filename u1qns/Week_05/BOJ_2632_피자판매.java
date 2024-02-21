import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static StringBuilder sb;
	static StringTokenizer st;
	static BufferedReader br;

	static int A[], B[];
	static int K, N, M, answer = 0;
	
	static int[] pick(final int pizza[])
	{
		// 피자의 모든 조합을 여기서 구한다 ! 
		int result[] = new int[K+1];
		
		int range = pizza.length;
		int sum = 0, cnt = 0;
		int total = 0;
		for(int i=0; i<range; ++i)
		{
			if(pizza[i] > K) continue;
			
			//System.out.printf("SUM : %d \n", pizza[i]);
			result[pizza[i]]++;
			total += pizza[i];
		}
		
		result[0] = 1;
		
		// 홀 피자 만드는 짓은 한번만 하고 싶다.
		
		for(int i=0; i<range; ++i)
		{
			sum = pizza[i];
			cnt = range-1;
			for(int j=i+1; cnt>0; ++j)
			{
				j = j%range;
				sum += pizza[j];
				if(sum > K) break;
				--cnt;
				//System.out.printf("SUM : %d \n", sum);
				result[sum]++;
			}
		}
		
		if(total <= K)
			result[total] = 1;
		
		return result;
	}
	
	static void solve()
	{
		// 1. A에서 가능한 조합을 만들자. 
		// 이 조합의 합이 K 이하라면 
		// 2. 나머지를 B에서 만들자
		
		int[] sumA = pick(A);
		//System.out.println("\n=====================");
		int[] sumB = pick(B);
		for(int i=0; i<=K; ++i)	
		{
			answer += (sumA[i] * sumB[K-i]);
			//System.out.printf("answer += %d * %d = %d\n", sumA[i], sumB[K-i], answer);

		}
	}
	
	public static void main(String[] args) throws IOException {
	
		br = new BufferedReader(new InputStreamReader(System.in));

		K = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		A = new int[N]; B = new int[M];
		for(int i=0; i<N; ++i)
			A[i] = Integer.parseInt(br.readLine());
		
		for(int i=0; i<M; ++i)
			B[i] = Integer.parseInt(br.readLine());
		
		
		solve();
		
		System.out.print(answer);
		
	} // main

	
}
