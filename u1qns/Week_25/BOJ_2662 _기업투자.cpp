#include <iostream>
#include <vector>
#include <algorithm>

#define N_MAX 301
#define M_MAX 21

typedef std::pair<int, int> pii;

int N, M;
int arr[M_MAX][N_MAX];
int dp[M_MAX][N_MAX];
std::vector<pii> list[M_MAX][N_MAX];

int main()
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);

            int temp;

    std::cin >> N >> M;

    for (int i = 1; i <= N; ++i)
    {
        std::cin >> temp;
        for (int j = 1; j <= M; ++j)
        {
            std::cin >> arr[j][i];
        }
    }

    for (int i = 1; i <= M; ++i)
    {
        for (int j = 1; j <= N; ++j)
        {
            for (int k = N; k > 0; --k)
            { 
                    if(j < k) continue;
                    if (dp[i][j] < dp[i - 1][j])
                    {
                        dp[i][j] = dp[i - 1][j];
                        list[i][j] = list[i - 1][j];
                    }
                    if (dp[i][j] < dp[i - 1][j - k] + arr[i][k])
                    {
                        dp[i][j] = dp[i - 1][j - k] + arr[i][k];
                        list[i][j] = list[i - 1][j - k];
                        list[i][j].push_back({i, k});
                    }
            }
        }
    }

    std::cout << dp[M][N] << "\n";
    std::vector<int> answer(M + 1, 0);
    for (const auto& p : list[M][N])
    {
        answer[p.first] = p.second;
    }

    for (int i = 1; i <= M; ++i)
    {
        std::cout << answer[i] << " ";
    }

    return 0;
}
