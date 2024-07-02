import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	
	static int N, size = 0;
	static int[] indegree, answer;
	static boolean graph[][];

	static boolean solve()
	{
		Queue<Integer> q = new ArrayDeque<>();
		
		for(int i=1; i<=N; ++i)
		{
			if(indegree[i] == 0)
			{
				answer[size++] = i;
				q.offer(i);
			}
		}
		
		while(!q.isEmpty())
		{
			int top = q.poll();
			for(int i=1; i<=N; ++i)
			{
				if(!graph[top][i])
					continue;
				
				
				if(--indegree[i] == 0)
				{
					answer[size++] = i;
					q.offer(i);
				}
			}
			
		}
		
		if(size < N)
		{
			sb.append("0");
			return false;
		}
		
		for(int i=0; i<size; ++i)
			sb.append(answer[i]).append("\n");
		
		return true;
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int M, K, pre, cur;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		indegree = new int[N+1];
		answer = new int[N+1];
		graph = new boolean[N+1][N+1];
		
		Arrays.fill(indegree ,0);
		for(int i=0; i<M; ++i)
		{
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			
			pre = Integer.parseInt(st.nextToken());
			for(int j=1; j<K; ++j)
			{
				cur = Integer.parseInt(st.nextToken());
				
				if(graph[pre][cur] == false) // 중복 처리
				{
					graph[pre][cur] = true;
					indegree[cur]++;
				}
				pre = cur;
			}
		}
	
	
		solve();
	
			
		System.out.print(sb);
	}

}
