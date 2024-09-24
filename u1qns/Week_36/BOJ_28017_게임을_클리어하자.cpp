#include <iostream>
using namespace std;

constexpr int MAX = 500 + 1;
int min(const int a, const int b) { return a < b ? a : b;}
int main()
{
    int N, M;
    int inp[MAX][MAX], dp[MAX][MAX];

    cin >> N >> M;

    for(int i=1; i<=N; ++i)
        for(int j=0; j<M; ++j)
            cin >> inp[i][j];


    for(int i=1; i<=N; ++i)
    {
        for(int j=0; j<M; ++j)
        {
            int tmp = 1e9;
            for(int t=0; t<M; ++t)
            {
                if(j != t)
                    tmp = min(tmp, dp[i-1][t]);
            }
            dp[i][j] = tmp + inp[i][j];
        }
    }

    int answer = 1e9;
    for(int i=0; i<M; ++i)
        answer = min(answer, dp[N][i]);
    cout << answer;
    return 0;
}
