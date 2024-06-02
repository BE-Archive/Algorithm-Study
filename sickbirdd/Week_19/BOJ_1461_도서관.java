import java.io.*;
import java.util.*;

public class Main {
	static int ans = 0, max_dist = 0;

	static void foo(ArrayList<Integer> lis, int cnt) {
		int idx = 0;
		while(idx < lis.size()) {
			int now = Math.abs(lis.get(idx));
			max_dist = Math.max(max_dist, now);
			ans += now * 2;
			idx += cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		ArrayList<Integer> pos = new ArrayList<>(), neg = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			int number = Integer.parseInt(st.nextToken());
			if(number < 0) neg.add(number);
			else pos.add(number);
		}
		Collections.sort(pos, (a, b) -> Integer.compare(b, a));
		Collections.sort(neg);
		foo(neg, M);
		foo(pos, M);
		System.out.println(ans - max_dist);
	}
}