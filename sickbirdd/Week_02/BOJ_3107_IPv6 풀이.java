import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static final String identifier = "*";
	static final String groupSymbol = "::";
	static final int IPV6_LENGTH = 8;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String ipv6 = br.readLine().replaceFirst(groupSymbol, ":" + identifier + ":");
		
		StringTokenizer st = new StringTokenizer(ipv6, ":");
		ArrayList<String> hexList = new ArrayList<String>(IPV6_LENGTH);
		while(st.hasMoreTokens()) {
			hexList.add(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		int groupNumber = IPV6_LENGTH - hexList.size() + 1;
		
		for(int i = 0; i < hexList.size(); i++) {
			String hex = hexList.get(i);
			
			if(hex.equals(identifier)) {
				for(int j = 0; j < groupNumber - 1; j++) {
					sb.append("0000:");
				}
				sb.append("0000");
			}
			else {
				for(int j = 0; j < 4 - hex.length(); j++) {
					sb.append('0');
				}
				sb.append(hex);				
			}
			
			if(i < hexList.size() - 1) {
				sb.append(':');
			}
		}
		System.out.println(sb);
	}
}