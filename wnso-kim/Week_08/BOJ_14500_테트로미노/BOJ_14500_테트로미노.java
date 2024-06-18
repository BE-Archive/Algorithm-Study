import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N,M,result;
	static int[][] map;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력| N,M
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken()) +2;
		M = Integer.parseInt(stk.nextToken()) +2;
		
		// 입력| Map
		map = new int[N+2][M+2];
		for(int i=2; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j=2; j<M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		// 확인
		for(int i=2; i<N; i++) {
			for(int j=2; j<M; j++) {
				addOneBlock(i, j);
				addTwoBlock(i, j);
			}
		}
		
		// 출력
		System.out.println(result);
	}

	// 
	static boolean isOut(int r, int c) {
		return r<2 || c<2 || r>N || c>M;
	}
	
	// 3개 블럭에 1개 블럭을 붙이는 경우
	static int[] drRow3 = {0,1,1,1,0,-1,-1,-1};
	static int[] dcRow3 = {3,2,1,0,-1,0,1,2};
	static int[] drCol3 = {-1,0,1,2,3,2,1,0};
	static int[] dcCol3 = {0,1,1,1,0,-1,-1,-1};
	static void addOneBlock(int r, int c) {
		// 가로3개
		if(!isOut(r,c+2)) {
			int sum = map[r][c]+map[r][c+1]+map[r][c+2];
			for(int i=0; i<8; i++) {
				int row = r+drRow3[i];
				int col = c+dcRow3[i];
				if(isOut(row,col)) continue;
				
				result = Math.max(result, sum+map[row][col]);
			}
		}
		// 세로3개
		if(!isOut(r+2,c)) {
			int sum = map[r][c]+map[r+1][c]+map[r+2][c];
			for(int i=0; i<8; i++) {
				int row = r+drCol3[i];
				int col = c+dcCol3[i];
				if(isOut(row,col)) continue;
				
				result = Math.max(result, sum+map[row][col]);
			}
		}
	}
	
	// 2개 블럭에 2개 블럭을 붙이는 경우
	static int[] dr2 = {-1,-1,-1,1,1,1};
	static int[] dc2 = {-1,0,1,-1,0,1};
	static void addTwoBlock(int r, int c) {
		// 가로2개
		if(!isOut(r,c+1)) {
			int sum = map[r][c]+map[r][c+1];
			for(int i=0; i<6; i++) {
				int row = r+dr2[i];
				int col = c+dc2[i];
				if(isOut(row,col) || isOut(row,col+1))continue;
				
				result = Math.max(result, sum+map[row][col]+map[row][col+1]);
			}
		}
		// 세로2개
		if(!isOut(r+1,c)) {
			int sum = map[r][c]+map[r+1][c];
			for(int i=0; i<6; i++) {
				int row = r+dc2[i];
				int col = c+dr2[i];
				if(isOut(row,col) || isOut(row+1,col))continue;
				
				result = Math.max(result, sum+map[row][col]+map[row+1][col]);
			}
		}
	}
}
