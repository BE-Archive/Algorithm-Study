#include <iostream>
using namespace std;
constexpr int MAX_N = 20 + 1;

int L[MAX_N] = {0, };
int J[MAX_N] = {0, };
int dp[101] = {0, };
int N;

int max(int a, int b) { return a > b ? a : b; }

int main()
{
    cin >> N;
    for(int i=1; i<=N; ++i) cin >> L[i];   
    for(int i=1; i<=N; ++i) cin >> J[i];

    for(int i=1; i<=N; ++i)
    {
        for(int j=100; j>0; --j)
        {
            if(j > L[i])
                dp[j] = max(dp[j], dp[j-L[i]] + J[i]);
        }
    }
    
    cout << dp[100];
    return 0;
}
