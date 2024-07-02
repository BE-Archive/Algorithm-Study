import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18427_함께블록쌓기 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());
		int MAX = Integer.parseInt(stk.nextToken());
		
		int[][] blocks = new int[N][];
		for(int i=0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			
			int size = stk.countTokens();
			blocks[i] = new int[size];
			for(int j=0; j<size; j++) {				
				blocks[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		int[] oldDP = new int[MAX+1];
		
		for(int[] block: blocks) {
			int[] newDP = new int[MAX+1];

			for(int num=1; num<=MAX; num++) {
				newDP[num] = oldDP[num];

				for(int b: block){
					int minus = num - b;

					if(minus < 0) continue;
					else if(minus == 0) newDP[num] += 1;
					else if(oldDP[minus] > 0) newDP[num] += oldDP[minus];

					newDP[num] = newDP[num]%10007;
				}
			}
			
			oldDP = newDP;
		}

		System.out.println(oldDP[MAX]);
	}
}
