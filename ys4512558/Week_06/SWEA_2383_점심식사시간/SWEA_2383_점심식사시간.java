import java.io.*;
import java.util.*;

public class SWEA2383 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	
	static int N, min, people;
	static int[][] map;
	static Pair[] stairs;
	static ArrayList<Pair> stair1;
	static ArrayList<Pair> stair2;
	static boolean[] isSelected;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= T; i++) {
			sb.append("#").append(i).append(" ");
			sb.append(solve()).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static void init() {
		map = new int[N][N];
		stairs = new Pair[2];
		stair1 = new ArrayList<Pair>();
		stair2 = new ArrayList<Pair>();

		min = Integer.MAX_VALUE;
		people = 0;
	}

	private static int solve() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		init();
		
		int idx = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] >= 2) stairs[idx++] = new Pair(i, j, map[i][j]);
				if(map[i][j]== 1) people++;
			}
		}
		
		bfs(stairs[0], stair1);
		bfs(stairs[1], stair2);
		
		//부분 집합 생성

		isSelected = new boolean[people];
		powerSet(0);
		return min;
	}

	private static void powerSet(int depth) {
		if(depth == people) {
			Queue<Pair> queue1 = new ArrayDeque<Pair>();
			Queue<Pair> queue2 = new ArrayDeque<Pair>();
			
			for (int i = 0; i < people; i++) {
				if(isSelected[i]) queue1.offer(stair1.get(i));
				else queue2.offer(stair2.get(i));
			}
			int time1 = DownStair(queue1, stairs[0]);
			int time2 = DownStair(queue2, stairs[1]);
			min = Math.min(min, Math.max(time1, time2));
			return;
		}
		
		isSelected[depth] = true;
		powerSet(depth + 1);
		isSelected[depth] = false;
		powerSet(depth + 1);
	}

	private static int DownStair(Queue<Pair> queue, Pair stair) {
		int time = 0;
		int cnt = 0;
		Queue<Pair> waitQueue = new ArrayDeque<Pair>();
		while (!queue.isEmpty()) {
			int size = queue.size();
			time++;
			while(size-- > 0) {
				Pair p = queue.poll();
				
				//내려가기 가능 3명 덜 됨
				if(cnt < 3 && p.breadth == stair.breadth) {
					queue.offer(new Pair(p.x, p.y, p.breadth - 1));
					cnt++;
				} else if(cnt == 3 && p.breadth == stair.breadth) {
					//아직 못가는 경우 계단 대기 큐에 넣음.
					waitQueue.offer(new Pair(p.x, p.y, p.breadth)); 
				} else if (p.breadth == 0) {
					cnt--;
					if(waitQueue.isEmpty()) continue;
					Pair next = waitQueue.poll();
					queue.offer(new Pair(next.x, next.y, next.breadth - 1));
					cnt++;
				} else {
					queue.offer(new Pair(p.x, p.y, p.breadth - 1));
				}
			}
		}
		
		return time;
	}

	private static void bfs(Pair stair, ArrayList<Pair> stairList) {
		boolean[][] isv = new boolean[N][N];
		Queue<Pair> queue = new ArrayDeque<Pair>();
		queue.offer(stair);
		isv[stair.x][stair.y] = true;
		
		while (!queue.isEmpty()) {
			Pair pair = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int x = pair.x + dx[i];
				int y = pair.y + dy[i];
				
				if(x < 0 || x >= N || y < 0 || y >= N || isv[x][y]) continue;
				Pair temp = new Pair(x, y, pair.breadth + 1);
				if(map[x][y] == 1) stairList.add(temp);
				queue.offer(temp);
				isv[x][y] = true; 
			}
		}
		
		Collections.sort(stairList);
	}

}
class Pair implements Comparable<Pair>{
	int x, y, breadth;

	public Pair(int x, int y, int breadth) {
		super();
		this.x = x;
		this.y = y;
		this.breadth = breadth;
	}

	@Override
	public int compareTo(Pair o) {
		if(this.x == o.x) {
			return Integer.compare(this.y, o.y);
		}
		return Integer.compare(this.x, o.x);
	}
}