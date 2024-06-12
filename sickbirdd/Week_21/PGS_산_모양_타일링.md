## 소스코드
```
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
```

## 소요시간

1시간 30분

## 알고리즘

DP

## 풀이

- 뭔가 깔끔한 **1차원 점화식 풀이**를 생각하려다가 시간이 오래걸려서 결국 **2차원 점화식**으로 해결한 문제입니다.
- 깔끔하게 풀지 않더라도 푼 사람이 승자다! 라는 교훈을 얻게 해준 문제 같습니다.
- `Bottom-Up` 방식으로도 풀릴 것 같았지만 뭔가 직관적으로 떠올랐을 때 편한 방법은 `Top-Down`이라서 이를 활용하여 풀었습니다.
    - 기본적으로 삼각형이 윗변에 있을 경우 표현 가능한 경우의 수는 총 4가지이고, 없을 경우 총 3가지입니다.
        ![image](https://github.com/BE-Archive/Algorithm-Study/assets/39708676/ac28e270-9504-405d-a4de-8a232d164dff)
        - 위와 같이 dp 1차원 배열에 **4가지 상태**를 추가적으로 고려한 **2차원 배열**을 선언했습니다.
    - **만약에 현재 상태가 1번인데 다음 삼각형의 상태가 2번이 오는 경우는 불가능한 경우입니다.**
    - 따라서 이를 제외한 경우만 연산해줘서 답을 계산할 수 있습니다. 