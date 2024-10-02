T = int(input())
test_cases = [int(input()) for _ in range(T)]

max_n = max(test_cases)

###############################################
dp = [1] * 10001
# dp 테이블을 생성하고 1로 초기화 (1로만 숫자를 만드는 경우는 항상 1가지이기 때문)


# 2를 사용해서 dp 테이블 갱신
for i in range(2, max_n + 1):
    dp[i] = dp[i] + dp[i - 2]

# 3을 사용해서 dp 테이블 갱신
for i in range(3, max_n + 1):
    dp[i] = dp[i] + dp[i - 3]
        
###############################################
for n in test_cases:
    print(dp[n])
