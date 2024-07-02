import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] dp; 
	final static int CONST = Integer.MAX_VALUE;
	public static int foo(int x) {
		if(x < 0) return CONST;
		if(dp[x] != CONST) return dp[x];
		
		int cal = Math.min(foo(x - 5), foo(x - 3));
		if(cal == CONST) return CONST;
		return dp[x] = cal + 1;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		dp = new int[N + 1];
		Arrays.fill(dp, 1, N + 1, CONST);
		System.out.println(foo(N) == CONST ? -1 : dp[N]);
	}
}