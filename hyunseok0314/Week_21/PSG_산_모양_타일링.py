def solution(n, tops):
    mod = 10007
    
    dp = [[0, 0] for _ in range(n + 1)]
    dp[n] = [1, 0]
    
    for i in range(n):
        dp[i][1] = sum(dp[i - 1]) % mod
        
        dp[i][0] = dp[i][1] + dp[i - 1][0] % mod
        
        if(tops[i]):
            dp[i][0] += dp[i][1] % mod
    
    return sum(dp[n - 1]) % mod