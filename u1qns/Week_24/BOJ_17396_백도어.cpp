#include <iostream>
#include <queue>
#include <vector>
#include <limits.h>
#include <algorithm>
#define MAX 100001
#define INF LLONG_MAX

typedef std::pair<long long, int> pii;

int N, M;
std::priority_queue<pii, std::vector<pii>, std::greater<>> pq;
std::vector<std::vector<pii>> graph;
bool isVisible[MAX];

long long getAnswer()
{
    std::vector<long long> dist(N, INF);
    dist[0] = 0;

    pii top;
    pq.push({0, 0});

    while(!pq.empty())
    {
        top = pq.top();
        pq.pop();
        
        if(top.second == N-1) return top.first;

        if(top.first > dist[top.second]) continue;

        for(int i = 0; i < graph[top.second].size(); ++i)
        {
            int next_node = graph[top.second][i].first;

            if(isVisible[next_node] && next_node != N-1) continue;

            long long next_cost = graph[top.second][i].second + top.first;

            if(dist[next_node] > next_cost)
            {
                dist[next_node] = next_cost;
                pq.push({next_cost, next_node});
            }
        }
    }

    return dist[N-1] == INF ? -1 : dist[N-1];
}

int main() {

    std::ios::sync_with_stdio(false);
    std::cin.tie(NULL);

    int a, b, t;
    std::cin >> N >> M;

    graph.resize(N);

    for(int i = 0; i < N; ++i)
        std::cin >> isVisible[i];

    for(int i = 0; i < M; ++i)
    {
        std::cin >> a >> b >> t;
        graph[a].push_back({b, t});
        graph[b].push_back({a, t});
    }

    std::cout << getAnswer();

    return 0;
}
