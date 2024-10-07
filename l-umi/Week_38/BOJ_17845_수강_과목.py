import sys
input = sys.stdin.readline


N, K = map(int, input().split())
subjects = [tuple(map(int, input().split())) for _ in range(K)]

############################################################


def knapsack(N, K, subjects):
    dp = [0] * (N + 1)  # 공부시간

    for importance, time in subjects:
        for t in range(N, time - 1, -1):
            dp[t] = max(dp[t], dp[t - time] + importance)

    return dp[N]


############################################################

result = knapsack(N, K, subjects)

print(result)
