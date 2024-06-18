import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	final static int MAX_SIZE = 100, INF = (int)1e9;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] mat = new int[MAX_SIZE + 1], count = new int[MAX_SIZE + 1];
		for(int i = 1; i <= MAX_SIZE; i++) {
			mat[i] = i;
			count[i] = INF;
		}
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		for(int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			mat[a] = b;
		}
		
		Queue<int[]> queue = new ArrayDeque<>();
		count[1] = 0;
		queue.add(new int[] {1, 0});
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			if(now[1] > count[now[0]]) continue;
			for(int i = 1; i <= 6; i++) {
				if(now[0] + i > MAX_SIZE) break;
				int next = mat[now[0] + i];
				if(count[next] > count[now[0]] + 1) {
					count[next] = count[now[0]] + 1;
					queue.add(new int[] {next, count[next]});
				}
			}
		}
		System.out.println(count[MAX_SIZE]);
	}
}