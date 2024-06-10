import java.io.*;
import java.util.*;

public class Main {
	final static int MAX_PLAYER = 11;
	static ArrayList<Pair>[] players;
	static class Pair {
		int idx, amount;
		Pair(int idx, int amount) {
			this.idx = idx;
			this.amount = amount;
		}
	}

	static int foo(int sum, int visited, int idx) {
		if(idx == MAX_PLAYER) return visited == (1 << MAX_PLAYER) - 1 ? sum : 0;
		int ans = 0;
		for(Pair p : players[idx]) {
			if((visited & (1 << p.idx)) == 0) {
				ans = Math.max(ans, foo(sum + p.amount, visited | (1 << p.idx), idx + 1));
			}
		}
		return ans;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(--C >= 0) {
			players = new ArrayList[MAX_PLAYER];
			for(int i = 0; i < MAX_PLAYER; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				players[i] = new ArrayList<>();
				for(int j = 0; j < MAX_PLAYER; j++) {
					int ability = Integer.parseInt(st.nextToken());
					if(ability > 0) {
						players[i].add(new Pair(j, ability));
					}
				}
			}
			sb.append(foo(0, 0, 0)).append('\n');
		}
		System.out.println(sb);
	}
}