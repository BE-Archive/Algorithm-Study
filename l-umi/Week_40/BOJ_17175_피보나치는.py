n = int(input())

MOD = 1_000_000_007
dp = [0] * 51

######################################

def fibonacci_calc(n):

    dp[0] = 1 
    if n > 0:
        dp[1] = 1

    for i in range(2, n + 1):
        dp[i] = (dp[i-1] + dp[i-2] + 1) % MOD

    return dp[n]

######################################

print(fibonacci_calc(n))
