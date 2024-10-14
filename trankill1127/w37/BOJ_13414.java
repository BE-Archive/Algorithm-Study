package trankill1127.w37;

import java.io.*;
import java.util.*;

public class BOJ_13414 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken()); // 수강 가능 인원
		int L = Integer.parseInt(st.nextToken()); // 대기목록의 길이

		Map<String, Integer> clickCnt = new HashMap<>();
		Queue<String> list = new LinkedList<>();
		for (int i=0; i<L; i++){
			String student = br.readLine();
			list.add(student);
			int cnt = clickCnt.getOrDefault(student, 0);
			clickCnt.put(student, cnt+1);
		}

		StringBuilder sb = new StringBuilder();
		while (!list.isEmpty()) {
			String cur = list.poll();

			int cnt = clickCnt.get(cur);
			if (cnt==1) {
				sb.append(cur).append("\n");
				K--;
				if (K==0) break;
			}
			clickCnt.put(cur, cnt-1);
		}

		System.out.print(sb.toString());
	}

}
