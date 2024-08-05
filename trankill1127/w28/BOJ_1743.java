package trankill1127.w28;

import java.io.*;
import java.util.*;

public class BOJ_1743 {
	public static int[][] dir = {
		{-1,0}, {1,0}, {0,1}, {0,-1}
	};
	public static int n;
	public static int m;
	public static boolean[][] map;
	public static boolean[][] visited;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		map = new boolean[n+2][m+2];
		visited = new boolean[n+1][m+1];
		List<int[]> points = new ArrayList<>();
		for (int i=0; i<k; i++){
			st = new StringTokenizer(br.readLine().trim());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			points.add(new int[]{x,y});
			map[x][y]=true;
		}

		int maxSize=0;
		for (int[] now: points){
			if (visited[now[0]][now[1]]) continue;
			maxSize=Math.max(maxSize, solution(now[0], now[1]));
		}
		System.out.println(maxSize);
	}

	public static int solution(int x, int y){
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[]{x,y});
		visited[x][y]=true;

		int totCnt=0;
		while(!q.isEmpty()){
			int cnt=q.size();
			totCnt+=cnt;

			while(cnt-->0){
				int[] now = q.poll();
				for (int i=0; i<4; i++){
					int nx=now[0]+dir[i][0];
					int ny=now[1]+dir[i][1];
					if (!map[nx][ny] || visited[nx][ny]) continue;
					q.add(new int[]{nx,ny});
					visited[nx][ny]=true;
				}
			}
		}

		return totCnt;
	}
}
