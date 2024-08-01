package trankill1127.w28;

import java.io.*;

public class BOJ_1254 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine().trim();
		int len=s.length();
		for (int now=0; now<s.length(); now++){
			if ( isPalindrome(s.substring(now)) ) break;
			len++;
			//System.out.println(now+" "+len);
		}
		System.out.println(len);
	}

	public static boolean isPalindrome(String s){
		int l=0; int r=s.length()-1;
		while ( l<=r ) {
			if (s.charAt(l) != s.charAt(r)) return false;
			l++; r--;
		}
		return true;
	}
}