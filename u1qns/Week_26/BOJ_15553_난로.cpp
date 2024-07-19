#include <iostream>
#include <queue>

int start, end;

int main()
{

    std::priority_queue<int> pq;

    int N, K;
    int answer = 0;
    std::cin >> N >> K;
    std::cin >> start;

    int pre = start, tmp;
    for (int i=1; i<N; ++i)
    {
        std::cin >> tmp;
        pq.push(tmp- pre-1);
        pre = tmp;
    }

    end = pre;
    answer = end+1 - start; // 전체 시간 (마지막으로 나간 시간 - 처음 들어온 시간)

    while (--K)
    {
        answer -= pq.top();
        pq.pop();
    }

    std::cout << answer;

    return 0;
}
