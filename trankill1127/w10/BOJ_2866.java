import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		String[] ss = new String[r];
		for (int i=0; i<r; i++) {
			ss[i] = br.readLine();
		}

		for (int len=1; len<=r; len++) { //길이
			boolean isSame=false;

			Set<String> set = new HashSet<>();
			for (int j=0; j<c; j++) { //열

				StringBuilder sb =new StringBuilder();
				for (int i=r-1; i>=r-len; i--) { //행
					sb.append(ss[i].charAt(j));
				}

				//System.out.println(s);
				if (set.contains(sb.toString())) {
					isSame=true;
					break;
				}

				set.add(sb.toString());
			}

			if (!isSame) {
				System.out.println(r-len);
				return;
			}
		}
	}
}
