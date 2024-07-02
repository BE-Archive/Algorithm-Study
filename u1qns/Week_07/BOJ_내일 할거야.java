import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
				
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));
		
		int t, due;
		int answer = 0;
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; ++i)
		{
			st = new StringTokenizer(br.readLine());
			t = Integer.parseInt(st.nextToken());
			due = Integer.parseInt(st.nextToken());
			pq.offer(new int[] {due, t} );
		}

		answer = pq.peek()[0];
		while(!pq.isEmpty())
		{
			answer = Math.min(pq.peek()[0], answer);
			answer -= pq.peek()[1];
			pq.poll();
		}
		
		System.out.print(answer);
	}

}
