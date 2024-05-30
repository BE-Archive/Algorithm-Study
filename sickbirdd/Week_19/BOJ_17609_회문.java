import java.io.*;

public class Main {
	static char[] S;
	static int isPalindrome(int start, int end, int rep) {
		if(rep > 1 || start >= end) return rep;
		if(S[start] == S[end]) return isPalindrome(start + 1, end - 1, rep);
		return Math.min(isPalindrome(start, end - 1, rep + 1), isPalindrome(start + 1, end, rep + 1));
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < T; i++) {
			S = br.readLine().toCharArray();
			sb.append(Math.min(isPalindrome(0, S.length - 1, 0), 2)).append('\n');
		}
		System.out.println(sb);
	}
}