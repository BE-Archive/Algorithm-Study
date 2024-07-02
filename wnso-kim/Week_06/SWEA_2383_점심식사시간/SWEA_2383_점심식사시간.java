import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
	
	static int N,answer;
	static List<int[]> peoples; // 사람들 위치 저장
	static int[][] steps;		// 계단 입구 위치, 길이 저장
	static List<int[]> groupA;  // 1번 계단 그룹 
	static List<int[]> groupB;  // 2번 계단 그룹 
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			// 입력| N
			N = Integer.parseInt(br.readLine());
			
			// 초기화| peoples, steps, answer
			peoples = new ArrayList<>();
			steps = new int[2][3];
			answer = Integer.MAX_VALUE;
			
			// 입력| 맵
			int stepIndex = 0;
			for(int i=0; i<N; i++){
				StringTokenizer stk = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++){
					int state = Integer.parseInt(stk.nextToken());
					if(state==1) peoples.add(new int[]{i,j});
					if(state>=2) {
						steps[stepIndex][0] = i;
						steps[stepIndex][1] = j; 
						steps[stepIndex][2] = state; stepIndex++;
					}
				}
			}

			// 시간이 흐르고..
			twoThousnadYearLater();

			// 출력
			result.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		System.out.println(result);
	}

	static void twoThousnadYearLater(){
		int size = peoples.size();

		// 부분집합
		int end = 1<<size;
		for(int i=0; i<end; i++){
			groupA = new ArrayList<>();
			groupB = new ArrayList<>();

			for(int j=0; j<size; j++){
				if((i&1<<j) == 0) groupA.add(peoples.get(j));
				else groupB.add(peoples.get(j));
			}

			// 그룹 별 시간 체크| A그룹:0번 계단, B그룹:1번계단
			int timeA = check(groupA,0);
			int timeB = check(groupB,1);
			answer = Math.min(answer, Math.max(timeA,timeB));

			// 그룹 별 시간 체크| A그룹:1번 계단, B그룹:0번계단
			timeB = check(groupB,0);
			timeA = check(groupA,1);
			answer = Math.min(answer, Math.max(timeA,timeB));
		}
	}

	static int check(List<int[]> group, int stepIndex){
		int size = group.size();
		if(size == 0) return 0;

		// 길이 배열 제작
		Queue<Integer> pq = new PriorityQueue<>();
		for(int i=0; i<size; i++){
			int r = Math.abs(steps[stepIndex][0] - group.get(i)[0]);
			int c = Math.abs(steps[stepIndex][1] - group.get(i)[1]);
			pq.add(r+c);
		}

		// 뚜벅뚜벅..
		return oneStepTwoStepPollJjackPollJjack(pq, steps[stepIndex][2]);
	}

	static int oneStepTwoStepPollJjackPollJjack(Queue<Integer> pq, int stairSize){
		Queue<Integer> stair = new ArrayDeque<>(); // 계단 입성 시간
		int time = 0;

		while(!pq.isEmpty() || !stair.isEmpty()){
			// 틱택톡 틱택톡
			time++;

			// 계단에 있는 사람들 나가!
			int size = stair.size();
			for(int i=0; i<size; i++){
				int length = stair.poll();
				if(time-length < stairSize) stair.add(length); 
			}

			// 다음 사람이 계단에 들어갈 수 있는지 확인
			// 계단에 사람이 3명 미만인 경우 가능
			while(stair.size()<3 && !pq.isEmpty()){
				int length = pq.peek();
				if(length >= time) break;

				pq.poll();
				stair.add(time);
			}
		}

		return time;
	}
}