#include <iostream>
#include <memory.h>
#define MAX 11

int answer = 0;
int arr[MAX][MAX] = { 0, };
int visited[MAX] = {false, };

void solve(const int depth, const int sum)
{
    if(depth == MAX)
    {
        answer = (answer > sum ? answer : sum);
        return ;
    }

    for(int i=0; i<MAX; ++i)
    {
        if(visited[i] || arr[depth][i] == 0) continue;
        visited[i] = true;
        solve(depth + 1, sum + arr[depth][i]);
        visited[i] = false;
    }
}

int main()
{
    int T = 0;
    std::cin >> T;
    while(T--)
    {
        for(int i=0; i<MAX; ++i)
        {
            for(int j=0;j<MAX; ++j)
                std::cin >> arr[i][j];
        }

        answer = 0;
        memset(visited, false, sizeof(visited));
        solve(0, 0);
        std::cout << answer << "\n";
    }
    return 0;
}
