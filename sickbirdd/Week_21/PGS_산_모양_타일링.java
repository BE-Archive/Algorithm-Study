class Solution {
	final static int MOD = 10007;
	static int[][] dp;
	public static int foo(int[] tops, int index, int type) {
		if(index == 0) return 1;
		if(dp[index][type] > 0) return dp[index][type];
		int cnt = tops[index - 1] == 1 ? 4 : 3;
		for(int i = 0; i < cnt; i++) {
			if(type == 1 && i != 2) {
				dp[index][type] += foo(tops, index - 1, i) % MOD;
			}
			else if(type != 1){
				dp[index][type] += foo(tops, index - 1, i) % MOD;
			}
		}
		return dp[index][type] % MOD;
	}
	public int solution(int n, int[] tops) {
		int ans = 0, cnt = tops[n - 1] == 1 ? 4 : 3;
		dp = new int[n][4];
		for(int i = 0; i < cnt; i++ ) {
			ans += foo(tops, n - 1, i);
		}
		return ans % MOD;
	}
}