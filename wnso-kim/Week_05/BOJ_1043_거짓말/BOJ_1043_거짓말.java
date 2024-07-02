import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1043 {

	static int N,M;
	static int[] leaders; // 각 사람들이 속한 조직의 대표: 음수는 본인 
	static int[] reliable;
	static int[][] parties; // 각 파티 구성원
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		// 입력 N M
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		leaders = new int[N+1];
		Arrays.fill(leaders, -1);
		
		// 입력 진실을 아는 사람
		stk = new StringTokenizer(br.readLine());
		int size = Integer.parseInt(stk.nextToken());
	
		if(size != 0) {
			reliable = new int[size];
			reliable[0] = Integer.parseInt(stk.nextToken());
			for(int i=1; i<size; i++) {
				reliable[i] = Integer.parseInt(stk.nextToken());
				union(reliable[i-1], reliable[i]);
			}
		}else {
			System.out.println(M);
			return;
		}

		
		// 파티 입력
		// 파티 인원들로 조직 합치기
		parties = new int[M][];
		for(int i=0; i<M; i++) {
			stk = new StringTokenizer(br.readLine());
			size = Integer.parseInt(stk.nextToken());
			parties[i] = new int[size];
			
			parties[i][0] = Integer.parseInt(stk.nextToken());
			for(int j=1; j<size; j++) {
				parties[i][j] = Integer.parseInt(stk.nextToken());
				union(parties[i][j-1], parties[i][j]);
			}
		}
		
		// 진실 혹은 거짓 당신의 선택은?
		int answer = 0;
		int truster = find(reliable[0]); 
		for(int i=0; i<M; i++) {
			int trueOrFalse = find(parties[i][0]);
			if(truster != trueOrFalse)
				answer++;
		}
		
		// 출력
		System.out.println(answer);
	}
	
	static int find(int node) {
		if(leaders[node] < 0) return node;
		return leaders[node] = find(leaders[node]);
	}

	static void union(int a, int b) {
		int node1 = find(a);
		int node2 = find(b);
		
		if(node1 == node2) return;
		if(node1 < node2) {
			leaders[node2] += leaders[node1];
			leaders[node1] = node2;
		}
		else {
			leaders[node1] = leaders[node2];
			leaders[node2] = node1;
		}
	}
	
}
