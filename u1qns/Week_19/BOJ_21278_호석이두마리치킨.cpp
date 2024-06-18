#include <iostream>
#include <vector>
#include <algorithm>

const int INF = 100*100*100;
const int MAX = 100 + 1;
int N;
int answer = 1e9;
int answers[2] = {0, };
int pick[2] =  {false, };

std::vector<std::vector<int>> graph;
void comb(const int idx, const int cnt)
{
    if(cnt == 2)
    {
        // 구하기
        int a = pick[0]; int b = pick[1];
        int tmp = 0;
        for(int i=1; i<=N; ++i)
            tmp += std::min(graph[a][i], graph[b][i]);

        if(answer > tmp)
        {
            answer = tmp;
            answers[0] = std::min(a, b);
            answers[1] = std::max(a, b);
        }
        return;
    }

    for(int i=idx; i<=N; ++i)
    {
        pick[cnt] = i;
        comb(i+1, cnt+1);
    }
}

int main()
{
    int M, A, B;

    std::cin >> N >> M;
    graph.resize(N+1, std::vector<int>(N+1, INF));

    for(int i=1; i<=N; ++i)
        graph[i][i] = 0;

    for(int i=0;i <M; ++i)
    {
        std::cin >> A >> B;
        graph[A][B] = graph[B][A] = 1;
    }

    // 모든 정점으로부터 다른 모든 정점의 거리 비용을 구해야 함 -> 플루이드 와샬
    for(int k=1; k<=N; ++k)
    {
        for(int i=1; i<=N; ++i)
        {
            for(int j=1; j<=N; ++j)
            {
                graph[i][j] = std::min(graph[i][j], graph[i][k] + graph[k][j]);
            }
        }
    }

    comb(1, 0);

    printf("%d %d %d", answers[0], answers[1], answer*2);
    return 0;
}
