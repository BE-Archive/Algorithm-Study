import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ1238 {
	static int N,M,X,answer;
	static List<int[]>[] towns;
	static int[] toX, toHome;

	/**
	 * 다익스트라
	 * all to x
	 * x to all
	 */
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력| N M X
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		X = Integer.parseInt(stk.nextToken());

		// 초기화| 마을 지도
		towns = new List[N+1];
		for(int i=0; i<=N; i++) {
			towns[i] = new ArrayList<>();
		}
		
		// 입력| 마을 지도
		for(int i=0; i<M; i++) {
			stk = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(stk.nextToken());
			int e = Integer.parseInt(stk.nextToken());
			int w = Integer.parseInt(stk.nextToken());
			towns[s].add(new int[] {e,w});
		}
		
		// all to x
		toX = new int[N+1];
		oneStepTwoStepPollJjackPollJjackGo2X();
		
		// x to all
		toHome = new int[N+1];
		Arrays.fill(toHome, Integer.MAX_VALUE);
		oneStepTwoStepPollJjackPollJjackGo2Home();
		
		// 결과 저장
		result();
		
		// 출력
		System.out.println(answer);
	}

	
	// all to x
	static void oneStepTwoStepPollJjackPollJjackGo2X() {
		for(int i=1; i<=N; i++) {
			if(i==X) continue;
			toX[i] = go2X(i);
		}
	}
	static int go2X(int index) {
		Queue<int[]> queue = new PriorityQueue<>((i1,i2) -> (i1[1]-i2[1]));
		int[] visit = new int[N+1];
		Arrays.fill(visit, Integer.MAX_VALUE);
		
		// index 마을 기준으로 다익스트으으으라
		queue.add(new int[] {index,0});
		visit[index] = 0;
		
		while(!queue.isEmpty()) {
			int[] town = queue.poll();
			
			for(int[] next: towns[town[0]]) {
				// 이전에 방문했던 마을
 				if(visit[next[0]] <= town[1]+next[1]) continue;
				
 				// 방문
 				visit[next[0]] = town[1]+next[1];
 				queue.add(new int[] {next[0], town[1]+next[1]});
			}
		}
	
		return visit[X];
	}
	
	
	// x to all
	static void oneStepTwoStepPollJjackPollJjackGo2Home() {
		Queue<int[]> queue = new PriorityQueue<>((i1,i2) -> (i1[1]-i2[1]));
		
		// X 마을 기준으로 다익스트으으으라
		queue.add(new int[] {X,0});
		toHome[X] = 0;
		
		while(!queue.isEmpty()) {
			int[] town = queue.poll();
			for(int[] next: towns[town[0]]) {
				// 이전에 방문했던 마을 비교
 				if(toHome[next[0]] <= town[1]+next[1]) continue;
				
 				// 방문
 				queue.add(new int[] {next[0], town[1]+next[1]});
 				toHome[next[0]] = town[1]+next[1];
			}
		}
	}
	
	// result
	static void result() {
		answer = 0;
		for(int i=1; i<=N; i++) {
			answer = Math.max(answer, toX[i]+toHome[i]);
		}
	}
}

