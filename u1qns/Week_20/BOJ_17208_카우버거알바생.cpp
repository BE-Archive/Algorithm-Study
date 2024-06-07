#include <iostream>

int main()
{
    int N, M, K, x, y;
    int dp[101][301][301]= {0, };
    int answer = 0;

    std::cin >> N >> M >> K;

    for(int i=1; i<=N; ++i)
    {
        std::cin >> x >> y;
        for(int j=1; j<=M; ++j)
        {
            for(int h=1; h<=K; ++h)
            {
                if(x > j || y > h)
                    dp[i][j][h] = dp[i-1][j][h];
                else
                {
                	dp[i][j][h] = std::max(dp[i-1][j][h], dp[i-1][j-x][h-y] + 1);
                	answer = std::max(answer, dp[i][j][h]);
                }
            }

        }
    }

    std::cout << dp[N][M][K];
    return 0;
}
