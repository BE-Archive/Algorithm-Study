#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

const int MAX_N = 1000000 + 1;

bool visited[MAX_N] = {false, };
int dp[MAX_N][2] = {false, };
vector<int> adj[MAX_N];

void dfs(const int idx)
{
    dp[idx][0] = 0; // 일반인이니까 0 
    dp[idx][1] = 1; // 내가 얼리어답터니까 1부터 시작

    for(int i=0; i<adj[idx].size(); ++i)
    {
        int tmp = adj[idx][i];
        if(visited[tmp]) continue;
        
        visited[idx] = true;
        
        dfs(tmp);
        dp[idx][0] += dp[tmp][1]; // 내가 일반인이라면 내 부모라도 얼리어 답터여야 함
        dp[idx][1] += min(dp[tmp][0], dp[tmp][1]); // 내가 얼리어답터라면 둘 중 고를 수 있음
    }
}

int main()
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);
    
    int N, u, v;
    cin >> N;
    for(int i=1; i<N; ++i)
    {
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    
    int start = 1; // 임의의정점
    dfs(start);
    cout << min(dp[start][0], dp[start][1]);
    return 0;
}
