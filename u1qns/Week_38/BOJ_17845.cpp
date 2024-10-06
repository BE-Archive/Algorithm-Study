#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
int dp[1001][10001] = {0, };

int main()
{
    int limit, N, v, w;

    cin >> limit >> N;

    for (int i = 1; i <= N; ++i)
    {
        cin >> v >> w;
        for (int j = 0; j <= limit; ++j)
        {
            // 현재 보고자 하는 무게 j가 입력받은 w보다 작다면,
            // dp[ 1 - 3] -> dp[-2]가 되므로 아래의 식이 필요하다.
            if (j < w)
                dp[i][j] = dp[i - 1][j];
            else
                dp[i][j] = max(dp[i - 1][j - w] + v, dp[i - 1][j]);
        }
    }
    cout << dp[N][limit];
    return 0;
}
