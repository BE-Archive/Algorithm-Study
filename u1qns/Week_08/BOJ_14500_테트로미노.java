import ja미va.io.*;
import java.util.*;


public class Main {

	final static int dx[] = {0, 0, 1, -1};
	final static int dy[] = {1, -1, 0, 0};
	
	static int N, M, arr[][], answer = 0;
	static boolean visited[][];
	
	static void debug()
	{
		System.out.println("=================");
		for(int i=0; i<N; ++i)
		{
			for(int j=0; j<M; ++j)
			{
				System.out.print(visited[i][j] ? arr[i][j] : 0);
			}
			System.out.println();
		}
	}
	
	static boolean isValid(final int x, final int y)
	{
		if(x<0 || y<0 || x>=N || y>=M)
			return false;
		return true;
	}
	
	static void oh(final int x, final int y)
	{
		// ㅓ ㅏ ㅗ ㅜ 
		
		int sum = 0;
		
		// ㅗ ㅜ 
		if(y+2 < M)
		{
			sum = arr[x][y] + (arr[x][y+1] + arr[x][y+2]);
			if(x+1 < N)
			{
				
				answer = (answer > sum + arr[x+1][y+1] ? 
						answer : sum + arr[x+1][y+1]);
			}
			
			if(x-1 >= 0)
			{
				answer = (answer > sum + arr[x-1][y+1] ? 
						answer : sum + arr[x-1][y+1]);
			}
		}
		
		//  ㅏ ㅓ
		if(x+2 < N)
		{
			sum = arr[x][y] + (arr[x+1][y] + arr[x+2][y]);
			
			if(y+1 < M)
			{
				
				answer = (answer > sum + arr[x+1][y+1] ? 
						answer : sum + arr[x+1][y+1]);
			}
			
			if(y-1 >= 0)
			{
				answer = (answer > sum + arr[x+1][y-1] ? 
						answer : sum + arr[x+1][y-1]);
			}
		}
		
		
		
	}
	
	static void solve(final int _x, final int _y, final int sum, final int depth)
	{
		
		if(depth == 4)
		{
			answer = (answer > sum ? answer : sum);
			//debug();
			return;
		}
		
		for(int d=0; d<4; ++d)
		{
			int x = _x + dx[d];
			int y = _y + dy[d];
			
			if(!isValid(x, y) || visited[x][y]) continue;
			
			visited[x][y] = true;
			solve(x, y, sum + arr[x][y], depth + 1);
			visited[x][y] = false;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st= new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visited = new boolean[N][M];
		answer = 0;
		
		for(int i=0; i<N; ++i)
		{
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; ++j)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		
		for(int i=0; i<N; ++i)
		{
			for(int j=0; j<M; ++j)
			{
				visited[i][j] = true;
				oh(i, j);
				solve(i, j, arr[i][j], 1);
				visited[i][j] = false;
			}
		}
		
		System.out.print(answer);
	}
}
