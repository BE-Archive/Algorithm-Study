#include <iostream>
#include <queue>

using namespace std;
int answer = 1e9, N, K;
typedef pair<int, int> pii;
pii info[21];

void dfs(const int position, const int energy, const bool bJump)
{
    if(energy > answer || position >= N) return;

    if(position == N-1) {
        answer = (answer < energy ? answer : energy);
        return ;
    }

    dfs(position+1, energy + info[position].first, bJump);
    dfs(position+2, energy + info[position].second, bJump);
    if(!bJump) dfs(position+3, energy + K, true);
}

int main()
{
    cin >> N;
    for(int i=0; i<N-1; ++i)
    {
        cin >> info[i].first >> info[i].second;
    }
    cin >> K;

    dfs(0, 0, 0);

    cout << answer;
    return 0;
}
