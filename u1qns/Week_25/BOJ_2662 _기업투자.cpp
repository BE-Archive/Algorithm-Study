#include <iostream>
#include <vector>
#include <string>

int main() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::cout.tie(nullptr);

    int N, M;
    std::cin >> N >> M;

    int cnt[301] = {0, };
    int weight[301] = {0, };
    int value[301] = {0, };
    int dp[301] = {0, };
    int cost_idx[301];

    int tmp, idx;
    for(int i=0; i<N; ++i)
    {

        std::cin >> weight[i];
        value[i] = 0;

        for(int j=0; j<M; ++j)
        {
            std::cin >> tmp;
            if(value[i] < tmp)
            {
                value[i] = tmp;
                idx = j;
            }
        }
        cost_idx[i] = idx;
    }

    for (int i = 1; i <= N; i++)
    {
        for (int j = N; j >= 1; j--)
        {
            if (weight[i] <= j)
            { // 넣을 수 있다면?
                if(dp[j] < dp[j - weight[i]] + value[i])
                {
                    dp[j] = dp[j - weight[i]] + value[i];
                }
            }
        }
    }

    std::cout << dp[N];

    return 0;
}
