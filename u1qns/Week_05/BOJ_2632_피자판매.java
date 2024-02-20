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

	static boolean[] isSelected; // 이미 탐색해본 합
	
	static void pickA()
	{
		// A피자로 만들 수 있는 조합을 구한다.
		// 구한 합에서 K를 빼서 그 값을 B피자로 만들 수 있는지 본다.
		int start=0, end=0, sum = 0;
		for(int i=0; i<N; ++i)
			pickB(A[i]);
		
		while(start < N)
		{
			//pickB(sum);
			
			if(sum <= K)
			{
				sum += A[end];
				end = (end+1)%N;
				pickB(sum);
			}
			else if(sum > K)
			{
				sum-=A[start++];
			}
		}
		
	}
	
	static void pickB(final int rest)
	{
		//System.out.printf("A SUM : %d\n", rest);
		if(rest > K)
			return;
		

		
		int start = 0, end = 0, sum = rest;
		while(start < M)
		{
			if(sum == K)
			{
				//System.out.printf("[ANSWER] : %d + [%d, %d] \n", rest, start, end);
				++answer;
				return;
			}
			
			if(sum < K)
			{
				sum += B[end];
				end = (end+1)%M;
			}
			else if(sum > K)
			{
				sum -= B[start++];
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException {
	
		br = new BufferedReader(new InputStreamReader(System.in));

		K = Integer.parseInt(br.readLine());
		isSelected = new boolean [K];

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		A = new int[N]; B = new int[M];
		for(int i=0; i<N; ++i)
			A[i] = Integer.parseInt(br.readLine());
		
		for(int i=0; i<M; ++i)
			B[i] = Integer.parseInt(br.readLine());
		
		
		pickA();
		
		System.out.print(answer);
		
	} // main

	
}
