import java.io.*;
import java.util.*;

/**
 * 각 영역을 구분짓고 (영역을 정점으로 생각), 다리를 간선으로 생각하면
 * 최소 스패닝 트리처럼 생각할 수 있지 않을까??
 * 필요한 로직
 * 1. 영역 구분
 * 2. 간선 정보 생성
 * 3. 크루스칼 알고리즘 이용
 */

public class Main {
	static int[][] map;
	static ArrayList<ArrayList<Pair>> areas;
	static ArrayList<Edge> edgeList;
	static int N, M, idx = 0;
	static boolean[][] isv;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int[] parents, rank;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		isv = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken()); 
			}
		}
		areas = new ArrayList<ArrayList<Pair>>();
		divideArea();

		edgeList = new ArrayList<Edge>();
		for (int i = 0; i < areas.size(); i++) {
			ArrayList<Pair> list = areas.get(i);
			for (int j = 0; j < list.size(); j++) {
				findPath(list.get(j));
			}
		}
		
		makeSet();
		sb.append(kruskal());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	

	private static int kruskal() {
		int edgeCount = idx - 1;
		int len = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>(edgeList);
		
		while (edgeCount != 0 && !pq.isEmpty()) {
			Edge edge = pq.poll();
			if (union(edge.from, edge.to)) {
				len += edge.dist;
				edgeCount--;
			}
		}
		
		return edgeCount == 0 ? len : -1;
	}
	
	private static boolean union(int v1, int v2) {
		int r1 = find(v1);
		int r2 = find(v2);
		if(r1 == r2) return false;
		
		if(rank[r1] < rank[r2]) {
			parents[v1] = r2;
			return true;
		}
		parents[r2] = r1;
		rank[r1] = rank[r1] == rank[r2] ? rank[r1] + 1 : rank[r1];
		return true;	
	}
	
	private static int find(int v) {
		if(parents[v] == v) return v;
		return parents[v] = find(parents[v]); 
	}
	
	private static void makeSet() {
		parents = new int[idx+1];
		rank = new int[idx+1];
		
		for (int i = 1; i <= idx; i++) {
			parents[i] = i; 
			rank[i] = 1;
		}
	}

	private static void findPath(Pair pair) {
		int len = 0;
		for (int i = 0; i < 4; i++) {
			int x = pair.x + dx[i];
			int y = pair.y + dy[i];
			len = 1;
			
			while(true) {
				if(x < 0 || x >= N || y < 0 || y >= M) break;
				if(map[x][y] == map[pair.x][pair.y]) break;
				if(map[x][y] != 0 && len <= 2) break;
				if(map[x][y] != 0 && len > 2) {
					int dist = Math.abs(pair.x - x) + Math.abs(pair.y - y) - 1;
					edgeList.add(new Edge(dist, map[pair.x][pair.y], map[x][y]));
					break;
				}
				x += dx[i];
				y += dy[i];
				len++;
			}
		}
	}
	
	private static void divideArea() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0 || isv[i][j]) continue;
				divide(i, j, idx++);
			}
		}
	}
	
	private static void divide(int row, int col, int idx) {
		areas.add(new ArrayList<Pair>());
		ArrayList<Pair> vertexList = areas.get(idx);
		
		Queue<int[]> queue = new ArrayDeque<int[]>();
		isv[row][col] = true;
		map[row][col] = (idx + 1);
		vertexList.add(new Pair(row, col, (idx + 1)));
		queue.offer(new int[] {row, col});
		
		while(!queue.isEmpty()) {
			int[] p = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int x = p[0] + dx[i];
				int y = p[1] + dy[i];
				
				if(x < 0 || x >= N || y < 0 || y >= M || isv[x][y] || map[x][y] == 0) continue;
				queue.offer(new int[] {x, y});
				isv[x][y] = true;
				map[x][y] = (idx + 1); 
				vertexList.add(new Pair(x, y, (idx+1)));
			}
		}
	}

}
class Pair{
	int x, y, area;

	public Pair(int x, int y, int area) {
		super();
		this.x = x;
		this.y = y;
		this.area = area;
	}
}

class Edge implements Comparable<Edge>{
	int dist;
	int from, to;
	public Edge(int dist, int from, int to) {
		super();
		this.dist = dist;
		this.from = from;
		this.to = to;
	}
	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.dist, o.dist);
	}
}