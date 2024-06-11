#include <string>
#include <vector>
#include <iostream>

using namespace std;

int solution(int n, vector<int> tops) {
    int answer = 0;
    vector< vector<int> > dp(2, vector<int> (n, 0));


    // 탑의 유무로 만들지 못하는 경우가 생김
    dp[0][1] = 2;
    dp[1][1] = 3;
    for(int i=2; i<n; ++i)
    {
        if(tops[i-1] == 1)
        {
            // 직전에 탑이 있으면 모양을 더 만들 수 있지만
            // 타일이 서로 겹치는 부분이 생기며 이 부분을 빼야함 
        }
        else
        {
          // 직전 탑이 없으면..
        }
            
        
        //cout << dp[0][i] << "\t" << dp[1][i] << "\n";
    }
    
}
