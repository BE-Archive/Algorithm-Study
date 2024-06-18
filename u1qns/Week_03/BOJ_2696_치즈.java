import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;
	static int N, M, answer=0, cnt = 0, cheese = 0;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static boolean map[][];

	static boolean isValid(final int x, final int y)
	{
		if(x<0 || y<0 || x>=N || y>=M)
			return false;
		return true;
	}
	
	static class Pair
	{
		int x, y;

		public Pair(final int x, final int y) {
			super();
			this.x = x;
			this.y = y;
		}
	};
	
	
	static boolean solve()
	{
		++answer;

		Queue<Pair> air = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];

		air.add(new Pair(0, 0));
		visited[0][0] = true;
		
		Pair top;
		cnt = cheese;
		
		while(!air.isEmpty())
		{
			top = air.poll();
			visited[top.x][top.y] = true;
			
			for(int d=0; d<4; ++d)
			{
				int x = top.x + dx[d];
				int y = top.y + dy[d];
				
				if(!isValid(x, y)|| visited[x][y])
					continue;
				
				if(map[x][y])
				{
					map[x][y] = false;
					--cheese;
				}				
				else
				{
					air.add(new Pair(x, y));
				}
				
				visited[x][y] = true;
			}
		}
		
		return cheese <= 0 ? true : false;
	}
	
	public static void main(String[] args) throws IOException {
				
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new boolean[N+1][M+1];		
		
		for(int i=0; i<N; ++i)
		{
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; ++j)
			{
				map[i][j] = Integer.parseInt(st.nextToken()) == 0 ? false : true;
				if(map[i][j]) ++cheese;
			}
		}
		
		
		while(true)
		{
			if(solve()) break;
		}
		
		sb.append(answer).append("\n").append(cnt);
		System.out.print(sb);

	} // main
}
