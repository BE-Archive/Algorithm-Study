import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main { 
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		Map<String, String> map = new HashMap<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String addr = st.nextToken(), pw = st.nextToken();
			map.putIfAbsent(addr, pw);
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			String addr = br.readLine();
			sb.append(map.get(addr)).append('\n');
		}
		System.out.println(sb);
	}
}