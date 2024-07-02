import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<ArrayList<Pair>> adj = new ArrayList<ArrayList<Pair>>();
	static int diameter;
	
	public static class Pair {
		int index;
		int weight;
		Pair(int index, int weight) {
			this.index = index;
			this.weight = weight;
		}
	}
	
	public static int dfs(int node) {
		ArrayList<Pair> currentNode = adj.get(node);
		int firstWeight = 0, secondWeight = 0;
		for(Pair p : currentNode) {
			int childWeight = dfs(p.index) + p.weight;
			if(firstWeight < childWeight) {
				secondWeight = firstWeight;
				firstWeight = childWeight;
			}
			else if(secondWeight < childWeight) {
				secondWeight = childWeight;
			}
		}
		diameter = Math.max(diameter, firstWeight + secondWeight);
		return firstWeight;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<Pair>());
        }
		
		for(int i = 0; i < n - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			ArrayList<Pair> parentNode = adj.get(parent);
			parentNode.add(new Pair(child, weight));
		}
		
		dfs(1);
		System.out.println(diameter);
	}
}