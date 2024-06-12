#include <string>
#include <vector>

using namespace std;

int solution(int n, vector<int> tops) {
    vector< vector<int> > dp(n, vector<int> (2, 0));
    const int mod = 10007;

    dp[0][0] = (tops[0] ? 3 : 2);
    dp[0][1] = 1;
    
    for(int i=1; i<n; ++i)
    {
        dp[i][1] = (dp[i-1][0] + dp[i-1][1]) % mod;
        
        if(tops[i])
            dp[i][0] = (dp[i-1][0]*3 + dp[i-1][1]*2) % mod;
        else
            dp[i][0] = (dp[i-1][0]*2 + dp[i-1][1]) % mod;
    }
    
    return (dp[n-1][0]+dp[n-1][1])% mod;
}
