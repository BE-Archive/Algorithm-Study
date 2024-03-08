import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main
{
	public static void main(String args[]) throws Exception
	{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		sb = new StringBuilder();
		 
		 n = Integer.parseInt(st.nextToken());
		 int m = Integer.parseInt(st.nextToken());
		 
		 graph = new ArrayList<>();
		 for (int i=0; i<=n; i++) {
			 graph.add(new ArrayList<>());
		 }
		 
		 inCnt = new int[n+1];
		 
		 for (int i=0; i<m; i++) {
			 st = new StringTokenizer(br.readLine(), " ");
			 int cnt = Integer.parseInt(st.nextToken());
			 int prev = Integer.parseInt(st.nextToken());
			 for (int j=1; j<cnt; j++) {
				 int now = Integer.parseInt(st.nextToken());
				 graph.get(prev).add(now);
				 inCnt[now]++;
				 prev=now;
			 }
		 }
		 
		 bfs();
		 System.out.println(sb.toString());
	}
	
	public static StringBuilder sb;
	public static ArrayList<ArrayList<Integer>> graph;
	public static int n;
	public static int[] inCnt;
			
	public static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		for (int i=1; i<=n; i++) {
			if (inCnt[i]==0) q.add(i);
		}
		
		while(!q.isEmpty()) {
			int now=q.poll();
			sb.append(now+"\n");
			
			for (int next: graph.get(now)) {
				if (inCnt[next]>0) {
					inCnt[next]--;
					if (inCnt[next]==0) q.add(next);
				}
			}
		}
		
		int allCnt=0;
		for (int v : inCnt) {
			if (v==0) allCnt++;
		}
		if (allCnt!=n+1) {
			sb = new StringBuilder();
			sb.append(0);
		}
	}
}
