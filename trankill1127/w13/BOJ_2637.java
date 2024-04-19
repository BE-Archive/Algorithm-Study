import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_2637 {

	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[n+1][n+1];
		StringTokenizer st;
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int target = Integer.parseInt(st.nextToken());
			int need = Integer.parseInt(st.nextToken());
			int needCnt = Integer.parseInt(st.nextToken());
			arr[target][need]+=needCnt;
		}
		
		//기본 부품이 무엇인지 알아낸다.
		ArrayList<Integer> origin = new ArrayList<>();
		for (int i=1; i<=n; i++) {
			boolean isOrigin=true;
			for (int j=1; j<=n; j++) {
				if (arr[i][j]>0) {
					isOrigin=false;
					break;
				}
			}
			if (isOrigin) {
				origin.add(i);
			}
		}
		
		//n번 부품을 만드는데 필요한 기본 부품의 개수를 조사한다.
		int notOriginCnt;
		do {
			notOriginCnt=0;
			for (int i=1; i<=n; i++) {
				
				for (int j=1; j<=n; j++) {
					if (origin.contains(j)) continue;
					
					for (int k=1; k<=n; k++) {
						arr[i][k]+=arr[j][k]*arr[i][j];
					}
					arr[i][j]=0;
				}
			}
			
			for (int i=1; i<=n; i++) {
				if ( arr[n][i]>0 && !origin.contains(i) ) notOriginCnt++;
			}
		} while (notOriginCnt>0); 
		
		
		StringBuilder sb = new StringBuilder();
		for (int i: origin) {
			sb.append(i).append(" ").append(arr[n][i]).append("\n");
		}
		System.out.println(sb.toString());
	}
}
