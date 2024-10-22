#include <iostream>
#include <vector>
#include <queue>
using namespace std;

typedef pair<int, int> pii;

vector<vector<pii>> adj;
vector<vector<int>> answer;

const int INF = 1e9;
int N, M;

void dijkstra(const int start)
{
    priority_queue<pii, vector<pii>, greater<>> pq;
    pq.push({0, start});

    vector<int> dp(N+1, INF);
    dp[start] = 0;

    vector<int> route(N+1, -1);

    while(!pq.empty())
    {
        int cost = pq.top().first;
        int idx = pq.top().second;
        pq.pop();

        if(dp[idx] < cost) continue;
        for(const auto& [cost, next] : adj[idx])
        {
            int next_cost = dp[idx] + cost;
            if (dp[next] > next_cost)
            {
                dp[next] = next_cost;
                route[next] = idx;
                pq.push({next_cost, next});
            }
        }
    }

    for (int i = 1; i <= N; ++i)
    {
        answer[i][start] = route[i];
    }

}



int main()
{
    cin >> N >> M;

    answer.resize(N+1, vector<int>(N+1, -1));
    adj.resize(N+1);
    int from, to, cost;
    for(int i=0; i<M; ++i)
    {
        cin >> from >> to >> cost;
        adj[from].push_back({cost, to});
        adj[to].push_back({cost, from});
    }

    for(int start=1; start<=N; ++start)
    {
        dijkstra(start);
    }

    for(int i=1; i<=N; ++i)
    {
        for(int j=1; j<=N; ++j)
        {
            if(i == j || answer[i][j] == -1)
                cout << "- ";
            else
                cout << answer[i][j] << " ";
        }
        cout << "\n";
    }

    return 0;
}
