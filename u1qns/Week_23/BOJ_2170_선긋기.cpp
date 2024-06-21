#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>

typedef std::pair<int, int> pii;

long long getAnswer(std::vector<pii>& line)
{
    long long answer = 0;

    std::sort(line.begin(), line.end());

    int start = line[0].first;
    int end = line[0].second;

    for(auto& [x, y] : line)
    {
        if(end < x) 
        {
            answer += end - start;
            start = x;
        }
        end = std::max(end, y); // 끝점은 계속 커져야하고
    }

    return answer + end - start;
}

int main()
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);

    int N, x, y;
    std::cin>>N;
    std::vector<pii> line(N);
    for(int i=0; i<N; ++i)
        std::cin >> line[i].first >> line[i].second;

    std::cout << getAnswer(line);

    return 0;
}
