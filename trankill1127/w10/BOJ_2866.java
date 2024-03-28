import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_2866 {

	public static void main(String[] args) throws IOException{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		String[] rev = new String[c];
		for (int i=0; i<r; i++) {
			String s = br.readLine();
			for (int j=0; j<c; j++) {
				if (i==0) rev[j]=""+s.charAt(j);
				else rev[j]+=s.charAt(j);
			}
		}
		
		if (r==2) {
			System.out.println(0);
			return;
		}
		
		for (int i=1; i<r-1; i++) {
			Set <String> set = new HashSet<>();
			
			for (int j=0; j<c; j++) {
				String key = rev[j].substring(i);
				
				if (set.contains(key)) {
					System.out.println(i-1);
					return;
				}
				else set.add(key);
			}
		}
		
		System.out.println(r-1);
	}
}
