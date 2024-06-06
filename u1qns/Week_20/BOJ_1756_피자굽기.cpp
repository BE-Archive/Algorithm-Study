#include <iostream>
#include <vector>
#include <algorithm>
int N, M;

int solve(const std::vector<long long>& oven)
{
    int depth = N;
    int cnt = 0;

    long long pizza;
    for(int i=0; i<M; ++i)
    {
        std::cin >> pizza;
        while(1)
        {
            if(depth-- < 0) return 0;
            if(pizza <= oven[depth])
            {
                ++cnt;
                break;
            }
        }
    }

    return cnt == M ? depth + 1 : 0;
}

int main()
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(0);

    std::cin >> N >> M;

    long long input;
    long long r = 1e9;
    std::vector<long long> oven(N, 0);
    for(int i=0; i<N; ++i)
    {
        std::cin >> input;
        if(r > input)  r = input;
        oven[i] = r;
    }
    std::cout << solve(oven);

    return 0;
}
