#include <iostream>
#include <vector>
#include <memory.h>
#define MAX 200001

int N;
long long answer = 0;
bool visited[MAX] = {false, };
bool indoors[MAX] = {false, };
std::vector<int> adj[MAX];

void dfs(const int idx)
{
    for(const int i: adj[idx])
    {
        if(visited[i]) continue;

        visited[i] = true;

        if(indoors[i]) ++answer;
        else dfs(i);
    }
}

int main()
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);

    char ch;
    int from, to;

    std::cin >> N;
    for(int i=0; i<N; ++i)
    {
        std::cin >> ch;
        indoors[i] = (ch == '1');
    }

    for(int i=0; i < N-1; ++i)
    {
        std::cin >> from >> to;
        adj[--from].push_back(--to);
        adj[to].push_back(from);
    }

    for(int i=0; i<N; ++i)
    {
        if(!indoors[i]) continue;

        memset(visited, false, sizeof(visited));
        visited[i] = true;
        dfs(i);
    }

    std::cout << answer;

    return 0;
}
