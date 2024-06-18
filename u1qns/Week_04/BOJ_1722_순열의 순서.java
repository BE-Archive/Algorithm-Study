import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb;
	
	static int N, K, arr[];
	static long[] fact;
	
	public static void main(String[] args) throws IOException {

		sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		fact = new long[N+1];
		arr = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		switch(Integer.parseInt(st.nextToken()) )
		{
			case 1 : 
			{
				K = Integer.parseInt(st.nextToken());
				func1();
			} break;
			case 2 : 
			{
				for(int i=1; i<=N; ++i)
					arr[i] = Integer.parseInt(st.nextToken());
				func2();
			} break;
		}
		
		System.out.print(sb);
		
	} // main

  /***********************[ TODO func1 ]**************************************/
	private static void func1() {
		
		boolean isSelected[] = new boolean[N+1];
		long target = 1; // 순서 시작은 0이 아니라 1
		int idx = 1; // 왼쪽에서 몇번째
		int order = 0;
		
		while(true)
		{
			order = 0;
			for(int i=1; i<=N; ++i)
			{
				if(isSelected[i]) continue;

				//long range = factorial(N-idx);
				
				if(range > K)
				{
					arr[idx] = i;
					isSelected[i] = true;
					break;
				}
			}

			if(target == K || idx++ > N)
				break;
		}
		
		//getAnswer
		for(int i=1; i<=N; ++i)
			sb.append(arr[i]).append(" ");		
	}
	
	// k 출력
	private static void func2() {

		boolean isSelected[] = new boolean[N+1];
		
		long target = 1; // 순서 시작은 0이 아니라 1
		int idx = 1; // 왼쪽에서 몇번째
		int order = 0;

		while(true)
		{
			order = 0;
			for(int i=1; i<=N; ++i)
			{
				if(isSelected[i]) continue;
				
				order ++;
				
				if(arr[idx] != i) continue;
				
				target += factorial(N-idx)*(order-1);
				isSelected[i] = true;
				break;
			}
			
			// 모든 원소들이 선택되면 진행할 필요가 없다.
			// 혹시 안 멈출까봐 두번째 조건
			if(order == 0 || idx++ > N)
				break;
		}
		
		//getAnswer
		sb.append(target);
	}

	private static long factorial(int n)
	{
		if(n < 2)
			return fact[n]= 1;
		
		return fact[n] = (fact[n] == 0 ? (factorial(n-1) * n) : fact[n]);
	}

}
