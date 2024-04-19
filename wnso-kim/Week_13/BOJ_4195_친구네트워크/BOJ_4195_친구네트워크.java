import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_4195_친구네트워크 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder result = new StringBuilder();
	static int index;
	static Map<String, Integer> persons;
	
	public static void main(String[] args) throws IOException{
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int F = Integer.parseInt(br.readLine());
			do_you_want_to_build_a_snowman(F);
		}
		
		System.out.println(result);
	}
	
	static void do_you_want_to_build_a_snowman(int F) throws IOException{
		persons = new HashMap<>();
		index = 0;
		
		UnionFind uf = new UnionFind(F*2);
		
		while(F-- > 0) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			
			String person1 = stk.nextToken();
			String person2 = stk.nextToken();
			
			if(!persons.containsKey(person1)) persons.put(person1, index++);
			if(!persons.containsKey(person2)) persons.put(person2, index++);
			
			int index1 = persons.get(person1); 
			int index2 = persons.get(person2); 
			
			result.append(uf.union(index1, index2)).append("\n");
		}
	}

	
	static class UnionFind{
		int size;
		int[] parents;
		
		UnionFind(int size){
			this.size = size;
			parents = new int[size];
			Arrays.fill(parents, -1);
		}
		
		int find(int index) {
			if(parents[index] < 0) return index;
			
			return parents[index] = find(parents[index]);
		}
		
		int union(int i1, int i2) {
			int p1 = find(i1);
			int p2 = find(i2);
			
			if(p1 == p2) return -parents[p1];
			
			parents[p1] += parents[p2];
			parents[p2] = p1;
			
			return -parents[p1]; 
		}
	}
}
