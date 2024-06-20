import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_17952 {

	public static class Work{
		int score;
		int time;
		
		Work(int s, int t){
			score=s;
			time=t;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		
		Stack<Work> s = new Stack<>();
		int totScore=0;
		for (int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int isOrder = Integer.parseInt(st.nextToken());
			if (isOrder==1) {
				int score = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());
				
				time--;
				if (time==0) totScore+=score;
				else s.push(new Work(score, time));
			}
			else { //isOrder==0
				if (!s.empty()){ //스택이 비어있는 경우를 거르지 못하면 런타임 에러가 발생한다!
					Work now = s.pop();

					now.time--;
					if (now.time==0) totScore+=now.score;
					else s.push(now);
				}
			}
		}
		
		System.out.println(totScore);
	}
	
}
