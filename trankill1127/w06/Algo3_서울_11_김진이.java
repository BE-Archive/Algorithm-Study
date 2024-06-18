import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Algo3_서울_11_김진이 {

	public static class Point {
		int x; int y;
		Point(int x, int y){
			this.x=x;
			this.y=y;
		}
	}
	
	public static int r;
	public static int c;
	public static char[][] land; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		land = new char[r][c];
		for (int i=0; i<r; i++) {
			land[i] = br.readLine().toCharArray();
		}
		//. : 녹은 땅
		//X : 언 땅
		//L : 인간
		
		//사람이 있는 위치를 찾는다.
		Point p1 = null;
		Point p2 = null;
		outer:
		for (int i=0; i<r; i++) {
			for (int j=0; j<c; j++) {
				if (land[i][j]=='L') {
					if (p1==null) {
						p1 = new Point(i,j);
					}
					else {
						p2=new Point(i,j);
						break outer;
					}
				}
			}
		}
		
		int cnt=0;
		while (true) {
			if (meet(p1,p2)) break;
			
			melt();
			cnt++;
			
//			for (int i=0; i<r; i++) {
//				for (int j=0; j<c; j++) {
//					System.out.print(land[i][j]+" ");
//				}
//				System.out.println();
//			}
		}
		
		System.out.println(cnt);
	}
	
	public static int[][] dir = {
			{-1,0}, {1,0}, {0,-1}, {0,1}
	};
	
	public static boolean meet(Point p1, Point p2) {
		boolean[][] visited = new boolean[r][c];
		
		Stack<Point> s = new Stack<>();
		s.add(p1);
		visited[p1.x][p1.y]=true;
		
		while(!s.isEmpty()) {
			Point now =s.pop();
			
			if (now.x==p2.x && now.y==p2.y) return true;
			
			for (int i=0; i<4; i++) {
				int nextX = now.x+dir[i][0];
				int nextY = now.y+dir[i][1];
				
				//System.out.println("next "+nextX+" "+nextY);
				
				if (nextX<0 || nextX>r-1 || nextY<0 || nextY>c-1 || visited[nextX][nextY]) continue;
				
				if (land[nextX][nextY]!='X') {
					s.add(now);
					s.add(new Point(nextX, nextY));
					visited[nextX][nextY]=true;
				}
			}
		}
		
		return false;
	}
	
	public static void melt() {
		Queue<Point> q = new LinkedList<>();
		for (int i=0; i<r; i++) {
			for (int j=0; j<c; j++) {
				if (land[i][j]=='.') {
					q.add(new Point(i,j));
				}
			}
		}
		
		while(!q.isEmpty()) {
			Point now =q.poll();
			for (int i=0; i<4; i++) {
				int nextX = now.x+dir[i][0];
				int nextY = now.y+dir[i][1];
				
				if (nextX<0 || nextX>r-1 || nextY<0 || nextY>c-1) continue;
				
				if (land[nextX][nextY]=='X') {
					land[nextX][nextY]='.';
				}
			}
		}
	}
	
}
