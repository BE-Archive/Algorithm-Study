import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_17219 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		HashMap<String, String> dict = new HashMap<>(); 
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine().trim());
			dict.put(st.nextToken(), st.nextToken());
		}
		for (int i=0; i<m; i++) {
			System.out.println(dict.get(br.readLine()));
		}
	}
}
