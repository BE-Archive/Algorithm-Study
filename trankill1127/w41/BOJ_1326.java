package trankill1127.w41;

import java.io.*;
import java.util.*;

public class BOJ_1326 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[] ns = new int[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1; i<n+1; i++) {
			ns[i]=Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int a =Integer.parseInt(st.nextToken());
		int b =Integer.parseInt(st.nextToken());

		boolean[] vs = new boolean[n+1];
		Queue<Integer> q = new ArrayDeque<>();
		q.add(a);
		vs[a]=true;

		int jumpCnt=0;

		boolean isFound=false;

		outer:
		while (!q.isEmpty()){

			int size = q.size();
			jumpCnt++;

			//System.out.println(size+" "+jumpCnt);

			while (size>0) {
				int now = q.poll();

				if (now==b) {
					isFound=true;
					break outer;
				}

				for (int i=now-ns[now]; i>=1; i-=ns[now]) {
					if (vs[i]) continue;
					q.add(i);
					vs[i]=true;
				}
				for (int i=now+ns[now]; i<=n; i+=ns[now]) {
					if (vs[i]) continue;
					q.add(i);
					vs[i]=true;
				}

				size--;
			}

		}

		if (!isFound) jumpCnt=0;
		System.out.print(jumpCnt-1);
	}

}
