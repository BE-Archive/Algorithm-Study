import java.io.*;
import java.util.*;

public class Main {

	final static int INF = 100001;
	static List<int[]> edges;
	static int[] distance;
	static int N;
	
	static boolean bf(int idx)
	{
		distance[idx] = 0;
		
		for(int i=1; i<=N; ++i)
		{
			for(int j=0; j<edges.size(); ++j)
			{
				int start = edges.get(j)[0];
				int end = edges.get(j)[1];
				int weight = edges.get(j)[2];
				
				//if(distance[start] != INF)
				if(distance[end] > weight + distance[start])
				{
					distance[end] = distance[start] + weight;
					
					if(i==N) return true;
				}
			}
		}
		
		return false;
	}
	
	
	public static void main(String[] args) throws IOException {
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int tc = Integer.parseInt(st.nextToken());
		while(tc-- > 0)
		{			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			int S, E, T;
			edges = new ArrayList<>();
			distance = new int[N+1];
			
			Arrays.fill(distance, INF);
			
			for(int i=0; i<M; ++i)
			{
				st = new StringTokenizer(br.readLine());
				S = Integer.parseInt(st.nextToken());
				E = Integer.parseInt(st.nextToken());
				T = Integer.parseInt(st.nextToken());
				edges.add(new int[] {S, E, T});
				edges.add(new int[] {E, S, T});
			}
			
			for(int i=0; i<W; ++i)
			{
				st = new StringTokenizer(br.readLine());
				S = Integer.parseInt(st.nextToken());
				E = Integer.parseInt(st.nextToken());
				T = Integer.parseInt(st.nextToken());
				edges.add(new int[] {S, E, -T});
      }

			
			sb.append(bf(1) ? "YES\n" : "NO\n");
		}
		System.out.print(sb);
		
	} // main
}
