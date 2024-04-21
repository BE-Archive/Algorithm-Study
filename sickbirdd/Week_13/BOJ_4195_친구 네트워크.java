import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static class UnionFind {
		Map<String, String> par;
		Map<String, Integer> size;
		
		UnionFind() {
			par = new HashMap<String, String>();
			size = new HashMap<String, Integer>();
		}
		String find(String x) {
			String parX = par.getOrDefault(x, "");
			if(parX.equals("")) return x;
			par.put(x, find(parX));
			return par.get(x);
		}
		int merge(String a, String b) {
			String rootA = find(a), rootB = find(b);
			int sizeA = size.getOrDefault(rootA, 1), sizeB = size.getOrDefault(rootB, 1);
			if(rootA.equals(rootB)) return sizeA;
			par.put(rootB, rootA);
			size.put(rootA, sizeA + sizeB);
			return sizeA + sizeB;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(--T >= 0) {
			int F = Integer.parseInt(br.readLine());
			UnionFind uf = new UnionFind();
			for(int i = 0; i < F; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String a = st.nextToken(), b = st.nextToken();
				sb.append(uf.merge(a, b)).append('\n');
			}
		}
		System.out.println(sb);
	}
}