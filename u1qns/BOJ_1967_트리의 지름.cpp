#include <iostream>
const int MAX = 100000 + 1;
bool visited[MAX] = {false, };

int N, answer = 0;
struct Info
{
    int dir[2];
    int sum[2];

    void setValue(const int d, const int v, const boolean b)
    {
        dir[b] = d;
        sum[b] = v;
    }
} node [MAX];

int solve(const int idx, bool d)
{
    if(node[idx].dir[d] == 0)
        return node[idx].dir[d];

    if(visited[idx]) return node[idx].dir[d];
    visited[idx] = true;
    node[idx].sum[d] += solve(node[idx].dir[d], d);
    node[idx].sum[!d] += solve(node[idx].dir[!d], !d);
    return node[idx].sum[d];
}

int main()
{
    std::cin >> N;
    int p, c, v;
    bool isLeft = true;
    for(int i=1; i<N; ++i)
    {
        std::cin >> p >> c >> v;

        if(node[p].dir[0] == 0) node[p].setRight(c, v, 1);
        else node[p].setRight(c, v, 0);
    }

    solve(1, 0);
    solve(1, 1);
    for(int i=1; i<=N; ++i)
    {
        int tmp = node[i].sum[0] + node[i].sum[1];
        answer = (answer > tmp ? answer : tmp);
    }
    std::cout << answer;


    return 0;
}
