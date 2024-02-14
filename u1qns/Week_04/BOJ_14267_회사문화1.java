import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {

		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int idx, w;

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());		
		int[] tree = new int[N+1]; // 내 보스 저장
		int[] DP = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; ++i)
			tree[i] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<M; ++i)
		{
			st = new StringTokenizer(br.readLine());
			idx = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			DP[idx] += w;
		}

		for(int i=2; i<=N; ++i) DP[i] += DP[tree[i]];
		
		for(int i=1; i<=N; ++i)
			sb.append(DP[i]).append(" ");
		
		System.out.print(sb);
	} // main
}
