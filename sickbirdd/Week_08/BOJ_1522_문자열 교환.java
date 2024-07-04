import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] s = br.readLine().toCharArray();
		int L = 0;
		for(int i = 0; i < s.length; i++) {
			if(s[i] == 'a') L++;
		}
		int answer = Integer.MAX_VALUE;
		for(int i = 0; i < s.length; i++) {
			int count = 0;
			for(int j = i; j < i + L; j++) {
				int index = j % s.length;
				if(s[index] == 'b') count++;
			}
			answer = Math.min(answer, count);
		}
		System.out.println(answer);
	}
}