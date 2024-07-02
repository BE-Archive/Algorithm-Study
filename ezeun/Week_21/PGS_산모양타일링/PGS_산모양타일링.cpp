#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

const int mod = 10007;

int dp[100001][2]; 
//dp[i][0]: i번째까지 정삼각or왼쪽사다리꼴or마름모로 채운 경우
//dp[i][1]: i번째까지 오른쪽사다리꼴로 채운 경우
int solution(int n, int tops[], size_t tops_len) {
    
    if(tops[0]){
        dp[0][0]=3;
        dp[0][1]=1;
    }
    else{
        dp[0][0]=2;
        dp[0][1]=1;
    }
    
    for(int i=1; i<n; i++){
        dp[i][1] = (dp[i-1][0] + dp[i-1][1])%mod;
        
        if(tops[i]){
            dp[i][0] = (dp[i-1][0]*3 + dp[i-1][1]*2)%mod;
        }
        else{
            dp[i][0] = (dp[i-1][0]*2 + dp[i-1][1])%mod;
        }
    }
    
    return (dp[n-1][0]+dp[n-1][1])%mod;
}