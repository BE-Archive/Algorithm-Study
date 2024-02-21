import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static StringBuilder sb;
	static StringTokenizer st;
	static BufferedReader br;

	static int parent[];
	public static void main(String[] args) throws IOException {
	
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// input
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		parent = new int[N+2];
		for(int i=1; i<=N; ++i)
			parent[i] = -1;
		
		int arr[] = new int[N+1];
		int answer = 0;
		
		st = new StringTokenizer(br.readLine());
		int cnt = Integer.parseInt(st.nextToken());
		int rep = 0;
		
		if(cnt == 0)
		{
			System.out.println(M);
			return;
		}
		
		if(cnt > 0)
		{
            arr[0] = Integer.parseInt(st.nextToken());
			for(int i=1; i<cnt; ++i)
			{
				arr[i] = Integer.parseInt(st.nextToken());
			    union(arr[0], arr[i]);
            }
		}

		boolean flag = false;
		int root = 0;
		int party[][] = new int[M][N+2];
        int size[] = new int[M];
		for(int i=0; i<M; ++i)
		{
			st = new StringTokenizer(br.readLine()); 
			size[i] = Integer.parseInt(st.nextToken());
			
			if(size[i]> 0)
			{
                flag = false;
                party[i][0] = Integer.parseInt(st.nextToken());
				for(int j = 1; j<size[i]; ++j)
				{
					party[i][j] = Integer.parseInt(st.nextToken());
					union(party[i][0], party[i][j]);
					if(find(party[i][j]) == find(arr[0])) flag = true;
				}
				
				if(flag)
				{
					union(party[i][1], arr[0]);
				}
			}
		}
		
		rep = find(arr[0]);
		for(int i=0; i<M; ++i)
		{
			flag = false;
			for(int j=0; j<size[i]; ++j)
			{
				root = find(party[i][j]);
				if(root == rep) 
				{
					flag = true; 
					break;
				};
			}
			answer = (flag == false ? ++answer : answer);
		}

		
		System.out.print(answer);
		
	} // main

	private static int find(final int x)
	{
		if(parent[x] < 0) return x;
		
		return parent[x] = find(parent[x]);
	}
	
	private static boolean union(final int a, final int b)
	{
		int p1 = find(a);
		int p2 = find(b);
		
		if(p1 == p2) return false;
		
		if(Math.abs(p1) < Math.abs(p2))
		{
			parent[p2] += parent[p1];
			parent[p1] = p2;
			
		}
		else //if(Math.abs(p1) < Math.abs(p2))
		{
			parent[p1] += parent[p2];
			parent[p2] = p1;
		}
		
		return true;
	}
}
