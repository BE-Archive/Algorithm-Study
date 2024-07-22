package trankill1127.w27;

import java.io.*;
import java.util.*;

public class BOJ_1158 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		Deque<Integer> deque = new ArrayDeque();
		for (int i = 1; i <= n; i++)
			deque.offerLast(i);

		StringBuilder sb = new StringBuilder("<");
		int cnt = 0;
		while (deque.size() > 0) {
			cnt++;
			if (cnt == k) {
				sb.append(deque.pollFirst());
				if (deque.size() > 0)
					sb.append(", ");
				else
					sb.append(">");
				cnt = 0;
				continue;
			} else
				deque.offerLast(deque.pollFirst());
		}

		System.out.print(sb.toString());
	}
}
