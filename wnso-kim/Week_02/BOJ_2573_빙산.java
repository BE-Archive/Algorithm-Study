import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int h,w;	// 빙하 맵 행,열 길이
	static Ice[][] ices;	// 빙하 맵
	static Queue<Ice> now = new LinkedList<>(); // 현재 높이가 1 이상인 빙하 리스트
	
	static int[] dxs = {0,1,0,-1};	// 인접 빙하 + xindex
	static int[] dys = {-1,0,1,0};	// 인접 빙하 + yindex
	
	
	// 빙하 정보 저장을 위한 class
	static class Ice{
		int y,x;
		int height;
		boolean marked;
		
		public Ice(int y, int x, int height) {
			this.y = y;
			this.x = x;
			this.height = height;
			this.marked = false;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// input| h,w
		StringTokenizer stk = new StringTokenizer(br.readLine());
		h = Integer.parseInt(stk.nextToken());
		w = Integer.parseInt(stk.nextToken());
		
		// init| ices
		ices = new Ice[h][w];
		for(int y=0; y<h; y++) {
			stk = new StringTokenizer(br.readLine());
			for(int x=0; x<w; x++) {
				int height = Integer.parseInt(stk.nextToken());
				ices[y][x] = new Ice(y,x,height);
				
				// 높이가 0이 아닌경우 now에 add
				if(height>0)
					now.add(ices[y][x]);
			}
		}
		
		// find| 세월 흐름
		System.out.println(twoThousandYearsLater());
	}
	
	// 세월의 흐름... 틱택톡
	static int twoThousandYearsLater() {
		int year = 1;
		
		// n년 동안 빙하 녹이기
		while(!now.isEmpty()) {
			// 1년 후 결과를 담기 위한 nextYear
			Queue<Ice> nextYear = new LinkedList<>();
			
			// 현재 빙하가 있는 경우 -> 빙하들 순회하며 바다인근 빙하 녹이기
			while(!now.isEmpty()) {
				Ice ice = now.poll();
				ice.height -= countOfSea(ice.y, ice.x, year);// 빙하 녹이기
				
				if(ice.height <= 0) {  //빙하가 녹아 바다가 된경우
					//빙하가 될때 까지 몇년 걸렸는지 음수로 저장 -> 0으로 저장하면 인근 빙하들에게 영향이 간다.
					ice.height = -year;	
				}						
				else {
					nextYear.add(ice); // 살아남은 빙하는 내년에 녹이기위해 queue에 넣기		
				}
			}

			// 올해 빙하가 녹았을 때, 2개 이상으로 분리 되었는지 확인
			if(isSeperate((LinkedList)nextYear)) {
				return year;
			}
			
			now = nextYear; // 내년에 다시 확인
			year++;
		}
		
		return 0;
	}
	
	// 빙하 기준 사방의 바다 개수 count
	static int countOfSea(int y, int x, int year) {
		int count = 0;
		for(int i=0; i<4; i++) {
			Ice ice = ices[y+dys[i]][x+dxs[i]];
			if(ice.height > -year && ice.height<=0) {
				count++;
			}
		}
		
		return count;
	}
	
	// 빙하가 2개 이상으로 분리 되었는지 확인하는 dfs 함수
	static boolean isSeperate(List<Ice> nextYear) {
		if(nextYear.isEmpty())
			return true;

		Ice root = nextYear.get(0);
		root.marked = true;
		
		Stack<Ice> stack = new Stack<>();
		stack.add(root);
		
		// dfs로 돌면서 빙하 방문
		while(!stack.isEmpty()) {
			Ice ice = stack.pop();
			int y = ice.y;
			int x = ice.x;
			
			for(int i=0; i<4; i++) {
				Ice cIce = ices[y+dys[i]][x+dxs[i]];
				if(cIce.height>0 && !cIce.marked) {
					cIce.marked = true;
					stack.push(cIce);
				}
			}
		}
		
		// 방문하지 않은 빙하 있는지 확인
		for(Ice ice: nextYear) {
			if(!ice.marked) {
				return true;
			}
		}
		
		// 빙하가 한 개인 경우 -> 체크할 때 사용했던 marked 다시 돌려놓기
		for(Ice i: nextYear)
			i.marked = false;
		
		return false;
	}
	
	
}
