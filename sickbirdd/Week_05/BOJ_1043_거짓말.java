import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		boolean[] group = new boolean[N + 1];
		
		st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		for(int i = 0; i < L; i++) {
			int number = Integer.parseInt(st.nextToken());
			group[number] = true;
		}
		
		int[][] parties = new int[M][];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			parties[i] = new int[P];
			for(int j = 0; j < P; j++) {
				parties[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean[] partyGroup = new boolean[M];
		while(true) {
			boolean isChange = false;
			for(int i = 0; i < M; i++) {
				boolean isTrue = false;
				for(int j = 0; j < parties[i].length; j++) {
					if(group[parties[i][j]] == true) {
						isTrue = true;
						break;
					}
				}
				if(partyGroup[i] != isTrue) isChange = true;
				partyGroup[i] = isTrue;
				for(int j = 0; j < parties[i].length; j++) {
					group[parties[i][j]] = isTrue;
				}
			}
			if(isChange == false) break;
		}
		
		int ans = 0;
		for(int i = 0; i < M; i++) {
			if(partyGroup[i] == false) {
				ans++;
			}
		}
		System.out.println(ans);
	}
}