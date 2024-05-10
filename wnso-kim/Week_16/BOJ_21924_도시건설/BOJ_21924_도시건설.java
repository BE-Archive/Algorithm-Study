package Week_16.BOJ_21924_도시건설;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_21924_도시건설 {
	static long answer = 0l;
	static int N,M;
	static Node[] nodes;
	
	static class Node{
		int index;			// 노드 번호
		boolean selected;	// 그룹에 속했는지 여부
		List<int[]> adj;	// 인접 노드들
		
		public Node(int index) {
			this.index = index;
			selected = false;
			adj = new LinkedList<>();
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력| N,M
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		// 초기화| nodes
		nodes = new Node[N+1];
		for(int i=0; i<=N; i++) {
			nodes[i] = new Node(i);
		}
		
		// 입력| nodes, answer
		for(int i=0; i<M; i++) {
			stk = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());
			
			nodes[a].adj.add(new int[] {b,c});
			nodes[b].adj.add(new int[] {a,c});
			
			answer += c;
		}
		
		// 프림 알고리즘
		twoThousandYearsLater();
		
		// 조건| -1
		for(int i=1; i<=N; i++) {
			if(!nodes[i].selected) {
				answer = -1;
				break;
			}
		}
		
		// 출력
		System.out.println(answer);
	}
	
	static void twoThousandYearsLater() {
		// 우선순위 큐 생성| 가중치 오름차순
		Queue<int[]> queue = new PriorityQueue<>((a1, a2) -> a1[1]-a2[1]);
		
		// 1번 노드 선택 및 큐에 인접 노드 추가
		nodes[1].selected = true;
		queue.addAll(nodes[1].adj);
		
		// 큐 순회
		while(!queue.isEmpty()) {
			int[] state = queue.poll();
			int target = state[0];
			int weight = state[1];
			
			// 선택 불가| 이미 그룹에 속한 경우 
			if(nodes[target].selected) continue;
			
			// 선택 가능| 인접 노드  큐에 추가 -> target 기준 
			for(int[] next: nodes[target].adj) {
				// 선택 불가| 이미 그룹에 속한 경우 
				if(nodes[next[0]].selected) continue;
				
				queue.add(next);
			}
			
			// 선택| target
			nodes[target].selected = true;
			answer -= weight;
		}
	}

}
