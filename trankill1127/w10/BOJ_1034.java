import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_1034 {
	
	public static int[] cnt;
	public static int maxResult=0;
	
	public static Map<String, Integer> map = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		for (int i=0; i<n; i++) {
			String str = br.readLine();
			if (map.get(str)!=null) map.put(str, map.get(str)+1);
			else map.put(str, 1);
		}
		
		int answer=0;
		int k =Integer.parseInt(br.readLine());
		for (String key : map.keySet()) {
			int zeroCnt=0;
			for (int j=0; j<m; j++) {
				if (key.charAt(j)=='0') zeroCnt++;
			}
			
			if (zeroCnt>k) continue;
			if ((k-zeroCnt)%2 == 1) continue;
			
			answer = Math.max(answer, map.get(key));
		}

		System.out.println(answer);
	}
}
