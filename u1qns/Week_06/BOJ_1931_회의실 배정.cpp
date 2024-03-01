#include <iostream>
#include <queue>

typedef std::pair<int, int> pii;

struct cmp
{
    bool operator()(const pii& a, const pii& b)
    {
        if(a.second == b.second)
        {
            return a.first > b.first;
        }
        return a.second > b.second;
    }
};

int main()
{
    int N, start, end, answer = 0;
    std::priority_queue<pii, std::vector<pii>, cmp> pq;
    
    std::cin >> N;
    for(int i=0; i<N; ++i)
    {
        std::cin >> start >> end;
        pq.emplace(start, end);
    }
    
    end = 0;
    while(!pq.empty())
    {
        // 끝나자 마자 시작할 수 있는 조건 >=
        if(pq.top().first >= end)
        {
            end = pq.top().second;
            ++answer;
        }
        pq.pop();
    }
    
    std::cout << answer;
    
    return 0;
}
