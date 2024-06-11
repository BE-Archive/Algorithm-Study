import sys

input = sys.stdin.readline

N, M, K = map(int, input().rstrip().split())

meals = [tuple(map(int, input().rstrip().split())) for _ in range(N)]

dp = [[[-1 for _ in range(K + 1)] for _ in range(M + 1)] for _ in range(N)]


def find_dp(i, x, y):
    if x <= 0 or y <= 0 or i < 0:
        return 0

    if dp[i][x][y] != -1:
        return dp[i][x][y]

    cx, cy = meals[i - 1]

    if cx > x or cy > y:
        dp[i][x][y] = find_dp(i - 1, x, y)
    else:
        dp[i][x][y] = max(
            find_dp(i - 1, x, y),
            1 + find_dp(i - 1, x - cx, y - cy),
        )

    return dp[i][x][y]


print(find_dp(N - 1, M, K))
