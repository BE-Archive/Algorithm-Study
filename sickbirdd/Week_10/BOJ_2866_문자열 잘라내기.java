import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());
		char[][] mat = new char[R][C];
		
		for(int i = 0; i < R; i++) {
			mat[i] = br.readLine().toCharArray();
		}
		
		StringBuilder[] sb = new StringBuilder[C];
		
		for(int i = 0; i < C; i++) {
			sb[i] = new StringBuilder();
		}
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				sb[j].append(mat[i][j]);
			}
		}
		
		for(int i = 0; i < R; i++) {
			boolean flag = false;
			Set<String> set = new HashSet<>();
			
			for(int j = 0; j < C; j++) {
				sb[j].deleteCharAt(0);
			}
			
			for(int j = 0; j < C; j++) {
				String s = sb[j].toString();
				if(!set.contains(s)) set.add(s);
				else flag = true;
			}
			
			if(flag) {
				System.out.println(i);
				return;
			}
		}
	}
}