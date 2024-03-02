import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static class Info implements Comparable<Info> {
		int start, end;
		Info(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Info o) {
			if(o.end == this.end) return this.start - o.start;
			return this.end - o.end;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Info[] rooms = new Info[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			rooms[i] = new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(rooms);
		int answer = 0, recent_end = 0;
		for(int i = 0; i < N; i++) {
			if(rooms[i].start >= recent_end) {
				recent_end = rooms[i].end;
				answer++;
			}
		}
		System.out.println(answer);
	}
}