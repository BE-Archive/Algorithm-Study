import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1423_원숭이키우기_실패 {
	static int N,D;
	static long answer = 0;
	static long[] level;
	static long[] power;
	static List<uX> uxs = new LinkedList<>();
	
	static class uX{
		int start;
		int end;
		int totalConsumption;
		double avgConsumptionPerIndex;
		public uX(int start, int end, int totalConsumption, double avgConsumptionPerIndex) {
			super();
			this.start = start;
			this.end = end;
			this.totalConsumption = totalConsumption;
			this.avgConsumptionPerIndex = avgConsumptionPerIndex;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer stk = new StringTokenizer(br.readLine());
		level = new long[N+1];
		for(int i=1; i<=N; i++) level[i] = Long.parseLong(stk.nextToken());
		
		stk = new StringTokenizer(br.readLine());
		power = new long[N+1];
		for(int i=1; i<=N; i++) {
			power[i] = Long.parseLong(stk.nextToken());
			answer += level[i]*power[i];
		}
		
		D = Integer.parseInt(br.readLine());
		
		// 기대값 리스트
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=D; j++) {
				if(i+j>N) break;
				if((power[i+j]-power[i]) <=0) continue;
				
				uxs.add(new uX(i,i+j,j,((power[i+j]-power[i])/(double)j)));
			}
		}
		
		// 정렬
		Collections.sort(uxs, (u1,u2) ->{
			return u1.avgConsumptionPerIndex == u2.avgConsumptionPerIndex? 
				Integer.compare(u1.totalConsumption, u2.totalConsumption):
				Double.compare(u2.avgConsumptionPerIndex, u1.avgConsumptionPerIndex);
		});
		
		// 순회
		for(uX ux: uxs) {
			if(level[ux.start] ==0) continue;
			if(ux.totalConsumption >D) continue;
			
			long count = Math.min(D/ux.totalConsumption, level[ux.start]);
			answer += (power[ux.end]-power[ux.start])*count;
			D -= count*ux.totalConsumption;
			level[ux.start] -= count;
		}
		
		// 출력
		System.out.println(answer);
	}
	
	static void print() {
		for(uX ux: uxs) {
			System.out.println("[start] "+ux.start+
					"  [end] "+ux.end+
					"  [totalConsumption] "+ux.totalConsumption+
					"  [avgConsumptionPerIndex] "+ux.avgConsumptionPerIndex);
		}
	}

}
