#include <iostream>
#include <vector>

int N;
bool bAnswer = false;
std::vector<std::vector<int>> arr;
bool visited[10001][10] = {false, };
std::vector<int> answer;

void solve(const int depth, const int pre)
{
    if(bAnswer || depth == N)
    {
        bAnswer = true;
        return;
    }

    for(const auto& i : arr[depth])
    {
        if(bAnswer == true) return;
        if(i == pre || visited[depth][i]) continue;

        visited[depth][i] = true;
        answer[depth] = i;
        solve(depth + 1, i);
    }
}

int main()
{
    std::cin >> N;
    arr.resize(N);
    answer.resize(N);

    int M;
    for(int i=0; i<N; ++i)
    {
        std::cin >> M;
        arr[i].resize(M);

        for(int j=0; j<M; ++j)
            std::cin >> arr[i][j];
    }
    
    solve(0, 0);

    if(bAnswer)
    {
        for(const auto& i : answer)
            std::cout << i << "\n";
    }
    else
        std::cout << -1;
        
    return 0;
}
