import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static int answer = 0;
    static class Pair
	{
		public Pair(int idx, int weight)
		{
			this.idx = idx;
			this.weight = weight;
		}
		int idx;
		int weight;
    }
	static Map<Integer, List<Pair> > g = new HashMap<>();
	
    static int solve(int _idx)
	{
		List<Pair> nodes = g.get(_idx);
		if(nodes == null)
			return 0;
		
		int max1=0, max2 = 0;
		
		for(Pair p : nodes)
		{
			int weight = solve(p.idx) + p.weight;
			if(max1 < weight)
			{
				max2 = max1;
				max1 = weight;
			}
			else if(max2 < weight)
			{
				max2 = weight;
			}
		}
		int tmp = max1 + max2;
		answer = (answer > tmp ? answer : tmp);
		return max1;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int p, c, v;

		for(int i=1; i<N; ++i)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			p = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			
			List<Pair> l = g.getOrDefault(p, new LinkedList<Pair>());
			l.add(new Pair(c, v));
			g.put(p, l);
		}
		
		solve(1);
		
		System.out.print(answer);
        
	} // main
}
