import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1423 {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); //최대 레벨
		
		heroes = new int[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i=1; i<=n; i++) { //레벨 당 캐릭터 수
			heroes[i]=Integer.parseInt(st.nextToken());
		}
		
		maxPower=0;
		power = new int[n+1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i=1; i<=n; i++) { //레벨 당 힘
			power[i]=Integer.parseInt(st.nextToken());
			maxPower+=(heroes[i]*power[i]);
		}
		
		d = Integer.parseInt(br.readLine()); //총 훈련 일수

		combi(0, maxPower);
		System.out.print(maxPower);
		
	}
	
	public static int n;
	public static int d;
	public static int[] heroes;
	public static int[] power;
	public static int maxPower=0;
	
	public static void combi(int date, int sumPower) {
		if (date==d) {
			maxPower = Math.max(maxPower, sumPower);
			return;
		}
		
		for (int i=n-1; i>=1; i--) {
			if (heroes[i]!=0) {
				heroes[i]--; heroes[i+1]++;
				combi(date+1, sumPower+(power[i+1]-power[i]));
				heroes[i]++; heroes[i+1]--;
			}
		}
	}
}
