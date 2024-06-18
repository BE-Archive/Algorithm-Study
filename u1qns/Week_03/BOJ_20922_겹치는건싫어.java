import java.io.*;
import java.util.*;
	
public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N, K, arr[], answer = 0;
		
		//input
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; ++i)
			arr[i] = Integer.parseInt(st.nextToken());
		
		
		//solve
		int start, end, count[] = new int[100001];
		start = end = 0;
		
		while (end < N)
		{
		    if (count[arr[end]] == K) // 겹치는 조건이 안 맞아
		    {
		        --count[arr[start++]]; // 맨 앞을 조정해볼게 ! 
						// 안 겹칠 때까지 start가 땡겨지면서 알아서 조정됨
		    }
		    else
		    {
						// 조건이 맞으니 end++로 계속 범위 오른쪽으로 넓혀보자
		        answer = answer > end - start ? answer : end - start;
		        ++count[arr[end++]];
		    }
		}		
		
		//output
		System.out.print(answer+1); // end - start로 계산하면서 1이 빠졌으니 추가
	}
}
